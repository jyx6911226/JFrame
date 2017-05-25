package com.jyx.util.jpa;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withName;
import static org.reflections.ReflectionUtils.withParametersCount;
import static org.reflections.ReflectionUtils.withReturnTypeAssignableTo;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DynamicSpecifications {
    /**
     * 获取格式化之后的查询值
     *
     * @param filter
     * @return
     */
    private static Object getFormattedValue(SearchFilter filter) {

        //过滤空值
        if (filter.value == null || filter.value.toString().equals("null")) {
            return filter.value;
        }

        Object value = filter.value;

        if (filter.operator.applyClass.getName().equals(Date.class.getName()) &&
                !(filter.value instanceof Date)) {
            //处理日期类型
            String dateString = value.toString();
            String datePatten = null;
            //如果带有“-”，则认定为日期
            if (dateString.indexOf("-") > -1) {
                datePatten = "yyyy-MM-dd";
            }
            //如果有“:”，则认定为时间
            if (dateString.indexOf(":") > -1) {

                if (datePatten == null) {
                    datePatten = "";
                } else {
                    datePatten += " ";
                }

                datePatten += "HH:mm:ss";
            }

            try {
                if (datePatten != null) {
                    value = new SimpleDateFormat(datePatten).parseObject(dateString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (filter.operator.applyClass.getName().equals(String.class.getName())) {
            //对字符串进行处理
            String valueStr = value.toString();
            //为特殊字符加上转义字符
            value = valueStr.replaceAll("[%_$]", "/$0");
        }

        return value;
    }

    private static <T> Predicate createPredicate(Expression expression, SearchFilter filter, CriteriaBuilder builder) {

        Predicate predicate = null;
        switch (filter.operator) {
            case EQ:
                predicate = builder.equal(expression, getFormattedValue(filter));
                break;
            case NEQ:
                predicate = builder.notEqual(expression, getFormattedValue(filter));
                break;
            //字符串类型比较
            case LIKE:
                predicate = builder.like(expression, "%" + getFormattedValue(filter) + "%", '/');
                break;
            case LLIKE:
                predicate = builder.like(expression, "%" + getFormattedValue(filter), '/');
                break;
            case RLIKE:
                predicate = builder.like(expression, getFormattedValue(filter) + "%", '/');
                break;
            case NLIKE:
                predicate = builder.notLike(expression, "%" + getFormattedValue(filter) + "%", '/');
                break;
            //一般类型的比较处理
            case GT:
                predicate = builder.greaterThan(expression, (Comparable) getFormattedValue(filter));
                break;
            case LT:
                predicate = builder.lessThan(expression, (Comparable) getFormattedValue(filter));
                break;
            case GTE:
                predicate = builder.greaterThanOrEqualTo(expression, (Comparable) getFormattedValue(filter));
                break;
            case LTE:
                predicate = builder.lessThanOrEqualTo(expression, (Comparable) getFormattedValue(filter));
                break;
            //日期类型比较
            case GTDATE:
                predicate = builder.greaterThan(expression, (Comparable) getFormattedValue(filter));
                break;
            case LTDATE:
                predicate = builder.lessThan(expression, (Comparable) getFormattedValue(filter));
                break;
            case GTEDATE:
                predicate = builder.greaterThanOrEqualTo(expression, (Comparable) getFormattedValue(filter));
                break;
            case LTEDATE:
                predicate = builder.lessThanOrEqualTo(expression, (Comparable) getFormattedValue(filter));
                break;
            //空值判断
            case ISNULL:
                predicate = builder.isNull(expression);
                break;
            case ISNOTNULL:
                predicate = builder.isNotNull(expression);
                break;
            case IN:
                predicate = expression.in(StringUtils.split(filter.value.toString(), ','));
                break;
        }

        return predicate;
    }

    /**
     * 在实体中根据属性名称获取相应的get方法，方法参数个数为0
     *
     * @param entityClazz：实体类
     * @param attributeName：属性名称
     * @param returnType：返回值类型，get方法的返回值可以是returnType的子类
     * @return：满足条件的方法集合
     */
    @SuppressWarnings("unused")
    private static <T> Set<Method> getEntityGetMethods(Class<T> entityClazz, String attributeName, Class returnType) {
        Set<Method> methods = null;
        //获取所有的方法，包括父类中方法
        if (returnType == null) {
            methods = getAllMethods(entityClazz,
                    withName("get" + StringUtils.capitalize(attributeName)),
                    withParametersCount(0));
        } else {
            methods = getAllMethods(entityClazz,
                    withName("get" + StringUtils.capitalize(attributeName)),
                    withParametersCount(0),
                    withReturnTypeAssignableTo(returnType));
        }

        return methods;
    }

    /**
     * 构建关联关系。
     * 如果存在集合关联关系，查询时需要使用distinct，以免出现多条记录，
     * 使用distinct关键字后，order by的属性必须包含在select中。
     *
     * @param joinMap：查询条件
     * @param root：查找的连接源
     * @param builder：查询构造器
     * @param entityClazz：查询实体
     * @return：是否存在构建集合关联关系，如果存在，返回true，否则返回false。
     */
    private static <T> Boolean buildJoin(Map<String, List<SearchFilter>> joinMap, Root<T> root, CriteriaBuilder builder, Class<T> entityClazz) {
        boolean flag = false;
        //构造关联关系
        for (Map.Entry<String, List<SearchFilter>> entry : joinMap.entrySet()) {
//			Set<Method> methods = getEntityGetMethods(entityClazz , entry.getKey() , null) ;
//			if(methods==null||methods.size()<1){
//				throw new IllegalArgumentException(entry.getKey() + "不存在get方法");
//			}
//			获取第一个get方法
//			Method getMethod = methods.toArray(new Method[]{})[0] ;

            Attribute attribute = root.getModel().getAttribute(entry.getKey());

            //构建内连接对象
            Join join = null;

            if (attribute instanceof SetAttribute) {
                join = root.join((SetAttribute) attribute, JoinType.INNER);
            } else if (attribute instanceof ListAttribute) {
                join = root.join((ListAttribute) attribute, JoinType.INNER);
            } else if (attribute instanceof CollectionAttribute) {
                join = root.join((CollectionAttribute) attribute, JoinType.INNER);
            } else if (attribute instanceof SingularAttribute) {
                join = root.join((SingularAttribute) attribute, JoinType.INNER);
            }

            if (join == null) {
                throw new IllegalArgumentException(entry.getKey() + " 无法获取get方法或不支持连接查询。");
            }

            //判断是否存在与集合的关联关系
            if (!flag && attribute instanceof PluralAttribute) {
                flag = true;
            }

            Predicate predicate = null;
            for (SearchFilter filter : entry.getValue()) {
                predicate = joinPredicate(predicate, join.get(filter.attributeName), filter, builder);
            }
            //设置关联条件
            join.on(predicate);
        }
        return flag;
    }

    /**
     * 连接两个查询条件
     *
     * @param existPredicate：已有的查询条件
     * @param expression：新查询条件表达式
     * @param filter：查询源信息
     * @param builder：查询条件构造器
     * @return
     */
    private static Predicate joinPredicate(Predicate existPredicate, Expression expression, SearchFilter filter, CriteriaBuilder builder) {
        //创建新的predicate
        Predicate newPredicate = createPredicate(expression, filter, builder);
        //根据条件连接符构造查询条件
        if (newPredicate != null) {
            if (existPredicate == null) {
                existPredicate = newPredicate;
            } else {
                //存在两个predicate，使用条件连接符连接两个条件
                switch (filter.connector) {
                    case OR:
                        existPredicate = builder.or(existPredicate, newPredicate);
                        break;
                    default:
                        existPredicate = builder.and(existPredicate, newPredicate);
                        break;
                }
            }
        }

        return existPredicate;
    }

    public static <T> Specification<T> bySearchFilter(Map<String, Object> searchParams, final Class<T> entityClazz) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

        return bySearchFilter(filters.values(), entityClazz);
    }

    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        return new Specification<T>() {
            private Map<String, List<SearchFilter>> joinMap = new HashMap<String, List<SearchFilter>>();

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = null;

                if (!CollectionUtils.isEmpty(filters)) {
                    List<SearchFilter> filterList = new ArrayList<SearchFilter>();
                    filterList.addAll(filters);
                    //查询条件按照条件连接符进行排序，AND连接放在前面，OR连接放到后面
                    Collections.sort(filterList, new Comparator<SearchFilter>() {
                        @Override
                        public int compare(SearchFilter o1, SearchFilter o2) {
                            if (o1.connector == SearchFilter.Connector.OR && o2.connector == SearchFilter.Connector.AND) {
                                return 1;
                            } else if (o1.connector == SearchFilter.Connector.AND && o2.connector == SearchFilter.Connector.OR) {
                                return -1;
                            }
                            return 0;
                        }
                    });
//					构建查询条件
                    for (SearchFilter filter : filterList) {
                        String[] attributeNames = filter.fieldName.split("[\\.]");
                        if (attributeNames.length > 2) {
                            throw new IllegalArgumentException(filter.fieldName + " 不支持多层次的属性查询 ");
                        }
                        //对于关联属性特殊处理
                        if (attributeNames.length == 2) {
                            filter.attributeName = attributeNames[1];
                            if (joinMap.containsKey(attributeNames[0])) {
                                joinMap.get(attributeNames[0]).add(filter);
                            } else {
                                List<SearchFilter> searchFilterList = Lists.newArrayList();
                                searchFilterList.add(filter);
                                joinMap.put(attributeNames[0], searchFilterList);
                            }
                            continue;
                        }

                        predicate = joinPredicate(predicate, root.get(attributeNames[0]), filter, builder);
                    }

                    boolean isPlural = buildJoin(joinMap, root, builder, entityClazz);

//					构建完关联关系之后，移除已有的关系
                    joinMap.clear();
//					唯一性查询
                    query.distinct(isPlural);
                }

                return predicate;
            }
        };
    }
}
