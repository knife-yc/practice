package com.yc.practice.tcp;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleRequestHandler extends AbstractRequestHandler<String> {

	private Logger logger = LoggerFactory.getLogger(AbstractRequestHandler.class);
	private Request request;

	public SimpleRequestHandler(Socket socket) {
		super(socket);
	}

	@Override
	public String execute() throws Exception {
		String response = "server response:kiss your ass" + ",time:" + System.currentTimeMillis();
		return response;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
