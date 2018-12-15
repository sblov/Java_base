package com.lov.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.lov.java8.pojo.Employee;

/**
 * 1.方法引用：若Lambda体中的内容有方法已经实现了，可以使用“方法引用”
 * 
 * 对象::实例方法名
 * 
 * 类::静态方法名
 * 
 * 类::实例方法名
 * 
 * 2.构造器引用：
 * 
 * ClassNam::new
 * 
 * 3.数组引用：
 * 
 * Type[]::new
 * 
 * @author P1314023
 *
 */
public class LambdaTest_1 {
	
	@Test
	public void test5(){
		
		//Function<Integer, String[]> function = (x)->new String[x];
		
		Function<Integer, String[]> function2 = String[]::new;

		System.out.println(function2.apply(10).length);
		
	}
	
	@Test
	public void test4(){
		
		//Supplier<Employee> supplier = ()->new Employee();

		Supplier<Employee> supplier2 = Employee::new;
		
		System.out.println(supplier2.get().getName());
		//构造器根据参数列表选择
		//Function<String, Employee> function = (s)->new Employee(s);
		
		Function<String, Employee> function2= Employee::new;
		
		System.out.println(function2.apply("lov").getName());
	}
	
	@Test
	public void test3(){
		//lambda参数列表中，第一个参数是实例方法调用者，第二个参数为实例方法的参数
		BiPredicate<String, String> bp = (x,y)->x.equals(y);
		System.out.println(bp.test("123", "123"));
		
		BiPredicate<String, String> bp2 = (x,y)->x.equals(y);
		System.out.println(bp2.test("123", "123"));
	}
	
	//类::静态方法名
	@Test
	public void test2(){
		
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		
		Comparator<Integer> com2 = Integer::compare;
		
		System.out.println(com.compare(2, 2));
		System.out.println(com2.compare(1, 2));
	}
	
	
	//对象::实例方法名
	@Test
	public void test1(){
		
		PrintStream pStream = System.out;
		
		Consumer<String> consumer = (x)->pStream.println(x);
		consumer.accept("hello");

		//对象方法的参数列表和返回值和lambda体内要实现的方法的必须一样
		Consumer<String> consumer1 = pStream::println;//System.out::println
		consumer1.accept("hello");
		
		
	}
	
	
}
