package com.lov.thread.thread_3;

import java.util.concurrent.LinkedTransferQueue;

public class SyncContainer_9 {
	
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strings = new LinkedTransferQueue<>();
		//�������������߳�
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		//���������߳�����ʱ���ȿ��Ƿ����������̣߳�����ֱ�Ӹ��������̣߳���������
		strings.transfer("aa");
		
		/*new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		
	}
	
}
