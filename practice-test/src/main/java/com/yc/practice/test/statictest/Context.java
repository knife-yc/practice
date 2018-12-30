package com.yc.practice.test.statictest;

import java.util.HashMap;
import java.util.Map;

public class Context {

	private static Map<String,String> map = new HashMap<String,String>();
	
	public static Map<String,String> getMap(){
		return map;
	}
	
	public void setMap(Map<String,String> map){
		this.map = map;
	}
	
}
