package com.yc.practice.test.abstra;

public abstract class Glyph {

	public Glyph(){
		System.out.println("before draw.....");
		draw();
		System.out.println("after draw.....");
	}
	
	public abstract void draw(); 
}
