package com.yc.practice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRequestHandler<T> implements Runnable {

	private Logger logger = LoggerFactory.getLogger(AbstractRequestHandler.class);

	private Socket socket;

	public AbstractRequestHandler(Socket socket) {
		this.socket = socket;

	}

	public abstract T execute() throws Exception;

	@Override
	public void run() {
		try {
			PrintWriter print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			Response<T> resPonse = new Response<T>();
			long serverDealBeginTimeMs = System.currentTimeMillis();
			resPonse.setServerDealBeginTimeMS(serverDealBeginTimeMs);
			T t = execute();
			resPonse.setResult(t);
			long serverDealEndTimeMs = System.currentTimeMillis();
			long serverDealUsingTimeMS = serverDealEndTimeMs - serverDealBeginTimeMs;
			resPonse.setServerDealUsingTimeMS(serverDealUsingTimeMS);
			resPonse.setServerDealEndTimeMS(serverDealEndTimeMs);
			print.println(resPonse.toJson());
		} catch (Exception e) {
			logger.error("AbstractRequestHandler Exception.", e);
		} catch (Throwable e) {
			logger.error("AbstractRequestHandler Throwable.", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				logger.error("run,socket.close IOException.", e);
			}
		}
	}

}
