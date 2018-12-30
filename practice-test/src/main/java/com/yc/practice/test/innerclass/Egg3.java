package com.yc.practice.test.innerclass;

public class Egg3 {

	private String str = "2414";
	private String name = "w34";
	private int a = 10;
	private String s;
	
	public Egg3(String s){
		System.out.println("member[str:"+str+",name:"+name+",a:"+a+",s:"+this.s+"]");
		this.s = s;
		System.out.println("new Egg3()");
		System.out.println("member[str:"+str+",name:"+name+",a:"+a+",s:"+this.s+"]");
	}
	
	public static void main(String[] args){
		new Egg3("hahahha");
	}
}
