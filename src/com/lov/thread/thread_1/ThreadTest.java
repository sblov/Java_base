package com.lov.thread.thread_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//�����̣߳��߳�1�����������Ԫ�أ��߳�2���Ԫ�ظ�����������5�����߳�2������ʾ������
//wait()�ͷ�����notify()���ͷ���������������еķ���
public class ThreadTest {

	volatile List list = new ArrayList();
	
	public void add(Object object){
		list.add(object);
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		ThreadTest thread_11  = new ThreadTest();
		
		/*new Thread(()->{
			for (int i = 0; i < 10; i++) {
				thread_11.add(new Object());
				System.out.println("add"+i);
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1").start();
		
		new Thread(()->{
			while (true) {//�˷�cpu
				if(thread_11.size()==5){
					break;
				}
				
			}
			System.out.println("t2 end");
		},"t2").start();*/
		
		final Object lock = new Object();
		
		new Thread(()->{
			synchronized (lock) {
				System.out.println("t2 start");
				if(thread_11.size()!=5){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				System.out.println("t2 end");
				lock.notify();//����t1
			}
		},"t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(()->{
			System.out.println("t1 start");
			synchronized (lock) {
				for (int i = 0; i <10; i++) {
					thread_11.add(new Object());
					System.out.println("add"+i);
					
					if (thread_11.size()== 5) {
						lock.notify();//notify()���ͷ���
						try {
							lock.wait();//�ͷ���
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
