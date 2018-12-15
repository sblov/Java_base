package com.lov.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * ��������Buffer������Java Nio�и������ݵĴ�ȡ���������������飬���ڴ洢��ͬ�������͵�����
 * 
 * �����������Ͳ�ͬ��boolean���⣩���ṩ����Ӧ�������͵Ļ�����
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * ��������������ʽ����һ�£�ͨ��allocate()��ȡ������
 * 
 * ��������ȡ���ݵ��������ķ�����
 * put()���������ݵ�������
 * get()����ȡ�������е�����
 * 
 * ���������ĸ��������ԣ�
 * capacity����ʾ�����������洢���ݵ�������һ���������ܸı�
 * limit����ʾ�������п��Բ������ݵĴ�С��limit�����ݲ��ܽ��ж�д
 * position����ʾ�����������ڲ������ݵ�λ��
 * mark����ʾ��¼��ǰposition��λ�ã�����ͨ��reset()�ָ���markλ��
 * 
 * 0 <= mark <= positon <= limit <= position
 * 
 * ֱ�ӻ��������ֱ�ӻ�����
 * ��ֱ�ӻ�������ͨ��allocate()�������仺��������������������JVM���ڴ���
 * ֱ�ӻ�������ͨ��allocateDirect()��������ֱ�ӻ��������������������������ڴ��У��������Ч��
 * 
 * 
 * @author P1314023
 *
 */
public class NIOTest {
	
	@Test
	public void test2(){
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
		System.out.println(buffer.isDirect());
		
	}
	
	@Test
	public void test1(){
		
		String str = "abcde";
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		
		buffer.put(str.getBytes());
		buffer.flip();
		
		byte[] bs = new byte[buffer.limit()];
		buffer.get(bs,0,2);
		System.out.println(new String(bs, 0, 2));
		
		System.out.println(buffer.position());
		
		//mark()���
		buffer.mark();
		
		buffer.get(bs, 2, 2);
		System.out.println(new String(bs, 2, 2));
		System.out.println(buffer.position());
		
		//reset() �ָ�����Ǵ�
		buffer.reset();
		
		System.out.println(buffer.position());
		
		//�жϻ������Ƿ�Ҫʣ������
		if (buffer.hasRemaining()) {
			//��ȡ�ɲ�����ʣ������
			System.out.println(buffer.remaining());
		}
		
	}
	
	@Test
	public void test(){
		String str = "abcde";
		
		//1.����һ��ָ����С�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//2.����put()�������ݵ�������
		buffer.put(str.getBytes());
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//3.�л���ȡ����ģʽ
		buffer.flip();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//4.����get()��ȡ�������е�����
		byte[] bs = new byte[buffer.limit()];
		buffer.get(bs);
		System.out.println(new String(bs,0,bs.length));
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//5.rewind() ���ظ���
		buffer.rewind();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//6.clear() ��ջ�����,���ǻ�������������Ȼ���ڣ����ڡ���������״̬
		buffer.clear();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		System.out.println((char)buffer.get());
	}

}
