package com.lov.java8.annotation;

import org.junit.Test;

/**
 * 重复注解与类型注解
 * @author P1314023
 *
 */
@MyAnnotation("hello")
@MyAnnotation("world")
public class AnnotationTest {

	
	
	@Test
	public void test(){
		
		Class<AnnotationTest> class1 = AnnotationTest.class;
	
		MyAnnotation[] mya = class1.getAnnotationsByType(MyAnnotation.class);
		
		for (MyAnnotation myAnnotation : mya) {
			System.out.println(myAnnotation.value());
		}
		
	}
	
	public void show(@MyAnnotation("aaa") String string){
		
	}
	
}
