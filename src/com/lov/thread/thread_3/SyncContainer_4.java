package com.lov.thread.thread_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

//дʱ����CopyOnWrite����������д��ʱ���Ḵ��ԭ�����ݣ���д�������ָ����ӵ����Ƶ������У�ԭ���Ķ���ָ���µ�����
public class SyncContainer_4 {

	public static void main(String[] args) {
		/*һ���߳���׼������ȥд���ݣ�ͻȻ�е���һ���߳�����д�˽��룬���л�������̲߳���֪�����λ���Ѿ�д��
		�����ݣ����������ǻ�ɵɵ��д�����ݣ�������һ���̵߳����ݾͱ������ˡ������һ����� ��һ�߱����Ļ�����
		�����ConcurrentModificationException�쳣*/
		List<String> list =
//				new ArrayList<>();
//				new Vector<>();
				new CopyOnWriteArrayList<>();//����д��
		
//		List<String> list2 = Collections.synchronizedList(list);//����ͬ��arraylist������װ��ͬ������������
		Random random = new Random();
		Thread[] threads = new Thread[100];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				for (int j = 0; j < 1000; j++) {
					list.add("a"+random.nextInt(10000));
				}
			});
		}
		runAndComputeTime(threads);
		System.out.println(list.size());
	}

	static void runAndComputeTime(Thread[] threads) {

		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(t->t.start());
		Arrays.asList(threads).forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
	}
	
}
