package com.lov.thread.thread_1;

import java.util.ArrayList;
import java.util.List;

//volatile只保证可见性，不保证原子性，而synchronized都保证
//以下情况中，在方法中的count++前，可能会有其他线程打断，导致加的值改变  
public class VolatileVSAtomic_1 {

	//当操作的仅仅时对对单个变量的操作，可以使用相应的原子类，原子类的方法都是具有原子性的，但多个方法不构成原子性
	//AtomicInteger  count = new AtomicInteger(0);
	volatile int count = 0;
	void a(){
		for (int i = 0; i < 10000; i++) {
			count++;//++不是原子性的操作
			//count.incrementAndGet(); //该方法具备原子性
		}
	}
	
	public static void main(String[] args) {
		VolatileVSAtomic_1 thread_7 =new VolatileVSAtomic_1();
		
		List<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(thread_7::a,"thread"+i));
		}
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(thread_7.count);
	}
}
