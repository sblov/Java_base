package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

public class VolatileTest_1 {

	//JMM，java内存模型。其中有一个是主内存，而线程都是有自己的储存位置，在线程使用主内存中的某个值时，
	//将值直接复制到线程的缓冲区中，从而不用每次都去读。而volatile修饰该值时，会在该值更改时，通知所有相关
	//线程，重新读取主内存中的值。(无锁同步，不同线程间可见性)
	volatile boolean runnning = true;
	
	void a(){
		System.out.println("a start");
		while (runnning) {
			
		}
		System.out.println("a end");
	}
	
	public static void main(String[] args) {
		 VolatileTest_1 thread_6 = new VolatileTest_1();
		 
		 new Thread(thread_6::a, "t1").start();
		 try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 thread_6.runnning =false;
	}
	
}
