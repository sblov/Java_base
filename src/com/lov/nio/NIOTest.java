package com.lov.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 缓冲区（Buffer），在Java Nio中负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据
 * 
 * 根据数据类型不同（boolean除外），提供了相应数据类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * 上述缓冲区管理方式几乎一致，通过allocate()获取缓冲区
 * 
 * 缓冲区存取数据的两个核心方法：
 * put()：存入数据到缓冲区
 * get()：获取缓冲区中的数据
 * 
 * 缓冲区的四个核心属性：
 * capacity：表示缓冲区中最大存储数据的容量，一大声明不能改变
 * limit：表示缓冲区中可以操作数据的大小，limit后数据不能进行读写
 * position：表示缓冲区中正在操作数据的位置
 * mark：表示记录当前position的位置，可以通过reset()恢复到mark位置
 * 
 * 0 <= mark <= positon <= limit <= position
 * 
 * 直接缓冲区与非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
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
		
		//mark()标记
		buffer.mark();
		
		buffer.get(bs, 2, 2);
		System.out.println(new String(bs, 2, 2));
		System.out.println(buffer.position());
		
		//reset() 恢复到标记处
		buffer.reset();
		
		System.out.println(buffer.position());
		
		//判断缓冲区是否还要剩余数据
		if (buffer.hasRemaining()) {
			//获取可操作的剩余数据
			System.out.println(buffer.remaining());
		}
		
	}
	
	@Test
	public void test(){
		String str = "abcde";
		
		//1.分配一个指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//2.利用put()存入数据到缓冲区
		buffer.put(str.getBytes());
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//3.切换读取数据模式
		buffer.flip();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//4.利用get()读取缓冲区中的数据
		byte[] bs = new byte[buffer.limit()];
		buffer.get(bs);
		System.out.println(new String(bs,0,bs.length));
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//5.rewind() 可重复读
		buffer.rewind();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		//6.clear() 清空缓冲区,但是缓冲区的数据依然存在，处于“被遗忘”状态
		buffer.clear();
		
		System.out.println("----------------------------");
		System.out.println(buffer.position());;
		System.err.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		System.out.println((char)buffer.get());
	}

}
