package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;
//synchronized支持重入锁，对于同一个线程对同一对象多次加锁；子类同步方法调用父类同步方法同理 
//死锁，   线程1先锁o1对象，再去申请锁o2对象，线程2先锁o2对象，再去申请锁o1对象，两个线程同时运行，即导致死锁
public class SynchronizedTest_4 {

	public synchronized void m1(){
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
	}
	
	public synchronized void m2(){
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("m2 end");
	}
}
