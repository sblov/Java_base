package com.lov.thread.thread_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//ThreadTest�Ż�
public class CountDownLatchTest {

volatile List list = new ArrayList();
	
	public void add(Object object){
		list.add(object);
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		ThreadTest thread_11  = new ThreadTest();
		
		CountDownLatch countDownLatch = new CountDownLatch(1);//��ֵ��Ϊ0����˨���
		
		new Thread(()->{
			
				System.out.println("t2 start");
				if(thread_11.size()!=5){
					try {
						countDownLatch.await();//��wait����Ҫ������������˨���ȴ���
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				System.out.println("t2 end");
			
		},"t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(()->{
			System.out.println("t1 start");
			
				for (int i = 0; i <10; i++) {
					thread_11.add(new Object());
					System.out.println("add"+i);
					
					if (thread_11.size()== 5) {
						countDownLatch.countDown();//��˨ֵ��һ��Ϊ0��t2�߳�ִ�У���Ϊû���������󣬸��̼߳���ִ��
											}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
		}).start();
	}
	
}
