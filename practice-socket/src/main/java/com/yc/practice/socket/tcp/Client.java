package com.yc.practice.socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
 * tcp协议进行通信
 */
public class Client {
	public static void main(String[] args) throws IOException {
		// Passing null to getByName() produces the
		// special "Local Loopback" IP address, for542
		// testing on one machine w/o a network:
		InetAddress addr = InetAddress.getByName(null);
		// Alternatively, you can use
		// the address or name:
		// InetAddress addr =
		// InetAddress.getByName("127.0.0.1");
		// InetAddress addr =
		// InetAddress.getByName("localhost");
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, Server.PORT);
		// Guard everything in a try-finally to make
		// sure that the socket is closed:
		try {
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Output is automatically flushed
			// by PrintWriter:
			// 发送数据的数据流
			PrintWriter print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			Request request = buildRequest();
			String json = request.toJson();
			print.println(json);// 将数据写入socket，并刷新数据流，是服务端马上收到数据,//因为客户和服务器在采取下一步操作之前都要等待一行文本内容的到达
			String str = in.readLine();// 读取服务端返回的数据
			System.out.println(request);
			System.out.println(str);

		} finally {
			System.out.println("closing...");
			socket.close();
		}
	}

	private static Request buildRequest() {
		Request request = new Request();
		request.setClientId("testId");
		request.setRequestBody("testBody");
		request.setRequestTimeMs(System.currentTimeMillis());
		return request;
	}
}
