package com.lov.java8.interface_default;

public class DefaultFunctionTest extends MyClass implements Myfun {
	
	/**
	 * ��ʵ�ֽӿ���̳���������ͬ���������еķ������ȣ�������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		DefaultFunctionTest dTest = new DefaultFunctionTest();
		System.out.println(dTest.getName());
		
		Myfun.show();
		
	}
	
}
