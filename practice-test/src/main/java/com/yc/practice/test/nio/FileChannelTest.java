package com.yc.practice.test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileChannelTest {

	public static void main(String[] args) {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(inetAddress.getHostAddress()+",hostName:"+inetAddress.getHostName());
//		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(, 1111));
//		SocketChannel.open();
	}
	
	public static void copyDataByFileChannel(){
		RandomAccessFile fromFile = null;
		RandomAccessFile toFile = null;
		FileChannel fromChannel = null;
		FileChannel toChannel = null;
		try {
			String path = System.getProperty("user.dir");
			fromFile = new RandomAccessFile(path + "/src/main/java/com/yc/test/nio/from.xml", "rw");
			toFile = new RandomAccessFile(path + "/src/main/java/com/yc/test/nio/to_channel.xml", "rw");
			fromChannel = fromFile.getChannel();
			long count = fromChannel.size();
			toChannel = toFile.getChannel();
			toChannel.transferFrom(fromChannel, 0, count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fromChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fromFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				toChannel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				toFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void copyDataFromChannel(){
		RandomAccessFile fromFile = null;
		RandomAccessFile toFile = null;
		FileChannel fromChannel = null;
		try {
			String path = System.getProperty("user.dir");
			fromFile = new RandomAccessFile(path + "/src/main/java/com/yc/test/nio/from.xml", "rw");
			toFile = new RandomAccessFile(path + "/src/main/java/com/yc/test/nio/to.xml", "rw");
			fromChannel = fromFile.getChannel();
			ByteBuffer bf = ByteBuffer.allocate(1024);
			int len = fromChannel.read(bf);
			bf.flip();//在读取channel中的数据时一定要反转buffer，反转之后buffer的position会变回0
			byte[] data = new byte[len];
			bf.get(data,0,len);
			bf.clear();
			toFile.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fromChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fromFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				toFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
