package com.lov.java8.interface_default;

public class DefaultFunctionTest extends MyClass implements Myfun {
	
	/**
	 * 当实现接口与继承类中有相同方法，类中的方法优先，类优先
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		DefaultFunctionTest dTest = new DefaultFunctionTest();
		System.out.println(dTest.getName());
		
		Myfun.show();
		
	}
	
}
