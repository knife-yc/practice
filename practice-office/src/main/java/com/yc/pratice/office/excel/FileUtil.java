package com.yc.pratice.office.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	private static final String _R_N = "\r\n";
	private static final int _1024 = 1024;
	public static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static Properties loadProperties(String propertiesFilePath) {
		File propertiesFile = new File(propertiesFilePath);

		return loadProperties(propertiesFile);
	}

	public static Properties loadProperties(File propertiesFile) {
		FileInputStream fileInputStream = null;
		String filePath = null;
		try {

			if (propertiesFile == null) {
				logger.error("properties file is null.");
				return new Properties();
			}
			filePath = propertiesFile.getAbsolutePath();
			if (!propertiesFile.exists()) {

				logger.error("properties file not exists.filePath=" + filePath);
				return null;
			}
			if (!propertiesFile.isFile()) {

				logger.error("properties file not exists.filePath=" + filePath);
				return null;
			}
			fileInputStream = new FileInputStream(propertiesFile);
			Properties properties = new Properties();

			properties.load(fileInputStream);

			return properties;

		} catch (Exception e) {
			logger.error("properties load Exception.filePath=" + filePath, e);

			return null;
		} finally {
			if (fileInputStream != null) {
				try {

					fileInputStream.close();
				} catch (IOException e) {
					logger.error("fileInputStream close Exception.filePath=" + filePath);
				}
			}

		}

	}

	public static void storeProperties(Properties properties, File targetFile) {

		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(targetFile);
			properties.store(fileOutputStream, "");
		} catch (Exception e) {
			logger.error("storeProperties Exception.filePath=" + targetFile);
		} finally {

			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				logger.error("fileInputStream close Exception.filePath=" + targetFile);
			}

		}
	}

	public static void writeFile(String fileContent, File targetFile, String fileContentEncoder) {

		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(targetFile);
			byte[] byteArray = fileContent.getBytes(fileContentEncoder);
			fileOutputStream.write(byteArray);
		} catch (Exception e) {
			logger.error("storeProperties Exception.filePath=" + targetFile);
		} finally {

			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				logger.error("fileInputStream close Exception.filePath=" + targetFile);
			}

		}
	}

	public static void writeFile(String fileContent, File targetFile) {

		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(targetFile);
			byte[] byteArray = fileContent.getBytes("GBK");
			fileOutputStream.write(byteArray);
		} catch (Exception e) {
			logger.error("storeProperties Exception.filePath=" + targetFile);
		} finally {

			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				logger.error("fileInputStream close Exception.filePath=" + targetFile);
			}

		}
	}

	public static byte[] readFileToBytes(File sourceFile) {
		if (sourceFile == null) {
			return null;
		}
		String absolutePath = sourceFile.getAbsolutePath();
		InputStream is = null;
		ByteArrayOutputStream out = null;
		byte[] returnBytes = null;
		try {
			out = new ByteArrayOutputStream();
			is = new FileInputStream(sourceFile);// pathStr 文件路径
			byte[] b = new byte[_1024];
			int n;
			while ((n = is.read(b)) != -1) {
				out.write(b, 0, n);
			}
			returnBytes = out.toByteArray();
		} catch (Exception e) {
			logger.error("Read File To Bytes Error! ,absolutePath:" + absolutePath, e);
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					logger.error("readFileToBytes InputStream close Exception", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error("readFileToBytes ByteArrayOutputStream close Exception", e);
				}
			}
		}
		return returnBytes;
	}

	public static StringBuffer readFileByLine(File sourceFile, String charsetName) {
		if (sourceFile == null) {
			return null;
		}

		if (!sourceFile.exists()) {

			return null;
		}
		String absolutePath = sourceFile.getAbsolutePath();
		FileInputStream fileInputStream = null;
		InputStreamReader insReader = null;

		BufferedReader bufReader = null;
		try {

			fileInputStream = new FileInputStream(sourceFile);
			insReader = new InputStreamReader(fileInputStream, charsetName);
			bufReader = new BufferedReader(insReader);
			StringBuffer lines = new StringBuffer();
			String line = null;
			while ((line = bufReader.readLine()) != null) {
				lines.append(line).append(_R_N);
			}

			return lines;

		} catch (Exception e) {
			logger.error("readFileByLine Error! ,absolutePath:" + absolutePath, e);
			return null;
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (Exception e) {
					logger.warn("bufReader.close Exception! ,absolutePath:" + absolutePath, e);
				}
			}
			if (insReader != null) {
				try {
					insReader.close();
				} catch (Exception e) {
					logger.warn("insReader.close Exception! ,absolutePath:" + absolutePath, e);
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					logger.warn("fileInputStream.close Exception! ,absolutePath:" + absolutePath, e);
				}
			}

		}
	}

	public static void deleteDir(final File file) {
		deleteFile(file);
	}

	public static void deleteFile(final File file) {
		String dirPath = null;
		try {

			if (file == null) {
				logger.warn("deleteFile,file is null.");
				return;
			}

			dirPath = file.getAbsolutePath();
			if (!file.exists()) {
				logger.info("deleteFile,file not esists.path=" + file.getPath());
				return;
			}

			if (!file.isDirectory()) {
				boolean flag = file.delete();
				if (flag) {
					logger.info("delete file.path=" + dirPath);
				} else {
					logger.error("delete file fail.path=" + dirPath);
				}

				return;
			}
			logger.info("deleteDir begin.path=" + dirPath);
			File flist[] = file.listFiles();
			for (int i = 0; i < flist.length; i++) {
				File temp_file = flist[i];
				String temp_fileName = temp_file.getName();
				if (temp_file.isDirectory()) {
					deleteFile(flist[i]);
				} else {
					boolean deleteFlag = temp_file.delete();
					if (deleteFlag) {
						logger.info("delete file.deleteFlag=" + deleteFlag + ",dirPath=" + dirPath + ",fileName=" + temp_fileName);
					} else {
						logger.error("delete file.deleteFlag=" + deleteFlag + ",dirPath=" + dirPath + ",fileName=" + temp_fileName);
					}
				}
			}
			logger.info("deleteDir end.path=" + dirPath);
			flist = null;

			boolean deleteFlag = file.delete();
			if (!deleteFlag) {

				String[] fileList = file.list();
				List<String> fileNameList = new ArrayList<String>();
				for (String fileName : fileList) {
					fileNameList.add(fileName);
				}

				logger.error("delete Dir Fail.path=" + dirPath + ",dirFileListSize=" + fileNameList.toString());
			}
		} catch (Exception e) {
			logger.error("delete Dir Exception.path=" + dirPath, e);

		}
	}

	public static String getCharset(File file) {
		String charset = "GBK"; // 默认编码
		byte[] first3Bytes = new byte[3];
		BufferedInputStream bis = null;
		String filePath = null;
		try {
			if (file == null) {
				return null;
			}
			if (!file.exists()) {
				return null;
			}

			filePath = file.getAbsolutePath();
			boolean checked = false;
			bis = new BufferedInputStream(new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					// 单独出现BF以下的，也算是GBK
					if (0x80 <= read && read <= 0xBF)
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF)// 双字节 (0xC0 - 0xDF)
							// (0x80 -
							// 0xBF),也可能在GB编码内
							continue;
						else
							break;
						// 也有可能出错，但是几率较小
					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
				logger.warn(loc + " " + Integer.toHexString(read));

			}

		} catch (Exception e) {

			logger.error("fileInputStream.close Exception! ,absolutePath:" + filePath, e);

		} finally {
			try {
				if (bis != null) {
					bis.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return charset;
	}

	public static void zipByStream(final File sourceFile, final File targetZipFile) throws Exception {

		OutputStream os = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		try {
			if (targetZipFile.exists()) {
				targetZipFile.delete();
			}
			os = new FileOutputStream(targetZipFile);

			bos = new BufferedOutputStream(os);

			zos = new ZipOutputStream(bos);

			zipFile(zos, sourceFile, sourceFile.getName());

		} catch (Exception e) {

			throw new Exception("sourceFile:" + sourceFile.getPath() + ",targetZipFile:" + targetZipFile.getPath(), e);

		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (Exception e) {
					logger.error("ZipOutputStream close Exception", e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					logger.error("BufferedOutputStream close Exception", e);
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error("FileOutputStream close Exception", e);
				}
			}
		}

	}

	private static void zipFile(ZipOutputStream out, File f, String base) {

		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {

				try {
					out.putNextEntry(new ZipEntry(base + "/"));
				} catch (IOException e) {
					logger.error("putNextEntry  Exdeption.soruceFile:" + f.getPath(), e);
				}
				// 创建zip压缩进入点base
			}
			for (int i = 0; i < fl.length; i++) {
				zipFile(out, fl[i], base + "/" + fl[i].getName()); // 递归遍历子文件夹
			}

		} else {
			// String pathName = f.getPath().substring(base.length() + 1);

			FileInputStream in = null;
			BufferedInputStream bi = null;
			try {
				out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
				in = new FileInputStream(f);
				bi = new BufferedInputStream(in);
				int readIndex;
				byte[] buf = new byte[1024 * 1024];
				while ((readIndex = bi.read(buf)) != -1) {
					out.write(buf, 0, readIndex); // 将字节流写入当前zip目录
				}
			} catch (Exception e) {
				logger.error("zipFile  Exdeption.soruceFile:" + f.getPath(), e);
			} finally {
				if (bi != null) {
					try {
						bi.close();
					} catch (Exception e) {
						logger.error("BufferedInputStream close  Exdeption.soruceFile:" + f.getPath(), e);
					}

				}
				if (in != null) {
					try {
						in.close(); // 输入流关闭
					} catch (Exception e) {
						logger.error("FileInputStream close  Exdeption.soruceFile:" + f.getPath(), e);
					}
				}
			}

		}

	}

	public static File unzip(File sourceFile, File targetDir) throws FileUtilException {

		if (sourceFile == null) {
			logger.error("sourceFile is null.targetHome=" + targetDir);
			throw new FileUtilException("sourceFile is null.");
		}
		String absolutePath = sourceFile.getAbsolutePath();
		if (!sourceFile.exists()) {

			logger.error("sourceFile not exists.zipFilePath=" + absolutePath + ",targetHome=" + targetDir);
			throw new FileUtilException("sourceFile not exists.zipFilePath=" + absolutePath + ",targetHome=" + targetDir);
		}

		if (sourceFile.isDirectory()) {
			logger.warn("sourceFile is directory.absolutePath=" + absolutePath);
			return sourceFile;
		}
		ZipFile zipFile = null;

		try {

			String fileCharset = getCharset(sourceFile);
			zipFile = new ZipFile(sourceFile, Charset.forName(fileCharset));
			String name = zipFile.getName();
			logger.info("zipFileName=" + name + ",zipFilePath=" + absolutePath + ",fileCharset=" + fileCharset);

			Enumeration<?> en = zipFile.entries();

			String fristZipEntryName = null;
			while (en.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) en.nextElement();
				String zipEntryName = zipEntry.getName();
				if (fristZipEntryName == null || fristZipEntryName.length() == 0) {
					fristZipEntryName = zipEntryName;
				}
				logger.info("zipEntryName=" + zipEntryName + ",zipFilePath=" + absolutePath);
				if (!zipEntry.isDirectory()) {

					unzipWriteFile(zipFile, zipEntry, targetDir);

				} else {
					File temp_targetDir = new File(targetDir, zipEntryName);
					temp_targetDir.mkdirs();
				}
			}
			File fristZipEntryFile = new File(fristZipEntryName);

			File parentFile = null;
			while (fristZipEntryFile != null) {
				parentFile = fristZipEntryFile.getParentFile();
				if (parentFile != null) {
					fristZipEntryFile = parentFile;
				} else {
					break;
				}
			}
			File return_file = new File(targetDir.getAbsoluteFile(), fristZipEntryFile.getName());

			logger.info("unzip targetDir=" + return_file.getPath());
			return return_file;

		} catch (Exception e) {
			logger.error("unzip Exception.zipFilePath=" + absolutePath, e);
			throw new FileUtilException("unzip Exception.zipFilePath=" + absolutePath, e);
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					logger.error("zipFile close Exception.zipFilePath=" + absolutePath, e);
				}
			}

		}
	}

	private static void unzipWriteFile(ZipFile zipFile, ZipEntry zipEntry, File targetDirectory) {
		FileOutputStream outputStream = null;
		InputStream inputStream = null;
		String newFilePath = null;
		try {
			inputStream = zipFile.getInputStream(zipEntry);
			File newFile = new File(targetDirectory, zipEntry.getName());
			File parentFile = newFile.getParentFile();
			parentFile.mkdirs();
			logger.info("unzipWriteFile,zipEntryName=" + zipEntry.getName() + ",zipEntrySize=" + zipEntry.getSize());

			outputStream = new FileOutputStream(newFile);
			int len = 0;
			byte bufer[] = new byte[1024];
			while (-1 != (len = inputStream.read(bufer))) {
				outputStream.write(bufer, 0, len);
			}

		} catch (Exception e) {
			logger.error("unzip file outputStream write Exception.newFilePath=" + newFilePath, e);
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.error("unzip outputStream flush Exception.newFilePath=" + newFilePath, e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.flush();
				} catch (Exception e) {
					logger.error("unzip outputStream flush Exception.newFilePath=" + newFilePath, e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					logger.error("unzip outputStream close Exception.outputDirectory=" + targetDirectory, e);
				}
			}

		}

	}

	public static void writeFile(InputStream inputStream, OutputStream outputStream, String desc) {

		try {

			byte[] b = new byte[_1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);

			}

			outputStream.flush();
		} catch (Exception e) {
			logger.error("Read File To Bytes Error! ,desc:" + desc, e);
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.error("readFileToBytes InputStream close Exception", e);
				}
			}

			if (outputStream != null) {
				try {
					outputStream.flush();
				} catch (Exception e) {
					logger.error("readFileToBytes fileOutputStream flush Exception", e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					logger.error("readFileToBytes fileOutputStream close Exception", e);
				}
			}
		}

	}

	/**
	 * 将单个文件进行gzip的方式压缩，返回压缩文件的绝对路径
	 * 
	 * @return
	 */
	public static void gzip(File sourceFile, final File gzipFile) {
		BufferedReader in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedReader(new FileReader(sourceFile));
			out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(gzipFile)));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
		} catch (Exception e) {
			logger.error("gzip Exception.", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("gzip IOException.", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("gzip IOException.", e);
				}
			}

		}
	}

	public static String getFilePath(String fileName) {

		URL url = FileUtil.class.getClassLoader().getResource(fileName);
		if (url == null) {
			return null;
		} else {
			return url.getFile();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
