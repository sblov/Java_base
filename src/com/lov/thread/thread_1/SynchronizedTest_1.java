package com.lov.thread.thread_1;

public class SynchronizedTest_1  implements Runnable{

	private int count = 10;
	
	@Override
	public /*synchronized*/ void run() {

		count--;//当多线程访问时，在该处执行可能被打断，导致中途当前值被其他线程打断，应该保证原子性
		System.out.println(Thread.currentThread().getName()+"count = "+count);
		
	}
	
	public static void main(String[] args) {
		SynchronizedTest_1 thread_1 = new SynchronizedTest_1();
		for (int i = 0; i < 5; i++) {
			new Thread(thread_1).start();
		}
	}
	

}
