package com.yc.practice.test.finalclass;

public class Tset {

	public static void main(String[] args) {

		FinalSon finalSon = new FinalSon();
		finalSon.setAge(54);

		FinalSon finalSon2 = (FinalSon) finalSon.clone();
		finalSon.setAge(76);
		System.out.println(finalSon);
		System.out.println(finalSon2);
		// TODO Auto-generated catch block
	}
}
