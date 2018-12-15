package com.lov.thread.thread_1;


import java.util.concurrent.TimeUnit;

//只对写进行同步，而读非同步，导致脏读
public class SynchronizedTest_3 {

	String name;
	double balance;
	
	public synchronized void set(String name,double balance){
		this.name = name;
		
		//设置该睡眠，放大代码执行时间，以便看出在该期间其他的非同步读方法执行，导致的脏读问题
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		this.balance = balance;
	}
	
	public double getBalance(String name){
		return this.balance;
	}
	
	public static void main(String[] args) {
		SynchronizedTest_3 thread_3 = new SynchronizedTest_3();
		new Thread(()->thread_3.set("zhangsan", 100.0)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(thread_3.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(thread_3.getBalance("zhangsan"));
	}
	
}
