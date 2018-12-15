package com.lov.thread.thread_1;

import java.util.ArrayList;
import java.util.List;

//volatileֻ��֤�ɼ��ԣ�����֤ԭ���ԣ���synchronized����֤
//��������У��ڷ����е�count++ǰ�����ܻ��������̴߳�ϣ����¼ӵ�ֵ�ı�  
public class VolatileVSAtomic_1 {

	//�������Ľ���ʱ�ԶԵ��������Ĳ���������ʹ����Ӧ��ԭ���࣬ԭ����ķ������Ǿ���ԭ���Եģ����������������ԭ����
	//AtomicInteger  count = new AtomicInteger(0);
	volatile int count = 0;
	void a(){
		for (int i = 0; i < 10000; i++) {
			count++;//++����ԭ���ԵĲ���
			//count.incrementAndGet(); //�÷����߱�ԭ����
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
