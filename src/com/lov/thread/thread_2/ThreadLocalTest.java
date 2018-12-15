package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
//线程局部变量，相当于在线程中会自己copy一份到线程自己维护，于其他线程无关
	static ThreadLocal<Person> pLocal  =new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(pLocal.get());
		},"t1").start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pLocal.set(new Person());
		}).start();
	}
	
	
}

class Person{
	String name = "shangsan";
}
