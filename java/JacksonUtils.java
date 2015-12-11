package com.eflag.ocloud3;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * 
 * 
 * @author Tiny
 *
 * Time: Oct 15, 2015
 *
 */
public class JacksonUtils {

	public static <T> String toJson(T t) throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writerWithView(t.getClass());
		
		return writer.writeValueAsString(t);
	}
	
	
	public static <T> T fromJson(String json, T t) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectReader reader = mapper.reader(t.getClass());
		T result = reader.readValue(json);
		return result;
	}
	
}
