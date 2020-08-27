package com.moralok.mall.validation.constraintvalidators;

import com.moralok.mall.validation.constraints.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author moralok
 * @since 2020/8/27 下午2:03
 */
public class AgeValidator implements ConstraintValidator<Age, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(Age age) {
        // 传入注解对应的实例
        min = age.min();
        max = age.max();
        // 检查注解中的值，可有可无
        validateParameters();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // context 几乎用不上
        if (value == null) {
            return true;
        }
        return value >= min && value <= max;
    }

    private void validateParameters() {
        if (min < 0) {
            throw new IllegalArgumentException("年龄最小值为0");
        }
        if (min > max) {
            throw new IllegalArgumentException("年龄最小值应>=最大值");
        }
    }
}
