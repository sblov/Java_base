package com.lov.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.lov.java8.pojo.Employee;
import com.lov.java8.pojo.Employee.Status;

/**
 * Optional����Ժܺõı����ָ��
 * 
 * @author P1314023
 *
 */
public class OptionalTest {

	/**
	 * 	Optional.of(T t):����һ��Optionalʵ��
		Optional.empty():����һ���յ�Optionalʵ��
		Optional.ofNullable(T t):��t��Ϊnull������Optionalʵ�������򴴽���ʵ��
		isPresent():�ж��Ƿ����ֵ
		orElse(T t):������ö������ֵ�����ظ�ֵ�����򷵻� t
		orElseGet(Supplier s):������ö������ֵ�����ظ�ֵ�����򷵻�s��ȡ��ֵ
		map(Function f):�����ֵ���䴦�������ش�����Optional�����򷵻�Optional.empty()
		flatMap(Function mapper):��map���ƣ�Ҫ�󷵻�ֵ������Optional
		
	 * 
	 * 
	 */
	@Test
	public void test(){
		
		//����ָ���쳣�����ڸô�
		//Optional<Employee> optional  = Optional.of(null);//�쳣
		//Optional<Employee> optional  = Optional.empty();//�յ�Optional
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

		//����ֵ������Optional��װ
		Optional<String> sOptional2 = optional.flatMap(e->Optional.of(e.getName()));
		System.out.println(sOptional2.get());
	
	}
	
}
