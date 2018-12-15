package com.lov.thread.thread_2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//ThreadTest优化
public class ConditionTest<T> {

	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0 ;
	
	private Lock lock = new ReentrantLock();
	private Condition productor = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	//生产者
	public  void put(T t){
		lock.lock();
		try {
			while (lists.size() == MAX){
				try {				
						productor.await();
				} catch (Exception e) {
					e.printStackTrace();
				};
				//在该处，如果其他线程提前拿到锁，put使size为max后，该线程拿到锁时，会继续判断size，如果用if则直接往下面执行，造成容器内存溢出
			}
			lists.add(t);
			++count;
			consumer.signalAll();
		}finally {
			lock.unlock();
		}
			}
	//消费者
	public  T get(){
		T t = null;
		lock.lock();
		try {
			while (lists.size() == 0 ) {
				try {
					consumer.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			t = lists.removeFirst();
			count--;
			productor.signalAll();
		} finally {
			lock.unlock();
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		
		 ConditionTest<String> thread_4 = new ConditionTest<>();
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
