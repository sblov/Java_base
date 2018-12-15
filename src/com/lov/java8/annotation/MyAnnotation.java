package com.lov.java8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//声明可重复注解，并制定注解容器
@Repeatable(MyAnnotaions.class)
//可修饰目标
@Target({ElementType.TYPE,ElementType.TYPE_PARAMETER,ElementType.PARAMETER})
//注解生命周期
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	String value() default "aaa";
	
}
