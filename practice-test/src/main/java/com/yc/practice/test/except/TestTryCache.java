package com.yc.practice.test.except;

public class TestTryCache {

	public static void main(String[] args){
		while(true){
			try{
				Thread.sleep(1000);
				System.out.println("try module is executed");
//				throw new Exception();
			}catch(Exception e){
				System.out.println("catch module is executed");
			}finally{
				System.out.println("finally module is executed");
			}
		}
		
		
	}
	
}
