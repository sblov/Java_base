package com.lov.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UdpServer {

	public static void main(String[] args) throws IOException {
		System.out.println("UdpServer runnning...");
		
		DatagramSocket dSocket = new DatagramSocket(8080);
		
		byte[] buf = new byte[1024];
		
		DatagramPacket dPacket =  new DatagramPacket(buf, buf.length);
		
		dSocket.receive(dPacket);
		
		String address = dPacket.getAddress().toString();
		
		String data = new String(dPacket.getData(),0,dPacket.getLength());
		
		System.out.println("ip:"+address+"\ndata:"+data);
		
		System.out.println("UdpServer closed.");
		
	}
	
}
