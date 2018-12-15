package com.lov.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) throws IOException {
		System.out.println("Send message...");
		Socket socket = new Socket("127.0.0.1",8080);
		
		try {
			OutputStream outputStream = socket.getOutputStream();
			
			String msg = "Client message.";
			
			outputStream.write(msg.getBytes());
			System.out.println("msg end.");
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
			
			String result = bReader.readLine();
			
			System.out.println("Sever send:"+result);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			socket.close();
		}
	}
	
}
