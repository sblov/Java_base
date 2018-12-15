package com.lov.java8.time;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TimeTest {

	
	/**
	 * ZoneDate,ZoneTiem,ZoneDateTime : ʱ��
	 */
	@Test
	public void test5(){
		
		/*Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		for (String string : availableZoneIds) {
			System.out.println(string);
		}*/
		Instant instant = Instant.now();//Ĭ��UTCʱ��
		System.out.println(instant);
		
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
		System.out.println(ldt);

		LocalDateTime ldt2 = LocalDateTime.now();
		System.out.println(ldt2.atZone(ZoneId.of("Europe/Monaco")));//׷����ʾ��ʱ����UTCʱ��ʱ��
		
		
		
	}
	
	/**
	 * DateTimeFormatter: ��ʽ��ʱ��/����
	 * 
	 */
	@Test
	public void test4(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(ldt.format(dtf));
		System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		
	}
	
	/**
	 * ʱ�������:TemporalAdjuster
	 * 
	 */
	@Test
	public void test3(){
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);
		
		System.out.println(ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
		
		//�Զ���
		System.out.println("next WorkDay - "+ldt.with((l)->{
			LocalDateTime ldt3 = (LocalDateTime)l;
			
			DayOfWeek dow = ldt3.getDayOfWeek();
			
			if (dow.equals(DayOfWeek.FRIDAY)) {
				return ldt3.plusDays(3);
			}else if (dow.equals(DayOfWeek.SATURDAY)) {
				return ldt3.plusDays(2);
			}else {
				return ldt3.plusDays(1);
			}
 		}));
		
	}
	
	@Test
	public void test2() throws InterruptedException{
		
		//LocalDate,LocalTime,LocalDateTime ���ǲ��ɱ��,����String
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		System.out.println(LocalDateTime.of(2018, 9, 10, 10, 12));
		System.out.println(ldt.plusYears(12));
		System.out.println(ldt.minusYears(2));
		System.out.println(ldt.getYear());
		System.out.println(ldt.getDayOfMonth());
		
		System.out.println("---------------------");
		
		//Instant :ʱ�������UNIXԪ��(1970.1.1 00:00:00)��ĳ��ʱ��֮��ĺ���ֵ
		Instant instant = Instant.now();//Ĭ��UTCʱ��
		System.out.println(instant);
		//ʱ��ƫ����
		System.out.println(instant.atOffset(ZoneOffset.ofHours(8)));
		System.out.println(instant.toEpochMilli());
		System.out.println(Instant.ofEpochSecond(60));
		
		System.out.println(instant);
		
		System.out.println("---------------------");
		
		//Duration :��������ʱ��֮��ļ��
		//Period :������������֮��ļ��
		Instant instant2 = Instant.now();
		TimeUnit.SECONDS.sleep(2);
		Instant instant3 = Instant.now();
		
		Duration duration = Duration.between(instant2, instant3);
		System.out.println(duration.toMillis());
		
		System.out.println("---------------------");
		
		LocalTime lTime = LocalTime.now();
		TimeUnit.SECONDS.sleep(2);
		LocalTime lTime1 = LocalTime.now();
		System.out.println(Duration.between(lTime, lTime1).toMillis());
		
		System.out.println("---------------------");
		
		LocalDate ltd = LocalDate.of(2016, 9, 10);
		LocalDate ltd1 = LocalDate.now();
		System.out.println(Period.between(ltd, ltd1));//P1Y11M25D 1��11��25��
	}
	
	
	/**
	 * java8��ʱ��api���̰߳�ȫ
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test1() throws InterruptedException, ExecutionException{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		
	
		Callable<LocalDate>	 task  = ()->LocalDate.parse("20180910", dtf);
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<LocalDate>> list  = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}
		
		for (Future<LocalDate> future : list) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
		
	}
	
	
	
	/**
	 * 
	 * ��ͳʱ����߳�����
	 * @throws Exception
	 * @throws ExecutionException
	 */
	@Test
	public void test() throws Exception, ExecutionException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//���̰߳�ȫ����
//		Callable<Date>	 task  = ()->sdf.parse("20180910");
		Callable<Date>	 task  = ()->DateFormatThreadLocal.convert("20180910");
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<Date>> list  = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}
		
		for (Future<Date> future : list) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	
	}
	
}
