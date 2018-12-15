package com.lov.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * @author Administrator
 *����UDP���䣬�ͻ��˿���ֱ�ӽ����ݷ��ͳ�ȥ����ʱ������û������
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
