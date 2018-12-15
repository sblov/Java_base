package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;
//synchronized֧��������������ͬһ���̶߳�ͬһ�����μ���������ͬ���������ø���ͬ������ͬ�� 
//������   �߳�1����o1������ȥ������o2�����߳�2����o2������ȥ������o1���������߳�ͬʱ���У�����������
public class SynchronizedTest_4 {

	public synchronized void m1(){
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
	}
	
	public synchronized void m2(){
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("m2 end");
	}
}
