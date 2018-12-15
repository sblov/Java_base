package com.lov.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

public class NIOTest_3 {

	/**
	 * �ܵ��������̼߳�ĵ�����������
	 * Pipe��һ��sourceͨ����һ��sinkͨ�������ݱ�д��sinkͨ������sourceͨ����ȡ
	 * @throws IOException 
	 */
	@Test
	public void Test() throws IOException{
		//����д�Ĳ����ֱ���������߳��У�ʵ�ֵ������ݴ��� 
		//1.��ȡ�ܵ�
		Pipe pipe = Pipe.open();
		
		//2.���������е�����д��ܵ�
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		Pipe.SinkChannel sinkChannel = pipe.sink();
		buffer.put("�ܵ��˷����".getBytes());
		buffer.flip();
		sinkChannel.write(buffer);
		
		//3.��ȡ������������
		Pipe.SourceChannel sourceChannel = pipe.source();
		buffer .flip();
		sourceChannel.read(buffer);
		System.out.println(new String(buffer.array(),0,buffer.limit()));
		
		sourceChannel.close();
		sinkChannel.close();
	}
	
	
	@Test
	public void send() throws IOException{
		
		DatagramChannel dChannel = DatagramChannel.open();
		dChannel.configureBlocking(false);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			
			String string = scanner.next();
			
			buffer.put((new Date().toString()+":\n"+string).getBytes());
			buffer.flip();
			dChannel.send(buffer, new InetSocketAddress("127.0.0.1",9898));
			buffer.clear();
			
		}
		
		dChannel.close();
		
	}
	
	@Test
	public void server() throws IOException{
		
		DatagramChannel dChannel = DatagramChannel.open();
		dChannel.configureBlocking(false);
		dChannel.bind(new InetSocketAddress(9898));
		
		Selector selector = Selector.open();
		
		dChannel.register(selector, 1);
		
		while (selector.select() > 0 ) {
			
			Iterator<SelectionKey> iterator  = selector.selectedKeys().iterator();
			
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				
				if (key.isReadable()) {
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					
					dChannel.receive(buffer);
					buffer.flip();
					System.out.println(new String(buffer.array(),0,buffer.limit()));
					buffer.clear();
				}
			}
			iterator.remove();
			
		}
		
		
	}
	
}
