package com.lov.thread.thread_4;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
//��ʱ���̳߳�
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		service.scheduleAtFixedRate(()->{//�Թ̶�Ƶ��ִ������
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}, 0/*��ʼִ��ʱ��*/, 500/*��500msִ��*/, TimeUnit.MILLISECONDS);
		
		
	}
	
}
