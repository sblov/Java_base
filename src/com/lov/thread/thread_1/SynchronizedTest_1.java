package com.lov.thread.thread_1;

public class SynchronizedTest_1  implements Runnable{

	private int count = 10;
	
	@Override
	public /*synchronized*/ void run() {

		count--;//�����̷߳���ʱ���ڸô�ִ�п��ܱ���ϣ�������;��ǰֵ�������̴߳�ϣ�Ӧ�ñ�֤ԭ����
		System.out.println(Thread.currentThread().getName()+"count = "+count);
		
	}
	
	public static void main(String[] args) {
		SynchronizedTest_1 thread_1 = new SynchronizedTest_1();
		for (int i = 0; i < 5; i++) {
			new Thread(thread_1).start();
		}
	}
	

}
