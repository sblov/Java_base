package com.lov.thread.thread_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*java�߲�����
	1.synchronized
	2��ͬ������
	3.ThreadPool��executor*/
public class ReentrantLockTest_1 {

	//ReentrantLock�ܴ���synchronized
	Lock lock = new ReentrantLock();
	
	/*synchronized*/ void m1(){
		lock.lock();//�൱��synchronized(this),����һ���ֶ����� �����ֶ��ͷ�
		try {
			for (int i = 0; i < 10; i++) {
				
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();//�ͷ���
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
