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
		//set��map����һ��
		//TreeMap �Ǹ߲������������
		Map<String, String> map = new ConcurrentSkipListMap<>();//�߲���������
//		Map<String, String> map = new ConcurrentHashMap<>();//�ֿ���������Զ����ͬ�������������Ը�
//		Map<String, String> map = new Hashtable<>();//����������ֱ�Ӽ������Ͳ���
		
		Random random = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);//������˨
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				//ÿ���߳���map�м���10000�����ֵ��
				for (int j = 0; j < 10000; j++) {
					map.put("a"+random.nextInt(100000), "a"+random.nextInt(100000));
				}
				latch.countDown();//��˨ÿ�μ�һ
			});
		}
		
		Arrays.asList(threads).forEach(t->t.start());
		
		try {
			latch.await();//���̵߳ȴ���֪����˨�ͷ�
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		
	}
	
	
	
}
