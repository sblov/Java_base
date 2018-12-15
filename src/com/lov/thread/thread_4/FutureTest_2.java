package com.lov.thread.thread_4;
//≤¢––º∆À„

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest_2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 200000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		final int cpuCore = 4;
		
		ExecutorService service = Executors.newFixedThreadPool(cpuCore);
		
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);
		
		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	
	static class MyTask implements Callable<List<Integer>>{

		int startPos,endPos;
		
		public MyTask(int s,int e) {
			this.startPos  =s;
			this.endPos = e;
		}
		
		
		@Override
		public List<Integer> call() throws Exception {
		
			List<Integer> list = getPrime(startPos, endPos);
			return list;
		}
		
	}
	
	static boolean isPrime(int num){
		for (int i = 2; i <= num/2 ; i++) {
			if (num % i == 0 ) {
				return false;
			}
		}
		return true;
	}
	
	static List<Integer> getPrime(int start,int end){
		List<Integer> result = new ArrayList<>();
		for (int i = start; i <= end ; i++) {
			if (isPrime(i)) {
				result.add(i);
			}
		}
		return result;
	}
}
