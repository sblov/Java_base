package com.lov.thread.thread_1;
//不要用字符串常量做锁对象，如下，两个锁都是同一对象，当其他线程也有锁定相同对象时，可能会出现死锁
public class SynchronizedTest_8 {

	String s1 = "hello";
	String s2 = "hello";
	
	void m1(){
		synchronized (s1) {
			System.out.println(s1);
		}
	}
	
	void m2(){
		synchronized (s2) {
			System.out.println(s2);
		}
	}
					
	
}
