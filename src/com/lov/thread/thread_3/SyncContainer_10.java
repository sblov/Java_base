package com.lov.thread.thread_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SyncContainer_10 {

	public static void main(String[] args) throws InterruptedException {
		//没有容量的队列
		BlockingQueue<String> strings =new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strings.put("aa");//阻塞，等待消费者消费，内部相当于transfer()
//		strings.add("aa");//容量为0，报错
		System.out.println(strings.size());
		
	}
	
}
