package com.lov.thread.thread_1;


//同步方法中调用非同步方法
public class SynchronizedTest_2 {
	
	public synchronized void m1(){
		System.out.println(Thread.currentThread().getName()+"m1  start ...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"m1  end");

	}

	public void m2(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"m2  end");

	}
	
	public static void main(String[] args) {
		SynchronizedTest_2 thread_2 = new SynchronizedTest_2();
		new Thread(()->thread_2.m1(),"t1").start();
//		new Thread(thread_2::m1(),"t1").start(); 方法引用
		new Thread(()->thread_2.m2(),"t2").start();

	}
}
