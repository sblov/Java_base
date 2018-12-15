package com.lov.java8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//�������ظ�ע�⣬���ƶ�ע������
@Repeatable(MyAnnotaions.class)
//������Ŀ��
@Target({ElementType.TYPE,ElementType.TYPE_PARAMETER,ElementType.PARAMETER})
//ע����������
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	String value() default "aaa";
	
}
