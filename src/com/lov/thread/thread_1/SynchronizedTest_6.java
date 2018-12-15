package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

//以下两个方法的锁的范围不同，粗的锁执行效率较细锁的效率低，并发率不高
public class SynchronizedTest_6 {

	int count = 0;
	 synchronized void m1(){
		 
		 try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 count++;
		 
		 try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
	 }
	
	 void m2(){
		 
		 try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		 synchronized (this) {
			 count++;
		}
			 
			 
			 try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 
	 }
}
