package com.lov.iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//�ֽ�������
public class CopyTest {

	public static void main(String[] args) throws IOException {
		//�����ļ�
		File orgin = new File("C:/Users/P1314023/Pictures/Capture.png");
		File copy = new File("C:/Users/P1314023/Pictures/Capture-c.png");
		if (!copy.exists()) {
			copy.createNewFile();
		}
		//������
		InputStream iStream = new FileInputStream(orgin);
		OutputStream oStream = new FileOutputStream(copy);
		
		byte[] flush = new byte[100];
		int len = 0;
		//��д
		while ((len = iStream.read(flush)) !=-1 ) {
			oStream.write(flush, 0, len);
		}
		
		oStream.flush();
		//�ر���
		oStream.close();
		iStream.close();
		
	}
	
}
