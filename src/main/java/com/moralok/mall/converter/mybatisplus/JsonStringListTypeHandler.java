package com.moralok.mall.converter.mybatisplus;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Json String to List<String>
 *
 * @author moralok
 * @since 2020/9/6
 */
public class JsonStringListTypeHandler extends AbstractJsonTypeHandler<List<String>> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected List<String> parse(String json) {
        if (json != null && !json.isEmpty()) {
            try {
                return objectMapper.readValue(json, new TypeReference<List<String>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    protected String toJson(List<String> strings) {
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
