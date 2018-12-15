package com.lov.thread.thread_2;

import java.util.concurrent.locks.ReentrantLock;
//ReentrantLock可以设置公平锁
public class ReentrantLockTest_4 extends Thread{
	//true表示公平锁，不带参数时默认非公平锁
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
