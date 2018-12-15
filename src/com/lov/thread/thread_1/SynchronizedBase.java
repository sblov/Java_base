package com.lov.thread.thread_1;

import org.junit.Test;
//java中锁，是在要锁的堆中的对象上进行记录
public class SynchronizedBase {

	private int count = 10;
//	private Object object = new Object();//object引用指向堆内存中new出来的object对象
	
	@Test
	public void Test_1(){
	
			
		
	}
	public void a(){
//	public synchronized void a(){ 当以下方法中的synchronized在开始时加锁，直到方法结束才释放锁，可以将synchronized写到方法上
//	public synchronized static void a(){  若该方法为静态方法，相当以锁定的是className.class这个对象

		//		synchronized (object) {//（互斥锁）执行该块内代码时，必须先申请锁，synchronized对object指向的堆内存的对象申请加锁，并在对象中标记加锁记录，
		synchronized(this){//与上面的同理，但不用每次都去new新对象，直接对本身进行加锁
		 	count++;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
			
		}
	}
}
