package com.yc.practice.test.inherit;

public class MoreUseful extends Useful {
//	public void f() {
//	}
//
//	public void g() {
//	}

	public void u() {
	}

	public void v() {
	}

	public void w() {
	}

	public static void main(String[] args) {

		Useful[] x = { new Useful(), new MoreUseful() };
		x[0].f();
		x[1].g();
		((MoreUseful) x[1]).u(); // Downcast/RTTI
		if(x[0] instanceof MoreUseful){
			((MoreUseful) x[0]).f(); // Exception thrown
		}
		

	}
}
