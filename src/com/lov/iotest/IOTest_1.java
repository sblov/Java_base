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
//		 * File中的两个常量
//		 * 1、路径分隔符 ;
//		 * 2、路径文件分隔符    \(win)  /(linux)
		System.out.println(File.pathSeparator);// ;
		System.out.println(File.separator);// \
		
		String parenPath = "D:/poject";
		String name = "test.txt";
		//相对路径
		File src = new File(parenPath, name);
		src = new File(new File(parenPath), name);
		System.out.println(src.getName());
		System.out.println(src.getPath());
		//绝对路径
		src = new File("D:/poject/test.txt");
		System.out.println(src.getName());
		System.out.println(src.getPath());
		//当前项目根路径
		src = new File("test.txt");
		System.out.println(src.getName());
		System.out.println(src.getPath());
		System.out.println(src.getAbsolutePath());
		System.out.println(src.exists());
		
		
	}
	
}
