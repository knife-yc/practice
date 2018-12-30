package com.yc.practice.test.inter;

public interface Actor {
	int a = 1;
	void act();
	
	default void laught(){
		
	}
	
	public static void test(){
		System.out.println("test");
		
	}
	
//	private static void test1(){
//		
//	}
	static void test2(){
		
	}
}
