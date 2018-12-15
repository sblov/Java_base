package com.lov.thread.thread_1;


import java.util.concurrent.TimeUnit;

//ֻ��д����ͬ����������ͬ�����������
public class SynchronizedTest_3 {

	String name;
	double balance;
	
	public synchronized void set(String name,double balance){
		this.name = name;
		
		//���ø�˯�ߣ��Ŵ����ִ��ʱ�䣬�Ա㿴���ڸ��ڼ������ķ�ͬ��������ִ�У����µ��������
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
