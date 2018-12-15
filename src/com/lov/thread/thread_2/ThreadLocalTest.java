package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
//�ֲ߳̾��������൱�����߳��л��Լ�copyһ�ݵ��߳��Լ�ά�����������߳��޹�
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
