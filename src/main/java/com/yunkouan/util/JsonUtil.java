package com.yunkouan.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * 把数据封装成JSON格式字符串
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJson(Object obj) {
		try {
//			String json = JSON.toJSONString(obj);
//			return json;
			// 把数据封装成JSON格式字符串
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			String result = objectMapper.writeValueAsString(obj);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 把JSON格式数据转换成对象
	 * @param <T>
	 * @param json
	 * @return
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 */
	public static <T> T fromJson(String json, Class<T> c) {
		try {
//			T t = JSON.parseObject(json, c);
//			return t;
			// 把数据封装成JSON格式字符串
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			T result = objectMapper.readValue(json, c);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 把json转换为list
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> List<T> listFromJson(String json,Class<T> c){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = getCollectionType(List.class, c);
			List<T> list = objectMapper.readValue(json, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
		return null;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass,Class<?>... emementClass){
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, emementClass);
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		String s = JsonUtil.toJson(list);
		System.out.println(s);
		
		List<String> list2 = new ArrayList<String>();
		list2 = JsonUtil.listFromJson(s, String.class);
		System.out.println(list2.size());
		
	}
}