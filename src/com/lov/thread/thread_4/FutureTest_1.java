package com.lov.thread.thread_4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest_1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//任务中放入一个callable，返回值为int
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		});
		
		new Thread(task).start();
		
		System.out.println(task.get());//阻塞方法，直到任务执行完成才执行
		
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		//通过submit将callable放入线程中执行并返回futre类型结果
		Future<Integer> future = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		
		System.out.println(future.isDone());//判断任务是否执行完
		System.out.println(future.get());
		System.out.println(future.isDone());
		service.shutdown();
	}
}
