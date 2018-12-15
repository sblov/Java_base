package com.lov.thread.thread_3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SyncContainer_5 {

	public static void main(String[] args) {
		
		Queue<String> queue  =new ConcurrentLinkedQueue<>();
		
		for (int i = 0; i < 10; i++) {
			queue.offer("a"+i);//offer相当于add
		}
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.poll());//把第一个元素取出，并删除元素
		System.out.println(queue.size());
		System.out.println(queue.peek());//取出，不删除元素
		System.out.println(queue.size());
		
		
	}
	
}
