package com.lov.thread.thread_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolTest {
	//该线程池最初没有线程，当有一个任务就会自动新建启动一个线程，当启动的线程执行完任务并空闲时，来了新的任务，使用空闲线程执行，如果没有空闲的线程，则再启动新线程
	//线程最大数根据电脑容量，当空闲线程超过60s时，会自动结束线程
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
		
		service.shutdown();//关service
	}

}
