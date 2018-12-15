package com.lov.thread.thread_3;
//ConcurrentMap

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class SyncContainer_3 {

	
	public static void main(String[] args) {
		//set，map本质一致
		//TreeMap 非高并发，排序插入
		Map<String, String> map = new ConcurrentSkipListMap<>();//高并发并排序
//		Map<String, String> map = new ConcurrentHashMap<>();//分块加锁，可以多个块同步加锁，并发性高
//		Map<String, String> map = new Hashtable<>();//对整个对象直接加锁，低并发
		
		Random random = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);//设置门栓
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				//每个线程往map中加入10000随机键值对
				for (int j = 0; j < 10000; j++) {
					map.put("a"+random.nextInt(100000), "a"+random.nextInt(100000));
				}
				latch.countDown();//门栓每次减一
			});
		}
		
		Arrays.asList(threads).forEach(t->t.start());
		
		try {
			latch.await();//主线程等待，知道门栓释放
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		
	}
	
	
	
}
