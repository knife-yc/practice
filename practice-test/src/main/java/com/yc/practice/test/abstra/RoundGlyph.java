package com.yc.practice.test.abstra;

public class RoundGlyph extends Glyph{

	private int radius;
	public RoundGlyph(int r){
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = "+ radius);
	}
	
	@Override
	public void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}

	public static void main(String[] args){
		new RoundGlyph(5);
	}
	
}
