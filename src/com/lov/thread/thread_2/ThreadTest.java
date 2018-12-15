package com.lov.thread.thread_2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

//创建同步容器，有get，put，及getCount，当容器满了，put等待，容器空了，get等待
public class ThreadTest<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0 ;
	//生产者
	public synchronized void put(T t){
		while (lists.size() == MAX){
			try {				
					this.wait();//wait()一般结合while结合使用
			} catch (Exception e) {
				e.printStackTrace();
			};
			//在该处，如果其他线程提前拿到锁，put使size为max后，该线程拿到锁时，会继续判断size，如果用if则直接往下面执行，造成容器内存溢出
		}
		lists.add(t);
		++count;
		this.notifyAll();//唤醒所有线程，如果用notify，在容器满时，可能唤醒的还是一个put线程，导致堵塞死锁
	}
	//消费者
	public synchronized T get(){
		T t = null;
		while (lists.size() == 0 ) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count--;
		this.notifyAll();
		return t;
	}
	
	public static void main(String[] args) {
		
		 ThreadTest<String> thread_4 = new ThreadTest<>();
		 //10个消费者
		 for (int i = 0; i < 10; i++) {
			
			 new Thread(()->{
				 for (int j = 0; j < 5; j++) {
					System.out.println(thread_4.get());
				}
			 },"t"+i).start();
			 
		}
		 try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 //2个生产者
		 for (int i = 0; i < 2; i++) {
			new Thread(()->{
				for (int j = 0; j < 25; j++) {
					thread_4.put(Thread.currentThread().getName()+" "+j);
				}
			},"p"+i).start();
		}
		
	}
	
}
