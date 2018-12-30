package com.yc.practice.test.protect;

public class Son1 extends Parent{

	public void print(){
		super.getVar();
		System.out.println(super.var);
		
		Son1 son1 = new Son1();
		System.out.println(son1.var);
		son1.getVar();
		
		Parent parent = new Parent();
		parent.var = 100;
		System.out.println(parent.var);
		parent.getVar();
		
		Parent par = new Son1();
		System.out.println(par.var);
		System.out.println(par.getVar());
	}
	
}
