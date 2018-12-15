package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*java高并发：
	1.synchronized
	2。同步容器
	3.ThreadPool，executor*/
public class ReentrantLockTest_1 {

	//ReentrantLock能代替synchronized
	Lock lock = new ReentrantLock();
	
	/*synchronized*/ void m1(){
		lock.lock();//相当于synchronized(this),这是一个手动锁， 必须手动释放
		try {
			for (int i = 0; i < 10; i++) {
				
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();//释放锁
		}
	}
	
	/*synchronized*/ void  m2(){
		lock.lock();
		System.out.println("m2 start");
		lock.unlock();
	}
	
	public static void main(String[] args) {
		
		ReentrantLockTest_1 thread_0 = new ReentrantLockTest_1();
		
		new Thread(thread_0::m1,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(thread_0::m2,"t2").start();

		
	}
	
}
