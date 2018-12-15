package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

public class SynchronizedTest_7 {

	Object object = new Object();
	
	void m(){
		synchronized (object) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		
		SynchronizedTest_7 thread_9 = new SynchronizedTest_7();
		
		new Thread(thread_9::m, "t1").start();;
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(thread_9::m, "t2").start();
		//t1�߳��������Ķ���ı䣬t2���¶��󣬶�����ԭ���Ķ����
		thread_9.object = new Object();
		
		
	}
	
}
