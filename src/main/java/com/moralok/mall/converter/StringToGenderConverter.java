package com.moralok.mall.converter;

import com.moralok.mall.domain.constant.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 怎么自动注册的呢？
 * 原始数据是 String！！！还在困惑为什么不会生效！！！
 *
 * @author moralok
 * @since 2020/9/1 2:57 下午
 */
@Component
public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        if (Objects.equals(Integer.valueOf(source), Gender.MALE.ordinal())) {
            return Gender.MALE;
        } else if (Objects.equals(Integer.valueOf(source), Gender.FEMALE.ordinal())) {
            return Gender.FEMALE;
        }
        throw new IllegalArgumentException("性别选择有误");
    }
}
