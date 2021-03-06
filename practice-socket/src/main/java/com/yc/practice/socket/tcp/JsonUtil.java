package com.yc.practice.socket.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	public static String toString(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(object);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("json writeValueAsString JsonProcessingException.", e);
		} catch (Exception e) {
			logger.error("json writeValueAsString Exception.", e);
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
			ObjectMapper mapper = new ObjectMapper();
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
	
}
