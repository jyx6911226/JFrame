package com.jyx.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * jsr校验工具类
 * add by jiangyuxi
 */
public class ValiderUtil {
    /**
     * 校验实体类，
     * 如果校验未通过，抛出异常（ValidationException）
     * 如果校验的类为null，抛出异常（NullPointerException）
     * 必须在校验的类中加入相应注解
     */
    public static Boolean validObj(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Valided Object is null");
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
//        	System.out.println("对象属性:"+constraintViolation.getPropertyPath());  
//          System.out.println("国际化key:"+constraintViolation.getMessageTemplate());  
//          System.out.println("错误信息:"+constraintViolation.getMessage()); 
            throw new ValidationException(constraintViolation.getMessage());
        }
        return true;
    }

    /**
     * 仅校验Set<String> prarmname中的字段
     */
    public static Boolean validObj(Object obj, Set<String> prarmname) {
        if (prarmname == null) {
            return true;
        }
        if (obj == null) {
            throw new NullPointerException("Valided Object is null");
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            //System.out.println("!!!"+constraintViolation.getPropertyPath());
            if (prarmname.contains(constraintViolation.getPropertyPath().toString())) {
                throw new ValidationException(constraintViolation.getMessage() != null ? constraintViolation.getMessage() : "Valid Error");
            }
        }
        return true;
    }
//	public static void main(String[] args) {
//		User user = new User();
////		user.setUsername("abc");
////		user.setPassword("pass");
//		ValiderUtil.validObj(user);
////		Boolean b = ValiderUtil.validEmail("15165194064@qq.com");
////		System.out.println(b);
//	}
}








