package com.yc.practice.test.zip;

import java.io.File;

public class GZIPcompress {

	public static void main(String[] args) {
		try {
//			String path = System.getProperty("user.dir");
//			path = path+ "/src/main/resources/docker-compose.yml";
//			System.out.println(path);
//			BufferedReader in = new BufferedReader(new FileReader(path));
//			BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
//			System.out.println("Writing file");
//			int c;
//			while ((c = in.read()) != -1)
//				out.write(c);
//			in.close();
//			out.close();
//			System.out.println("Reading file");
//			BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
//			String s;
//			while ((s = in2.readLine()) != null)
//				System.out.println(s);
			
			String path = System.getProperty("user.dir");
			path = path+ "/src/main/resources/docker-compose.yml";
			File sourceFile = new File(path);
			File zipFile = new File(System.getProperty("user.dir")+"/src/main/resources/test.gz");
			System.out.println(zipFile.getPath());
			if(!zipFile.exists()){
				zipFile.createNewFile();
			}
			FileUtil.gzip(sourceFile, zipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
