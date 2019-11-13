/*
 * Copyright (C) 2011-2019 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBOXCHAIN inc.
 */
package com.bigguy.spider.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @AUTHOR Jream.Y
 * @CREATE 2019-08-29
 */
public class JSONUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    private JSONUtils() {

    }

    public static String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("toJson error: {}", e);
            return null;
        }
    }

    public static <T> T jsonParse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error("jsonParse error: {}", e);
            return null;
        }
    }

    public static Map beanToMap(Object object) {
        return jsonParse(toJSON(object), Map.class);
    }

    public static Map jsonToMap(String jsonStr) {
        return jsonParse(jsonStr, Map.class);
    }

    public static List jsonToList(String jsonStr) {
        return jsonParse(jsonStr, List.class);
    }

    public static <T> T jsonParse(Map map, Class<T> clazz) {
        return jsonParse(toJSON(map), clazz);
    }

}
