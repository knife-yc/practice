package com.yc.pratice.office.excel;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JSONUtil {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JSONUtil() {
    }

    public static String toString(Object object) {
        try {
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException var3) {
            logger.error("json writeValueAsString JsonProcessingException.", var3);
        } catch (Exception var4) {
            logger.error("json writeValueAsString Exception.", var4);
        }
        return null;
    }

    public static <T> T parse(String jsonStr, Class<T> returnObjectClass) {
        try {
            if (jsonStr == null) {
                return null;
            }

            if (jsonStr.length() == 0) {
                return null;
            }
            T returnObject = mapper.readValue(jsonStr, returnObjectClass);
            return returnObject;
        } catch (JsonParseException e) {
            logger.error("json readValue JsonParseException.", e);
        } catch (JsonMappingException e) {
            logger.error("json readValue JsonMappingException.", e);
        } catch (Exception e) {
            logger.error("json readValue Exception.", e);
        }

        return null;
    }


    public static <T> List<T> parseList(String jsonStr, Class<T> returnObjectClass) {
        try {
            if (jsonStr == null) {
                return null;
            }
            if (jsonStr.length() == 0) {
                return null;
            }
            return mapper.readValue(jsonStr, getCollectionType(List.class, returnObjectClass));
        } catch (JsonParseException e) {
            logger.error("json readValue JsonParseException.", e);
        } catch (JsonMappingException e) {
            logger.error("json readValue JsonMappingException.", e);
        } catch (Exception e) {
            logger.error("json readValue Exception.", e);
        }

        return null;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
