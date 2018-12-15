package com.lov.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * @author Administrator
 *对于UDP传输，客户端可以直接将数据发送出去，即时服务器没有启动
 */
public class UdpClient {

	
	
	public static void main(String[] args) throws IOException {
		System.out.println("Client sending msg...");
		
		String msg = "udpclient message:...";
		
		DatagramSocket dSocket = new DatagramSocket();
		
		byte[] bytes = msg.getBytes();
		
		DatagramPacket dPacket = new DatagramPacket(bytes, bytes.length,new InetSocketAddress("127.0.0.1", 8080));
		
		dSocket.send(dPacket);
		dSocket.close();
		
		System.out.println("msg end"); 
		
	}
	
}
