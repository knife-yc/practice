package com.yc.practice;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

	private LinkedBlockingQueue<AbstractRequestHandler> requestQueue = new LinkedBlockingQueue<AbstractRequestHandler>();
}
