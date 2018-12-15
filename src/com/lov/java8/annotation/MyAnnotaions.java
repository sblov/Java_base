package com.lov.java8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//可修饰目标
@Target({ElementType.TYPE,ElementType.TYPE_PARAMETER})
//注解生命周期
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyAnnotaions {

	MyAnnotation[] value();
	
}
