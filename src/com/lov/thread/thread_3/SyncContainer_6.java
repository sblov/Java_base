package com.lov.thread.thread_3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
//�����ߣ�������
public class SyncContainer_6 {

	static BlockingQueue<String> strings = new LinkedBlockingQueue<>();//����ʽ����
	
	static Random random = new Random();
	
	public static void main(String[] args) {
		
		new Thread(()->{
			for (int i = 0; i < 100; i++) {
				try {
					strings.put("a"+i);//������ˣ��Զ��ȴ�
					TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		},"t1").start();
		
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				for(;;){
					try {
						//��������Զ��ȴ�
						System.out.println(Thread.currentThread().getName()+"take"+strings.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			},"c"+i).start();
			
		}
		
	}
	
}
