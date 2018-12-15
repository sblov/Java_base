package com.lov.thread.thread_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolTest {
	//���̳߳����û���̣߳�����һ������ͻ��Զ��½�����һ���̣߳����������߳�ִ�������񲢿���ʱ�������µ�����ʹ�ÿ����߳�ִ�У����û�п��е��̣߳������������߳�
	//�߳���������ݵ����������������̳߳���60sʱ�����Զ������߳�
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		
		for (int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		TimeUnit.SECONDS.sleep(80);
		System.out.println(service);
		
		service.shutdown();//��service
	}

}