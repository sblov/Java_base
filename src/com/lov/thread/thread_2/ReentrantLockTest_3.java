package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest_3 {
	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		
		new Thread(()->{
			lock.lock();
			try {
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);//长时间执行
				System.out.println("t1 end");
					
			}catch (InterruptedException e) {
				System.out.println("Interrupted!");
				
			} finally {
				lock.unlock();
			}
		}).start();
		
		Thread thread = new Thread(()->{
			try {
//				lock.lock();//只会一直等待，不能打断
				lock.lockInterruptibly();//当长时间等待一把锁时，可以打断自己，不再继续等待
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("t2 end");
					
			}catch (InterruptedException e) {
				System.out.println("Interrupted!");
				
			} finally {
				try {
					if (lock.tryLock(1, TimeUnit.SECONDS)) {
						lock.unlock();
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
		
	}
}
