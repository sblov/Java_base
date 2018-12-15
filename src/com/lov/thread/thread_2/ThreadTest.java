package com.lov.thread.thread_2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

//����ͬ����������get��put����getCount�����������ˣ�put�ȴ����������ˣ�get�ȴ�
public class ThreadTest<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0 ;
	//������
	public synchronized void put(T t){
		while (lists.size() == MAX){
			try {				
					this.wait();//wait()һ����while���ʹ��
			} catch (Exception e) {
				e.printStackTrace();
			};
			//�ڸô�����������߳���ǰ�õ�����putʹsizeΪmax�󣬸��߳��õ���ʱ��������ж�size�������if��ֱ��������ִ�У���������ڴ����
		}
		lists.add(t);
		++count;
		this.notifyAll();//���������̣߳������notify����������ʱ�����ܻ��ѵĻ���һ��put�̣߳����¶�������
	}
	//������
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
		 //10��������
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
		 //2��������
		 for (int i = 0; i < 2; i++) {
			new Thread(()->{
				for (int j = 0; j < 25; j++) {
					thread_4.put(Thread.currentThread().getName()+" "+j);
				}
			},"p"+i).start();
		}
		
	}
	
}
