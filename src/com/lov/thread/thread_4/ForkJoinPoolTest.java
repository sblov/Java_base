package com.lov.thread.thread_4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
	
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random random = new Random();
	
	static {
		long start = System.currentTimeMillis();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(100);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(Arrays.stream(nums).sum());
	}
	//递归任务  RecursiveAction没有返回值
	/*static class AddTask extends RecursiveAction{

		int start,end;
		
		public AddTask(int s,int e) {
			this.start = s;
			this.end = e;
		}
		
		@Override
		protected void compute() {

			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from"+start+"to"+end+"=" +sum);
				
			}else {
				int middle = start + (end-start)/2;
				
				AddTask subtask = new AddTask(start, middle);
				AddTask subtask2 = new AddTask(middle, end);
				subtask.fork();
				subtask2.fork();
			}
			
		}
		
	}*/
	//递归任务  RecursiveTask有返回值
	static class AddTask extends RecursiveTask<Long>{

		int start,end;
		
		public AddTask(int s,int e) {
			this.start = s;
			this.end = e;
		}
		
		@Override
		protected Long compute() {

			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				return sum;
				
			}
				int middle = start + (end-start)/2;
				AddTask subtask = new AddTask(start, middle);
				AddTask subtask2 = new AddTask(middle, end);
				subtask.fork();
				subtask2.fork();
				
				return subtask.join() + subtask2.join();
			
			
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		ForkJoinPool fPool = new ForkJoinPool();
		AddTask task =new AddTask(0, nums.length);
		long start = System.currentTimeMillis();
		fPool.execute(task);
		
		System.out.println(task.join());//join()阻塞方法
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		
//		System.in.read();
		
	}
	
}
