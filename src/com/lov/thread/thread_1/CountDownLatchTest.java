package com.lov.thread.thread_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//ThreadTest优化
public class CountDownLatchTest {

volatile List list = new ArrayList();
	
	public void add(Object object){
		list.add(object);
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		ThreadTest thread_11  = new ThreadTest();
		
		CountDownLatch countDownLatch = new CountDownLatch(1);//当值变为0，门栓会打开
		
		new Thread(()->{
			
				System.out.println("t2 start");
				if(thread_11.size()!=5){
					try {
						countDownLatch.await();//该wait不需要锁定对象，有门栓，等待打开
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				System.out.println("t2 end");
			
		},"t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(()->{
			System.out.println("t1 start");
			
				for (int i = 0; i <10; i++) {
					thread_11.add(new Object());
					System.out.println("add"+i);
					
					if (thread_11.size()== 5) {
						countDownLatch.countDown();//门栓值减一，为0，t2线程执行，因为没有锁定对象，该线程继续执行
											}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
		}).start();
	}
	
}
