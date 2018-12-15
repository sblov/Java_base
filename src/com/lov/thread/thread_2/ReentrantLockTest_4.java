package com.lov.thread.thread_2;

import java.util.concurrent.locks.ReentrantLock;
//ReentrantLock�������ù�ƽ��
public class ReentrantLockTest_4 extends Thread{
	//true��ʾ��ƽ������������ʱĬ�Ϸǹ�ƽ��
	private static ReentrantLock lock = new ReentrantLock(true);
	public void run(){
		for (int i = 0; i < 100; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"get lock");
			} finally {
				lock.unlock();
			}
	
		}
	}
	
	public static void main(String[] args) {
		ReentrantLockTest_4 thread_3 = new ReentrantLockTest_4();
		Thread thread = new Thread(thread_3);
		Thread thread1 = new Thread(thread_3);
		thread.start();
		thread1.start();
	}
	
}
