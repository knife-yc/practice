package com.yc.practice.test.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test {

	public static void main(String[] args) {

		String filePath = FileUtil.getFilePath("docker-compose.yml");
		System.out.println(filePath);
		File file = new File(filePath);
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(file);
			byte[] b = new byte[1024];
			while(fileIn.read(b) != -1){
				String str = new String(b);
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
