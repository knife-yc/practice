package com.yc.practice.test.innerclass;

public class BigEgg extends Egg{

	protected class Yolk{//覆盖父类中的内部类Yolk
		public Yolk(){
			System.out.println("BigEgg Yolk()");
		}
	}
	
	public static void main(String[] args){
		new BigEgg();
	}
}
