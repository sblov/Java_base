package com.lov.iotest;

import java.io.File;

//alt+shift+j

/**
 * @author P1314023
 *
 */
public class IOTest_1 {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		 * File�е���������
//		 * 1��·���ָ��� ;
//		 * 2��·���ļ��ָ���    \(win)  /(linux)
		System.out.println(File.pathSeparator);// ;
		System.out.println(File.separator);// \
		
		String parenPath = "D:/poject";
		String name = "test.txt";
		//���·��
		File src = new File(parenPath, name);
		src = new File(new File(parenPath), name);
		System.out.println(src.getName());
		System.out.println(src.getPath());
		//����·��
		src = new File("D:/poject/test.txt");
		System.out.println(src.getName());
		System.out.println(src.getPath());
		//��ǰ��Ŀ��·��
		src = new File("test.txt");
		System.out.println(src.getName());
		System.out.println(src.getPath());
		System.out.println(src.getAbsolutePath());
		System.out.println(src.exists());
		
		
	}
	
}
