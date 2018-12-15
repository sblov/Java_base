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
 * NIO网络通信的三个核心：
 * 	通道（Channel）：负责连接
 * 		java.nio.channels.Channel 接口
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 	缓冲区（Buffer）：负责数据的存取
 * 	选择器（Selector）：是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 * 
 * @author P1314023
 *
 */
public class NIOTest_2 {
	
	//-------------------------------------非阻塞式的socket-------------------------------
	
	@Test
	public void client_1() throws IOException{
		
		//1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		//2.切换非阻塞模式
		sChannel.configureBlocking(false);
		
		//3。分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//4.发送数据给服务端
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
		
	
		//5.关闭通道
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
		
		//1.获取通道
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		
		//2.切换非阻塞模式
		sChannel.configureBlocking(false);
		
		//3.绑定连接
		sChannel.bind(new InetSocketAddress(9898));
		
		//4.获取选择器
		Selector selector = Selector.open();
		
		//5.将通道注册到选择器，并且指定监听接收事件
		sChannel.register(selector, 16);
		
		//6.轮询式的获取选择器上已经准备就绪的事件
		while (selector.select() > 0) {
			
			//7.获取当前选择器中所有注册的选择键
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			while (it.hasNext()) {
				//8.获取准备就绪的事件
				SelectionKey sKey = it.next();
				
				//9.判断具体是什么事件准备就绪
				if (sKey.isAcceptable()) {
					//10。若接收就绪，获取客户端连接
					SocketChannel socketChannel = sChannel.accept();
					
					//11.切换到非阻塞模式
					socketChannel.configureBlocking(false);
					
					//12.将该通道注册到选择器上
					socketChannel.register(selector, 1);
					
				}else if (sKey.isReadable()) {
					//13.获取当前选择器上读就绪状态的通道
					SocketChannel socketChannel = (SocketChannel) sKey.channel();
					
					//14.读取数据
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					
					int len = 0;
					while ((len = socketChannel.read(buffer)) >0) {
						buffer.flip();
						System.out.println(new String(buffer.array(),0,len));
						buffer.clear();
					}
				}
				
				//15。取消选择键
				it.remove();
			}
			
		}
		
		
		sChannel.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
//-------------------------------------阻塞式的socket-------------------------------
	/**
	 * 客户端
	 * @throws IOException 
	 */
	@Test
	public void client() throws IOException{
		
		//1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		FileChannel inChannel = FileChannel.open(Paths.get("file/1.txt"), StandardOpenOption.READ);
		
		//2。分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//3.读取本地文件，并发送到服务器
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}
		//阻塞式，需要手动关闭
		sChannel.shutdownOutput();
		
		//接收服务端的反馈
		int len = 0;
		while ((len = sChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(),0,len));
			buffer.clear();
		}
		
		//4.关闭通道
		inChannel.close();
		sChannel.close();
		
		
	}
	
	@Test
	public void server() throws IOException{
		
		//1.获取通道
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel.open(Paths.get("file/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		
		//2.绑定连接
		sChannel.bind(new InetSocketAddress(9898));
		
		//3.获取客户连接的通道
		SocketChannel sChannel2 = sChannel.accept();
		
		//4.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//5.接收客户端的数据，并保存到本地
		while (sChannel2.read(buffer) != -1) {
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		
		sChannel2.shutdownInput();
		
		//发送反馈给客户端
		buffer.put("finished----".getBytes());
		buffer.flip();
		sChannel2.write(buffer);
		
		//6.关闭通道
		sChannel2.close();
		outChannel.close();
		sChannel.close();
	}
	
	
}
