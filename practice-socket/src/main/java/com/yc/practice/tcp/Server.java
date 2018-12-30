package com.yc.practice.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * tcp协议进行通信
 */
public class Server {
	public static final int PORT = 8080;
	
	private final static Logger logger = LoggerFactory.getLogger(Server.class);

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Started: " + s);
		try {
			while (true) {
				Socket socket = s.accept();
				Request request = getRequest(socket);
				SimpleRequestHandler simpleRequestHandler = new SimpleRequestHandler(socket);
				simpleRequestHandler.setRequest(request);
				Thread thread = new Thread(simpleRequestHandler);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	private static Request getRequest(Socket socket) {
		Request request = null;
		try {
			logger.info("Connection accepted: " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String requestStr = null;
			requestStr = in.readLine();
			request = Request.parseFrom(requestStr);
			logger.info("Echoing: " + request.toJson());
		} catch (IOException e) {
			logger.error("init IOException.", e);
		}
		return request;
	}
}
