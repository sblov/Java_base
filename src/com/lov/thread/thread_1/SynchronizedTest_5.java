package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

//���̷߳����쳣ʱ�����Զ��ͷ���
public class SynchronizedTest_5 {

	int count = 0;
	synchronized void a(){
		System.out.println(Thread.currentThread().getName()+"start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName()+"count ="+count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (count == 5) {
				int i = 1/0;
			}
		}
	}
	
	public static void main(String[] args) {
		SynchronizedTest_5 thread_5 = new SynchronizedTest_5();
		
		new Thread(thread_5::a,"t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//t1���쳣�����ͷţ�t2���У�����t1ִ�е����ݼ���
		new Thread(thread_5::a, "t2").start();
	}
	
}
