package com.yc.practice.test.inter;

public class Stage {

	Actor a = new HappyActor();

	void change() {
		a = new SadActor();
	}

	void go() {
		a.act();
	}
	
	public static void main(String[] args){
		Stage s = new Stage();
		s.go(); // Prints "HappyActor"
		s.change();
		s.go(); // Prints "SadActor"
	}
}
