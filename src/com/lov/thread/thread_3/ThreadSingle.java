package com.lov.thread.thread_3;

import java.util.Arrays;

//�̰߳�ȫ�ĵ���ģʽ
public class ThreadSingle {

	private ThreadSingle(){
		System.out.println("single");
	}
	
	private static class Inner{
		private static ThreadSingle thread_0 = new ThreadSingle();
	}
	
	private static ThreadSingle getThread_0(){
		return Inner.thread_0;//ִ�е��˴��Ż����Inner
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[2000];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				ThreadSingle.getThread_0();
			});
		}
		Arrays.asList(threads).forEach(o->o.start());
	}
	
}
