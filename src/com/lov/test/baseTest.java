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
		
		//��ǰʱ��
		System.out.println(Calendar.getInstance().getTime());
	} 
	
	/**
	 * ʱ���ʽ
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
		System.out.println("��ǰϵͳ����:" + initProp.getProperty("file.encoding"));
		System.out.println("��ǰϵͳ����:" + initProp.getProperty("user.language"));
		//�������֧�ֵı��뼯
		System.out.println(Charset.availableCharsets());
		
		try {
			String a=new String("��".getBytes("gbk"));
			System.out.println(a);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
