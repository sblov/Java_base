package com.lov.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Server running...");
		ServerSocket socket = new ServerSocket(8080);
		try {
			Socket accept = socket.accept();
			
			InputStream inputStream = accept.getInputStream();
			
			byte[] bytes = new byte[1024];
			int length = inputStream.read(bytes);
			
			String result = new String(bytes,0,length);
			
			System.out.println("message:"+result);
			
			OutputStream outputStream = accept.getOutputStream();
			PrintWriter printWriter =  new PrintWriter(outputStream);
			
			printWriter.print("Server Accepted .");
			
			printWriter.flush();
			
			accept.shutdownOutput();
		}catch (Exception e) {
			
		} 
		finally {
			socket.close();
		}
		
		
	}
}
