package com.lov.thread.thread_4;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolTest {
//������ȡ�̳߳�
	public static void main(String[] args) throws IOException {
		//daemon  ��̨�̣߳������߳�ִ������߳̿��ܻ�ûִ���꣬���ں�ִ̨�У����ǲ������
		ExecutorService service = Executors.newWorkStealingPool();//�ײ���ForkJoinPool
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		//����̨�߳������������̨
		System.in.read();
		
	
	}
	
	
	static class R implements Runnable{

		int time;
		
		public R(int t) {
			this.time = t;
			
		}
		
		@Override
		public void run() {
	
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName());
		}
		
	}
}
