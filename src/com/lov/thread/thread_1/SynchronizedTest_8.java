package com.lov.thread.thread_1;
//��Ҫ���ַ������������������£�����������ͬһ���󣬵������߳�Ҳ��������ͬ����ʱ�����ܻ��������
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
