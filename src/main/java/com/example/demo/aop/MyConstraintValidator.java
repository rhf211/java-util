package com.example.demo.aop;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: 阮晗飞
 * @date: 2022/8/25
 * 自定义参数校验校验
 */
public class MyConstraintValidator implements ConstraintValidator<QsmSpecifiedSelector, Object> {
    @Override
    public void initialize(QsmSpecifiedSelector constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
