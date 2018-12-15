package com.lov.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.lov.java8.pojo.Employee;
import com.lov.java8.pojo.Employee.Status;

/**
 * Optional类可以很好的避免空指针
 * 
 * @author P1314023
 *
 */
public class OptionalTest {

	/**
	 * 	Optional.of(T t):创建一个Optional实例
		Optional.empty():创建一个空的Optional实例
		Optional.ofNullable(T t):若t不为null，创建Optional实例，否则创建空实例
		isPresent():判断是否包含值
		orElse(T t):如果调用对象包含值，返回该值，否则返回 t
		orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
		map(Function f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
		flatMap(Function mapper):与map类似，要求返回值必须是Optional
		
	 * 
	 * 
	 */
	@Test
	public void test(){
		
		//将空指针异常控制在该处
		//Optional<Employee> optional  = Optional.of(null);//异常
		//Optional<Employee> optional  = Optional.empty();//空的Optional
		//Optional<Employee> optional  = Optional.of(new Employee());
		Optional<Employee> optional  = Optional.ofNullable(null);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
		Employee employee  = optional.orElse(new Employee("lov",18,8888.88,Status.BUSY));
		System.out.println(employee);
		
		Employee employee2	= optional.orElseGet(()->new Employee());
		System.out.println(employee2);
		
		Optional<String> sOptional = optional.map(e->e.getName());
		System.out.println(sOptional.get());

		//返回值必须是Optional封装
		Optional<String> sOptional2 = optional.flatMap(e->Optional.of(e.getName()));
		System.out.println(sOptional2.get());
	
	}
	
}
