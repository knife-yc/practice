package com.yc.practice.test.finalclass;

public class Base implements Cloneable{

	public Base(){
		System.out.println("base class is constructed");
	}

	@Override
	protected Object clone(){
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
