package com.sappe.ontrack.sdk.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Mapper {
	
	public static <T> T fromJSON(final TypeReference<T> type,final String jsonPacket) {
		   T data = null;
	
		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return data;
	}
	

}
