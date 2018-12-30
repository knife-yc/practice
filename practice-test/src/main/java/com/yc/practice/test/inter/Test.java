package com.yc.practice.test.inter;

public class Test implements InterC{

	public void print() {
		// TODO Auto-generated method stub
		System.out.println("print is called");
	}

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("test is called");
	}

	public static void main(String args[]){
		Test test = new Test();
		InterA interA = (InterA)test;
		interA.print();
		InterB interB = (InterB)test;
		interB.print();
		System.out.println(Actor.a);
	}
}
