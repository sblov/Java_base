package com.lov.java8.interface_default;

public class DefaultFunctionTest2 implements Myfun ,Myfun2{
	
	/**
	 * 两个实现接口有相同默认方法，必须实现一个
	 * @param args
	 */
	public static void main(String[] args) {
		
		DefaultFunctionTest2 dTest = new DefaultFunctionTest2();
		System.out.println(dTest.getName());
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Myfun.super.getName();
	}
	
}
