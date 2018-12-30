package com.yc.practice.test.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompress {

	public static void main(String[] args) {
		try {
			FileOutputStream f = new FileOutputStream("test.zip");
			CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(csum));
			out.setComment("A test of Java Zipping");
			String path = System.getProperty("user.dir");
			List<String> filePathList = new ArrayList<String>();
			filePathList.add(path+"/src/main/resources/docker-compose.yml");
			filePathList.add(path+"/src/main/resources/docker-compose2.yml");
			filePathList.add(path+"/src/main/resources/docker-compose3.yml");
			filePathList.add(path+"/src/main/resources/docker-compose4.yml");
			// Can't read the above comment, though
			for (int i = 0; i < filePathList.size(); i++) {
				System.out.println("Writing file " + filePathList.get(i));
				BufferedReader in = new BufferedReader(new FileReader(filePathList.get(i)));
				String filePath = filePathList.get(i);
				int index = filePath.lastIndexOf("/");
				out.putNextEntry(new ZipEntry(filePath.substring(index + 1)));
				int c;
				while ((c = in.read()) != -1)
					out.write(c);
				in.close();
			}
			out.close();
			// Checksum valid only after the file
			// has been closed!
			System.out.println("Checksum: " + csum.getChecksum().getValue());
			
			// Now extract the files:
			System.out.println("Reading file");
			FileInputStream fi = new FileInputStream("test.zip");
			CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
			ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(csumi));
			ZipEntry ze;
			System.out.println("Checksum: " + csumi.getChecksum().getValue());
			while ((ze = in2.getNextEntry()) != null) {
				System.out.println("Reading file " + ze);
				int x;
				while ((x = in2.read()) != -1)
					System.out.write(x);
			}
			in2.close();// 314
			// Alternative way to open and read
			// zip files:
			ZipFile zf = new ZipFile("test.zip");
			Enumeration e = zf.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze2 = (ZipEntry) e.nextElement();
				System.out.println("\r\nFile: " + ze2);
				// ... and extract the data as before
			}
			Class c = ZipCompress.class;
			ZipCompress zipCompress = (ZipCompress)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}