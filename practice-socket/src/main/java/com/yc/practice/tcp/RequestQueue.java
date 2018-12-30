package com.yc.practice.tcp;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

	private LinkedBlockingQueue<AbstractRequestHandler> requestQueue = new LinkedBlockingQueue<AbstractRequestHandler>();
}
