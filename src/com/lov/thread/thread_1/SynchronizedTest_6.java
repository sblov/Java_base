package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

//�����������������ķ�Χ��ͬ���ֵ���ִ��Ч�ʽ�ϸ����Ч�ʵͣ������ʲ���
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
