package com.lov.thread.thread_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

//写时复制CopyOnWrite，当有数据写入时，会复制原来数据，将写入的数据指向并添加到复制的数据中，原来的对象指向新的数据
public class SyncContainer_4 {

	public static void main(String[] args) {
		/*一个线程正准备往进去写数据，突然切到另一个线程它先写了进入，在切回来这个线程并不知道这个位置已经写入
		了数据，所以它还是会傻傻的写入数据，这样另一个线程的数据就被覆盖了。如果是一边添加 ，一边遍历的话程序
		会产生ConcurrentModificationException异常*/
		List<String> list =
//				new ArrayList<>();
//				new Vector<>();
				new CopyOnWriteArrayList<>();//读多写少
		
//		List<String> list2 = Collections.synchronizedList(list);//将非同步arraylist容器包装成同步容器并返回
		Random random = new Random();
		Thread[] threads = new Thread[100];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				for (int j = 0; j < 1000; j++) {
					list.add("a"+random.nextInt(10000));
				}
			});
		}
		runAndComputeTime(threads);
		System.out.println(list.size());
	}

	static void runAndComputeTime(Thread[] threads) {

		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(t->t.start());
		Arrays.asList(threads).forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
	}
	
}
