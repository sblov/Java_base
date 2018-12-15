package com.lov.thread.thread_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//������̳߳ض���ThreadPoolExecutor֧��
public class FixedThreadPoolTest {
	public static void main(String[] args) throws InterruptedException {
		//����execute()��submit()ִ������
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 6; i++) {
			//6�������̳߳���������̣߳������������̳߳ص��ŶӶ����еȴ�����������̲߳����Զ��رգ����ڵȴ�
			executorService.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(executorService);
		//�ر��̳߳����̣߳��������Ϲرգ��ȴ��߳����������
		executorService.shutdown();
		System.out.println(executorService.isTerminated());
		System.out.println(executorService.isShutdown());
		System.out.println(executorService);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(executorService.isTerminated());
		System.out.println(executorService.isShutdown());
		System.out.println(executorService);
	}
	
}
