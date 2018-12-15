package com.lov.thread.thread_1;

import java.util.concurrent.TimeUnit;

public class VolatileTest_1 {

	//JMM��java�ڴ�ģ�͡�������һ�������ڴ棬���̶߳������Լ��Ĵ���λ�ã����߳�ʹ�����ڴ��е�ĳ��ֵʱ��
	//��ֱֵ�Ӹ��Ƶ��̵߳Ļ������У��Ӷ�����ÿ�ζ�ȥ������volatile���θ�ֵʱ�����ڸ�ֵ����ʱ��֪ͨ�������
	//�̣߳����¶�ȡ���ڴ��е�ֵ��(����ͬ������ͬ�̼߳�ɼ���)
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
