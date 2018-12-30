package com.yc.practice.test.statictest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "1");
		map.put("2", "2");
		Context context = new Context();
		context.setMap(map);
		Map<String,String> back = new Context().getMap();
		Iterator<Entry<String,String>> iterator = back.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String,String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println("key:"+key+",value:"+value);
		}
		
	}

}
