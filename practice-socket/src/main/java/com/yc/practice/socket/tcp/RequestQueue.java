package com.yc.practice.socket.tcp;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

	private LinkedBlockingQueue<AbstractRequestHandler> requestQueue = new LinkedBlockingQueue<AbstractRequestHandler>();
}
