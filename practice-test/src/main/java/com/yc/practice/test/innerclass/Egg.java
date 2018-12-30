package com.yc.practice.test.innerclass;

public class Egg {

	protected class Yolk{
		public Yolk(){
			System.out.println("Egg.Yolk()");
		}
		
	}
	private Yolk y;
	public Egg(){
		System.out.println("new Egg()");
		y = new Yolk();
	}
}
