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
 * 1.�������ã���Lambda���е������з����Ѿ�ʵ���ˣ�����ʹ�á��������á�
 * 
 * ����::ʵ��������
 * 
 * ��::��̬������
 * 
 * ��::ʵ��������
 * 
 * 2.���������ã�
 * 
 * ClassNam::new
 * 
 * 3.�������ã�
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
		//���������ݲ����б�ѡ��
		//Function<String, Employee> function = (s)->new Employee(s);
		
		Function<String, Employee> function2= Employee::new;
		
		System.out.println(function2.apply("lov").getName());
	}
	
	@Test
	public void test3(){
		//lambda�����б��У���һ��������ʵ�����������ߣ��ڶ�������Ϊʵ�������Ĳ���
		BiPredicate<String, String> bp = (x,y)->x.equals(y);
		System.out.println(bp.test("123", "123"));
		
		BiPredicate<String, String> bp2 = (x,y)->x.equals(y);
		System.out.println(bp2.test("123", "123"));
	}
	
	//��::��̬������
	@Test
	public void test2(){
		
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		
		Comparator<Integer> com2 = Integer::compare;
		
		System.out.println(com.compare(2, 2));
		System.out.println(com2.compare(1, 2));
	}
	
	
	//����::ʵ��������
	@Test
	public void test1(){
		
		PrintStream pStream = System.out;
		
		Consumer<String> consumer = (x)->pStream.println(x);
		consumer.accept("hello");

		//���󷽷��Ĳ����б�ͷ���ֵ��lambda����Ҫʵ�ֵķ����ı���һ��
		Consumer<String> consumer1 = pStream::println;//System.out::println
		consumer1.accept("hello");
		
		
	}
	
	
}
