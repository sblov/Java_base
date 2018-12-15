package com.lov.thread.thread_3;

import java.util.concurrent.LinkedTransferQueue;

public class SyncContainer_9 {
	
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strings = new LinkedTransferQueue<>();
		//先启动消费者线程
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		//当生产者线程生产时，先看是否有消费者线程，有则直接给消费者线程，否则阻塞
		strings.transfer("aa");
		
		/*new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		
	}
	
}
