package com.lov.thread.thread_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class SyncContainer_1 {

	static List<String> tickets = new ArrayList<>();
//	static Vector<String> tickets = new Vector<>();//ͬ������������ͬ��������ԭ����
	static{
		for (int i = 0; i < 10000; i++) {
			tickets.add("ticket:"+i);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				/*while (tickets.size() >0) {//�жϺͲ�������
					
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("saled:"+tickets.remove(0));
				}*/
				while (true) {
					synchronized (tickets) {
						if (tickets.size() > 0 ) {
							System.out.println("saled:"+tickets.remove(0));
						}
					}
				}
			}).start();
		}
	}
	
}
