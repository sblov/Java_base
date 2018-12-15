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
 * 流（Stream）：是数据渠道，用于操作数据源（集合，数组等）锁生成的元素序列
 * 
 * 创建Stream
 * 中间操作
 * 终止操作
 * 
 * @author P1314023
 *
 */
public class StreamAPITest_1 {

	
	List<Employee> employees = Arrays.asList(
				new Employee("张三",18,9999.99,Status.FREE),
				new Employee("李四",58,5555.55,Status.BUSY),
				new Employee("王五",26,3333.33,Status.VOCATION),
				new Employee("赵六",36,6666.66,Status.FREE),
				new Employee("田七",12,8888.88,Status.BUSY),
				new Employee("田七",12,8888.88,Status.BUSY),
				new Employee("田七",12,8888.88,Status.BUSY)
			);
	
	
	/**
	 *收集
	 *collect——将流转换为其他形式，接收一个Collector接口的实例，用于给Stream中元素做汇总的方法
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
		//总数
		Long count  = employees.stream()
				.collect(Collectors.counting());
		//平均值
		Double avg = employees.stream()
				.collect(Collectors.averagingDouble(Employee::getSalary));
		
		//总和
		Double sum = employees.stream()
					.collect(Collectors.summingDouble(Employee::getSalary));
		
		//最大值
		Optional<Employee> optional = employees.stream()
				.collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary() ,e2.getSalary())));
		
		//分组
		Map<Status, List<Employee>> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
		
		Map<Status, Map<String, List<Employee>>> map2  = employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e->{
					if (((Employee) e).getAge() <= 35) {
						return "青年";
					}else if (((Employee) e).getAge() <= 50) {
						return "中年";
					}else {
						return "老年";
					}
				})));
		System.out.println(map2);
		
		//分区
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
	 * 并行流
	 */
	@Test
	public void test10(){
		Long long1 = LongStream.rangeClosed(0, 1000000000L)
				.parallel()//底层是Fork/Join框架，工作窃取模式
				.reduce(0,Long::sum);
		System.out.println(long1);
		
	}
	
	
	/**
	 * 归约
	 * reduce(T identity,BinaryOperator)/reduce(BinaryOperator)
	 * 可以将流中元素反复结合起来，得到一个值
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
	 * 终止操作
	 * 查找与匹配
	 * allMatch——检查是否匹配所有元素
	 * anyMatch——检查是否至少匹配一个元素
	 * noneMatch——检查是否没有匹配所有元素
	 * findFirst——返回前一个元素
	 * findAny——返回当前流中的任意元素
	 * count——返回流中元素的个数
	 * max——返回流中的最大值
	 * min——返回流中的最小值
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
	 *排序
	 *sorted——自然排序
	 *sorted(Comparator com) ——定制排序
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
	 * 映射
	 * map——接收lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，
	 *		并将其映射成一个新的元素。
	 *flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流 
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
		//类似与集合中 add(Object obj) 与  addAll(Collection coll)
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
	 * 中间操作：
		 * 筛选与切片
		 * filter——接收lambda，从流中排除某些元素
		 * limit——截断流，使其元素不超过给定数值
		 * skip——跳过元素，返回一个跳过前n个元素的流，若流中 元素不足n个，则返回一个空流，与limit互补
		 * distinct——筛选，通过流所生成元素的hashcode()和equals()去除重复元素
	 * 
	 */
	@Test
	public void test2(){
		
		//中间操作，不会执行任何操作
		Stream<Employee> stream = employees.stream()
								.filter(e->{
									System.out.println("Stream API 中间操作");
									return e.getAge() > 35;
								});
		//终止操作，一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
		
	}
	
	@Test
	public void test3(){
		
		employees.stream()
				.filter(e->{
					System.out.println("短路");
					return e.getSalary() > 5000;
				})
				.limit(2)
				.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		
		employees.stream()
				.filter(e-> e.getSalary() > 5000)
				.skip(2)
				.distinct()//要重新hashcode和equals
				.forEach(System.out::println);

	
	}
	
	/**
	 * 创建Stream
	 * 1.可以通过Collection系列集合提供的stream()或parallelStream()
	 * 2.通过Arrays 中的静态方法stream()获取数组流
	 * 3.通过Stream类中的静态方法 of()
	 * 4.创建无限流
	 * 
	 */
	@Test
	public void test1(){
		
		//1.可以通过Collection系列集合提供的stream()或parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		
		//2.通过Arrays 中的静态方法stream()获取数组流
		Employee[] employees = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(employees);
		
		//3.通过Stream类中的静态方法 of()
		Stream<String> stream3 = Stream.of("aa","bb","cc");
		
		//4.创建无限流
		//迭代
		Stream<Integer> stream4 = Stream.iterate(0, (x)->x + 2);
		stream4.forEach(System.out::println);
		stream4.limit(10).forEach(System.out::println);
		
		//生成
		Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
		
	}
	
	
}
