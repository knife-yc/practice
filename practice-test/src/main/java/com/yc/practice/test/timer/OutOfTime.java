package com.yc.practice.test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTime {

	public static void main(String[] args) {
		try {
			Timer timer = new Timer();//Timer在单个TimerTask任务执行过程中出现异常时就会取消整个timer
			timer.schedule(new ThrowTask(), 1);

			Thread.sleep(1000);
			timer.schedule(new ThrowTask(), 5);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class ThrowTask extends TimerTask {
		public void run() {
			throw new RuntimeException();
		}
	}
}
