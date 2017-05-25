package com.jyx.util.jpa;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class SearchFilter {

    public enum Operator {
        EQ(Object.class, false), NEQ(Object.class, false), LIKE(String.class, false), LLIKE(String.class, false), RLIKE(
                String.class, false), NLIKE(String.class, false), GT(Comparable.class, false), LT(Comparable.class,
                false), GTE(Comparable.class, false), LTE(Comparable.class, false), GTDATE(Date.class, false), LTDATE(
                Date.class, false), GTEDATE(Date.class, false), LTEDATE(Date.class, false), ISNULL(Object.class, true), ISNOTNULL(
                Object.class, true), IN(String.class, false);

        @SuppressWarnings("rawtypes")
        public Class applyClass;

        public boolean isAllowNullValue = false;

        @SuppressWarnings("rawtypes")
        Operator(Class applyClass, boolean isAllowNullValue) {
            this.applyClass = applyClass;
            this.isAllowNullValue = isAllowNullValue;
        }
    }

    public enum Connector {
        AND, OR
    }

    public String fieldName;
    //完整路径名称
    public Object value;

    public Operator operator;
    //属性名称
    public String attributeName;

    public Connector connector;

    public SearchFilter(String fieldName, Operator operator, Object value, Connector connector) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        //属性名称默认和查询字段相同
        this.attributeName = fieldName;

        this.connector = connector;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME_
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();

        if (searchParams != null && searchParams.size() > 0) {
            for (Entry<String, Object> entry : searchParams.entrySet()) {
                // 过滤掉空值
                String key = entry.getKey();
                Object value = entry.getValue();


                // 拆分operator与filedAttribute
                String[] names = key.split("_");

                if (names.length != 2 && names.length != 3) {
                    throw new IllegalArgumentException(key + " is not a valid search filter name");
                }
                //根据名称获取操作标识
                Operator operator = Operator.valueOf(names[0]);

                if (value == null || StringUtils.isBlank(value.toString())) {
                    //操作标识的比较值不允许为null，则进行过滤
                    if (!operator.isAllowNullValue) {
                        continue;
                    } else {
                        value = null;
                    }
                }

                if (value instanceof String) {
                    value = ((String) value).trim();
                }
                //构造不同条件之间的连接符
                Connector connector = null;
                if (names.length < 3) {
                    connector = Connector.AND;
                } else {
                    connector = Connector.valueOf(names[2]);
                }

                // 创建searchFilter
                SearchFilter filter = new SearchFilter(names[1], operator, value, connector);

                filters.put(key, filter);
            }
        }

        return filters;
    }

    @Override
    public String toString() {
        return "SearchFilter [fieldName=" + fieldName + ", value=" + value + ", operator=" + operator + "]";
    }

}
