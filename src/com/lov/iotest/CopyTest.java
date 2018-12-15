package com.lov.iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//字节流复制
public class CopyTest {

	public static void main(String[] args) throws IOException {
		//关联文件
		File orgin = new File("C:/Users/P1314023/Pictures/Capture.png");
		File copy = new File("C:/Users/P1314023/Pictures/Capture-c.png");
		if (!copy.exists()) {
			copy.createNewFile();
		}
		//创建流
		InputStream iStream = new FileInputStream(orgin);
		OutputStream oStream = new FileOutputStream(copy);
		
		byte[] flush = new byte[100];
		int len = 0;
		//读写
		while ((len = iStream.read(flush)) !=-1 ) {
			oStream.write(flush, 0, len);
		}
		
		oStream.flush();
		//关闭流
		oStream.close();
		iStream.close();
		
	}
	
}
