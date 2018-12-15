package com.lov.thread.thread_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SyncContainer_10 {

	public static void main(String[] args) throws InterruptedException {
		//û�������Ķ���
		BlockingQueue<String> strings =new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strings.put("aa");//�������ȴ����������ѣ��ڲ��൱��transfer()
//		strings.add("aa");//����Ϊ0������
		System.out.println(strings.size());
		
	}
	
}
