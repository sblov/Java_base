package com.lov.java8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//������Ŀ��
@Target({ElementType.TYPE,ElementType.TYPE_PARAMETER})
//ע����������
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyAnnotaions {

	MyAnnotation[] value();
	
}
