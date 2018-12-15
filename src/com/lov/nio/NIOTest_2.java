package com.lov.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/**
 * NIO����ͨ�ŵ��������ģ�
 * 	ͨ����Channel������������
 * 		java.nio.channels.Channel �ӿ�
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 	��������Buffer�����������ݵĴ�ȡ
 * 	ѡ������Selector������SelectableChannel�Ķ�·�����������ڼ��SelectableChannel��IO״��
 * 
 * @author P1314023
 *
 */
public class NIOTest_2 {
	
	//-------------------------------------������ʽ��socket-------------------------------
	
	@Test
	public void client_1() throws IOException{
		
		//1.��ȡͨ��
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		//2.�л�������ģʽ
		sChannel.configureBlocking(false);
		
		//3������ָ����С�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//4.�������ݸ������
//		buffer.put(new Date().toString().getBytes());
//		buffer.flip();
//		sChannel.write(buffer);
//		buffer.clear();
		
		
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			String string = scanner.next();
			buffer.put((new Date().toString() + "\n" + string).getBytes());
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}
		
	
		//5.�ر�ͨ��
		sChannel.close();
		
		
	}
	
	/**
	 * SelectionKey
	 * 	SelectionKey.OP_READ (1)
	 * 	SelectionKey.OP_WRITE (4)
	 * 	SelectionKey.OP_CONNECT (8)
	 * 	SelectionKey.OP_ACCEPT (16)
	 * @throws IOException
	 */
	@Test
	public void server_1() throws IOException{
		
		//1.��ȡͨ��
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		
		//2.�л�������ģʽ
		sChannel.configureBlocking(false);
		
		//3.������
		sChannel.bind(new InetSocketAddress(9898));
		
		//4.��ȡѡ����
		Selector selector = Selector.open();
		
		//5.��ͨ��ע�ᵽѡ����������ָ�����������¼�
		sChannel.register(selector, 16);
		
		//6.��ѯʽ�Ļ�ȡѡ�������Ѿ�׼���������¼�
		while (selector.select() > 0) {
			
			//7.��ȡ��ǰѡ����������ע���ѡ���
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			while (it.hasNext()) {
				//8.��ȡ׼���������¼�
				SelectionKey sKey = it.next();
				
				//9.�жϾ�����ʲô�¼�׼������
				if (sKey.isAcceptable()) {
					//10�������վ�������ȡ�ͻ�������
					SocketChannel socketChannel = sChannel.accept();
					
					//11.�л���������ģʽ
					socketChannel.configureBlocking(false);
					
					//12.����ͨ��ע�ᵽѡ������
					socketChannel.register(selector, 1);
					
				}else if (sKey.isReadable()) {
					//13.��ȡ��ǰѡ�����϶�����״̬��ͨ��
					SocketChannel socketChannel = (SocketChannel) sKey.channel();
					
					//14.��ȡ����
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					
					int len = 0;
					while ((len = socketChannel.read(buffer)) >0) {
						buffer.flip();
						System.out.println(new String(buffer.array(),0,len));
						buffer.clear();
					}
				}
				
				//15��ȡ��ѡ���
				it.remove();
			}
			
		}
		
		
		sChannel.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
//-------------------------------------����ʽ��socket-------------------------------
	/**
	 * �ͻ���
	 * @throws IOException 
	 */
	@Test
	public void client() throws IOException{
		
		//1.��ȡͨ��
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		FileChannel inChannel = FileChannel.open(Paths.get("file/1.txt"), StandardOpenOption.READ);
		
		//2������ָ����С�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//3.��ȡ�����ļ��������͵�������
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}
		//����ʽ����Ҫ�ֶ��ر�
		sChannel.shutdownOutput();
		
		//���շ���˵ķ���
		int len = 0;
		while ((len = sChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(),0,len));
			buffer.clear();
		}
		
		//4.�ر�ͨ��
		inChannel.close();
		sChannel.close();
		
		
	}
	
	@Test
	public void server() throws IOException{
		
		//1.��ȡͨ��
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel.open(Paths.get("file/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		
		//2.������
		sChannel.bind(new InetSocketAddress(9898));
		
		//3.��ȡ�ͻ����ӵ�ͨ��
		SocketChannel sChannel2 = sChannel.accept();
		
		//4.����ָ����С�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//5.���տͻ��˵����ݣ������浽����
		while (sChannel2.read(buffer) != -1) {
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		
		sChannel2.shutdownInput();
		
		//���ͷ������ͻ���
		buffer.put("finished----".getBytes());
		buffer.flip();
		sChannel2.write(buffer);
		
		//6.�ر�ͨ��
		sChannel2.close();
		outChannel.close();
		sChannel.close();
	}
	
	
}
