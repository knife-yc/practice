package com.yc.practice.test.innerclass;

public class BigEgg2 extends Egg2{

	protected class Yolk extends Egg2.Yolk{
		public Yolk(){
			System.out.println("BigEgg2 Yolk()");
		}
		
		public void f(){
			System.out.println("BigEgg2 Yolk.f()");
		}
	}
	
	public BigEgg2(){
		insertYolk(new Yolk());
	}
	
	public static void main(String[] args){
		Egg2 e2 = new BigEgg2();
		e2.g();
	}
}
