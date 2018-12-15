package com.lov.thread.thread_4;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolTest {
//工作窃取线程池
	public static void main(String[] args) throws IOException {
		//daemon  后台线程，当主线程执行完后，线程可能还没执行完，会在后台执行，但是不会输出
		ExecutorService service = Executors.newWorkStealingPool();//底层是ForkJoinPool
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		//将后台线程输出读到控制台
		System.in.read();
		
	
	}
	
	
	static class R implements Runnable{

		int time;
		
		public R(int t) {
			this.time = t;
			
		}
		
		@Override
		public void run() {
	
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName());
		}
		
	}
}
