package com.lov.thread.thread_1;

import org.junit.Test;
//java����������Ҫ���Ķ��еĶ����Ͻ��м�¼
public class SynchronizedBase {

	private int count = 10;
//	private Object object = new Object();//object����ָ����ڴ���new������object����
	
	@Test
	public void Test_1(){
	
			
		
	}
	public void a(){
//	public synchronized void a(){ �����·����е�synchronized�ڿ�ʼʱ������ֱ�������������ͷ��������Խ�synchronizedд��������
//	public synchronized static void a(){  ���÷���Ϊ��̬�������൱����������className.class�������

		//		synchronized (object) {//����������ִ�иÿ��ڴ���ʱ����������������synchronized��objectָ��Ķ��ڴ�Ķ���������������ڶ����б�Ǽ�����¼��
		synchronized(this){//�������ͬ��������ÿ�ζ�ȥnew�¶���ֱ�ӶԱ�����м���
		 	count++;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
			
		}
	}
}
