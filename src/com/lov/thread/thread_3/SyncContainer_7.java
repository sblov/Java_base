package com.lov.thread.thread_3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SyncContainer_7 {

	static BlockingQueue<String> strings = new ArrayBlockingQueue<>(10);
	
	static Random random= new Random();
	
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i <10; i++) {
			
				strings.put("a"+i);
			
			
		}
		
//		strings.put("aa");//������ʽ����
//		strings.add("aa");//���쳣
//		strings.offer("aa");//����booleanֵ
//		strings.offer("aa",1,TimeUnit.SECONDS);//����1s����bolean
		
		System.out.println(strings);
	}
	
}
