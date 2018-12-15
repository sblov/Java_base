package com.lov.thread.thread_3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
//SyncContainer_1 优化
public class SyncContainer_2 {

	
	static Queue<String> tickets =  new ConcurrentLinkedQueue<>();
	
	static{
		for (int i = 0; i < 10000; i++) {
			tickets.add("ticket:"+i);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				
				while (true) {
					String string = tickets.poll();//同步方法
					if (string == null) {
						break;
					}
					System.out.println("saled:"+string);
				}
			}).start();
		}
	}
	
}

