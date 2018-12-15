package com.lov.java8.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.lov.java8.pojo.Employee;
import com.lov.java8.pojo.Employee.Status;

/**
 * 
 * ����Stream�������������������ڲ�������Դ�����ϣ�����ȣ������ɵ�Ԫ������
 * 
 * ����Stream
 * �м����
 * ��ֹ����
 * 
 * @author P1314023
 *
 */
public class StreamAPITest_1 {

	
	List<Employee> employees = Arrays.asList(
				new Employee("����",18,9999.99,Status.FREE),
				new Employee("����",58,5555.55,Status.BUSY),
				new Employee("����",26,3333.33,Status.VOCATION),
				new Employee("����",36,6666.66,Status.FREE),
				new Employee("����",12,8888.88,Status.BUSY),
				new Employee("����",12,8888.88,Status.BUSY),
				new Employee("����",12,8888.88,Status.BUSY)
			);
	
	
	/**
	 *�ռ�
	 *collect��������ת��Ϊ������ʽ������һ��Collector�ӿڵ�ʵ�������ڸ�Stream��Ԫ�������ܵķ���
	 * 
	 */
	@Test
	public void test8(){
		
		List<String> list = employees.stream()
									.map(Employee::getName)
									.collect(Collectors.toList());
		System.out.println(list);
		
		Set<String> set = employees.stream()
								.map(Employee::getName)
								.collect(Collectors.toSet());
		System.out.println(set);
		
		HashSet<String> hashSet = employees.stream()
									.map(Employee::getName)
									.collect(Collectors.toCollection(HashSet::new));
		System.out.println(hashSet);
		
	}
	@Test
	public void test9(){
		//����
		Long count  = employees.stream()
				.collect(Collectors.counting());
		//ƽ��ֵ
		Double avg = employees.stream()
				.collect(Collectors.averagingDouble(Employee::getSalary));
		
		//�ܺ�
		Double sum = employees.stream()
					.collect(Collectors.summingDouble(Employee::getSalary));
		
		//���ֵ
		Optional<Employee> optional = employees.stream()
				.collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary() ,e2.getSalary())));
		
		//����
		Map<Status, List<Employee>> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
		
		Map<Status, Map<String, List<Employee>>> map2  = employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e->{
					if (((Employee) e).getAge() <= 35) {
						return "����";
					}else if (((Employee) e).getAge() <= 50) {
						return "����";
					}else {
						return "����";
					}
				})));
		System.out.println(map2);
		
		//����
		Map<Boolean	, List<Employee>> map3 = employees.stream()
				.collect(Collectors.partitioningBy(e->e.getSalary() > 8000));
		System.out.println(map3);
		
		DoubleSummaryStatistics	dss = employees.stream()	
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getSum());
		
		String	string = employees.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(",","---","---"));
		System.out.println(string);
		
	}
	
	/**
	 * ������
	 */
	@Test
	public void test10(){
		Long long1 = LongStream.rangeClosed(0, 1000000000L)
				.parallel()//�ײ���Fork/Join��ܣ�������ȡģʽ
				.reduce(0,Long::sum);
		System.out.println(long1);
		
	}
	
	
	/**
	 * ��Լ
	 * reduce(T identity,BinaryOperator)/reduce(BinaryOperator)
	 * ���Խ�����Ԫ�ط�������������õ�һ��ֵ
	 * 
	 */
	@Test
	public void test7(){
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
		Integer sum = list.stream()
						.reduce(0, (x,y)->x+y);
		System.out.println(sum);
			
		Optional<Double> optional = employees.stream()
				.map(Employee::getSalary)
				.reduce(Double::sum);
		System.out.println(optional.get());
		
	}
	
	/**
	 * ��ֹ����
	 * ������ƥ��
	 * allMatch��������Ƿ�ƥ������Ԫ��
	 * anyMatch��������Ƿ�����ƥ��һ��Ԫ��
	 * noneMatch��������Ƿ�û��ƥ������Ԫ��
	 * findFirst��������ǰһ��Ԫ��
	 * findAny�������ص�ǰ���е�����Ԫ��
	 * count������������Ԫ�صĸ���
	 * max�����������е����ֵ
	 * min�����������е���Сֵ
	 * 
	 * 
	 */
	@Test
	public void test6(){
		
		boolean b1 = employees.stream()
						.allMatch(e->e.getStatus().equals(Status.BUSY));
		
		boolean b12 = employees.stream()
						.anyMatch(e->e.getStatus().equals(Status.BUSY));
		
		boolean b3 = employees.stream()
						.noneMatch(e->e.getStatus().equals(Status.BUSY));
		
		Optional<Employee> optional = employees.stream()
					.sorted((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()))
					.findFirst();
		System.out.println(optional.get());
		
		System.out.println("-----------------------------");
		Optional<Employee> optional1 = employees.stream()
				.filter(e->e.getStatus().equals(Status.FREE))
				.findAny();
		System.out.println(optional1.get());
		
		System.out.println("-----------------------------");
		Long count = employees.stream().count();
		System.out.println(count);
		
		System.out.println("-----------------------------");
		Optional<Employee> optional3 = employees.stream()
				.max((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(optional3.get());	
		
		System.out.println("-----------------------------");
		Optional<Double> optional4 = employees.stream()
				.map(Employee::getSalary)
				.min(Double::compare);
		System.out.println(optional4);
		
	}
	
	
	/**
	 *����
	 *sorted������Ȼ����
	 *sorted(Comparator com) ������������
	 * 
	 */
	@Test
	public void test5(){
		
		List<String> list = Arrays.asList("dd","ee","aa","bb","cc");
		
		list.stream()
			.sorted()
			.forEach(System.out::println);
		
		employees.stream()
				.sorted((e1,e2)->{
					if (e1.getAge()==(e2.getAge())) {
;						return e1.getName().compareTo(e2.getName());
					}else{
						return ((Integer)e1.getAge()).compareTo(e2.getAge());
					}
				})
				.forEach(System.out::println);
	}
	
	/*
	 * ӳ��
	 * map��������lambda����Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�
	 *		������ӳ���һ���µ�Ԫ�ء�
	 *flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ���� 
	 * 
	 */
	
	@Test
	public void tes4(){
		
		List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
		
		list.stream()
			.map(String::toUpperCase)
			.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		
		employees.stream()
				.map(Employee::getName)
				.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		
/*		Stream<Stream<Character>> stream = list.stream()
			.map(StreamAPITest_1::filterCharacter);
		
		stream.forEach(sm->{
			sm.forEach(System.out::print);
		});*/
		//�����뼯���� add(Object obj) ��  addAll(Collection coll)
		list.stream()
			.flatMap(StreamAPITest_1::filterCharacter)
			.forEach(System.out::print);
		
	}
	
	public static Stream<Character> filterCharacter(String string){
		
		List<Character> list = new ArrayList<>();
		
		for (Character character : string.toCharArray()) {
			list.add(character);
		}
		
		return list.stream();
	}
	
	/**
	 * 
	 * �м������
		 * ɸѡ����Ƭ
		 * filter��������lambda���������ų�ĳЩԪ��
		 * limit�����ض�����ʹ��Ԫ�ز�����������ֵ
		 * skip��������Ԫ�أ�����һ������ǰn��Ԫ�ص����������� Ԫ�ز���n�����򷵻�һ����������limit����
		 * distinct����ɸѡ��ͨ����������Ԫ�ص�hashcode()��equals()ȥ���ظ�Ԫ��
	 * 
	 */
	@Test
	public void test2(){
		
		//�м����������ִ���κβ���
		Stream<Employee> stream = employees.stream()
								.filter(e->{
									System.out.println("Stream API �м����");
									return e.getAge() > 35;
								});
		//��ֹ������һ����ִ��ȫ�����ݣ�����������ֵ��
		stream.forEach(System.out::println);
		
	}
	
	@Test
	public void test3(){
		
		employees.stream()
				.filter(e->{
					System.out.println("��·");
					return e.getSalary() > 5000;
				})
				.limit(2)
				.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		
		employees.stream()
				.filter(e-> e.getSalary() > 5000)
				.skip(2)
				.distinct()//Ҫ����hashcode��equals
				.forEach(System.out::println);

	
	}
	
	/**
	 * ����Stream
	 * 1.����ͨ��Collectionϵ�м����ṩ��stream()��parallelStream()
	 * 2.ͨ��Arrays �еľ�̬����stream()��ȡ������
	 * 3.ͨ��Stream���еľ�̬���� of()
	 * 4.����������
	 * 
	 */
	@Test
	public void test1(){
		
		//1.����ͨ��Collectionϵ�м����ṩ��stream()��parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		
		//2.ͨ��Arrays �еľ�̬����stream()��ȡ������
		Employee[] employees = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(employees);
		
		//3.ͨ��Stream���еľ�̬���� of()
		Stream<String> stream3 = Stream.of("aa","bb","cc");
		
		//4.����������
		//����
		Stream<Integer> stream4 = Stream.iterate(0, (x)->x + 2);
		stream4.forEach(System.out::println);
		stream4.limit(10).forEach(System.out::println);
		
		//����
		Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
		
	}
	
	
}
