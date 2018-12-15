package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//ReentrantLock与synchronized
//     前者中能尝试锁，即可以尝试锁定，根据情况执行，不要像后者一直等待
public class ReentrantLockTest_2 {

	Lock lock = new ReentrantLock();
	
	void m1(){
		
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	void m2(){
		boolean locked = false;
		
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);//尝试锁，等待5秒
			System.out.println("m2 = "+locked);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if (locked) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ReentrantLockTest_2 thread_1 = new ReentrantLockTest_2();
		
		new Thread(thread_1::m1,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(thread_1::m2,"t2").start();

		
	}
	
}
