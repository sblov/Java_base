package com.lov.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;


import com.lov.base.Child;

public class baseTest {
	private static Logger logger = Logger.getLogger(baseTest.class);

	@Test
	public void test() {
		//this - super
		new Child();
		
		//当前时间
		System.out.println(Calendar.getInstance().getTime());
	} 
	
	/**
	 * 时间格式
	 */
	@Test
	public void dateFormat() {
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Date date = new Date();
		System.out.println(sFormat.format(date));
		
		try {
			System.out.println(sFormat.parse("2014-07-11 12:11:09"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tranString() {
		System.out.println();
		Properties initProp = new Properties(System.getProperties());
		System.out.println("当前系统编码:" + initProp.getProperty("file.encoding"));
		System.out.println("当前系统语言:" + initProp.getProperty("user.language"));
		//输出所有支持的编码集
		System.out.println(Charset.availableCharsets());
		
		try {
			String a=new String("中".getBytes("gbk"));
			System.out.println(a);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
