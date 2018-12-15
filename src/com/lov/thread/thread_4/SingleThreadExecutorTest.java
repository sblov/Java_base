package com.lov.thread.thread_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {

	public static void main(String[] args) {
		//单例线程池，任务是按顺序执行的
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int l = i;
			service.execute(()->{
				System.out.println(Thread.currentThread().getName()+l);
			});
		}
	}
	
}
