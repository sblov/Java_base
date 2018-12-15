package com.lov.thread.thread_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//大多数线程池都由ThreadPoolExecutor支持
public class FixedThreadPoolTest {
	public static void main(String[] args) throws InterruptedException {
		//可用execute()，submit()执行任务
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 6; i++) {
			//6个任务，线程池中有五个线程，多的任务会在线程池的排队队列中等待，任务完成线程不会自动关闭，处于等待
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
		//关闭线程池中线程，不会马上关闭，等待线程中任务完成
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
