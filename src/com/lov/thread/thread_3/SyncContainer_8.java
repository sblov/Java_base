package com.lov.thread.thread_3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class SyncContainer_8 {
//执行定时任务
	static BlockingQueue<MyTask> tasks = new DelayQueue<>();
	static Random random= new Random();
	
	static class MyTask implements Delayed{

		long runningTime;
		public MyTask(long rt) {
			this.runningTime = rt;
		}
		
		@Override
		public int compareTo(Delayed o) {

			if (this.getDelay(TimeUnit.MILLISECONDS)< o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			}else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			}else {
				return 0;
				
			}
			
		}

		@Override
		public long getDelay(TimeUnit unit) {

			return unit.convert(runningTime-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String toString() {
			return "MyTask [runningTime=" + runningTime + "]";
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		long start  = System.currentTimeMillis();
		MyTask t1 = new MyTask(start + 1000);
		MyTask t2 = new MyTask(start + 2000);
		MyTask t3 = new MyTask(start + 3000);
		MyTask t4 = new MyTask(start + 4000);

		tasks.put(t1);
		tasks.put(t2);
		tasks.put(t3);
		tasks.put(t4);
		//插入后已按执行时间排序
		System.out.println(tasks);
		
		for (int i = 0; i < 4; i++) {
			System.out.println(tasks.take());
		}
	}
}
