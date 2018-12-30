package com.yc.practice.test.thread;

public class DaemonThread extends Thread {

	public DaemonThread() {
		super.setDaemon(true);
		super.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				
				System.out.println("deamon thread ");
				Thread.sleep(1000);
//				super.yield();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			DaemonThread daemonThread = new DaemonThread();
//			Thread.sleep(10000000);
		} catch (Exception e) {

		}
	}

}
