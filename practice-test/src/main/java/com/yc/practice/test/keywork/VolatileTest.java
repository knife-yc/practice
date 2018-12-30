package com.yc.practice.test.keywork;

public class VolatileTest {
//	private static boolean bChanged;
	private static volatile boolean bChanged;
	public static void main(String[] args) throws InterruptedException {
		new Thread() {

			@Override
			public void run() {
				for (;;) {
					if (bChanged == !bChanged) {
						System.out.println("!=");
						System.exit(0);
					}else{
						System.out.println("continue");
					}
				}
			}
		}.start();
		Thread.sleep(1);
		new Thread() {

			@Override
			public void run() {
				for (;;) {
					bChanged = !bChanged;
				}
			}
		}.start();
	}
}
