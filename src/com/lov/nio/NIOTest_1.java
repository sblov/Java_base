package com.lov.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

/**
 * ͨ����Channel��������Դ�ڵ���Ŀ��ڵ�����ӣ���Java NIO�и��𻺳��������ݵĴ��䣬Channel�����洢���ݣ���Ҫ��ϻ��������д���
 * 
 * ͨ������Ҫʵ����
 * java.nio.channel.Channel �ӿ�:
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 * 
 * ��ȡͨ��
 * 1.java���֧��ͨ�������ṩ��getChannel()����
 * 		����IO
 * 			FileInputStream/FileOutputStream
 * 			RandomAccessFile
 * 		����IO	
 * 			Socket
 * 			SreverSocket
 * 			DatagramSocket
 * 2.��Ը���ͨ���ṩ��̬����open()
 * 3.File�������newByteChannel()
 * 
 * ͨ��֮�����ݴ���
 * transferFrom()
 * trandferTo()
 * 
 * ��ɢ��Scatter����ۼ���Gatter��
 * ��ɢ��ȡ��ͨ���е����ݷ�ɢ�����������
 * �ۼ�д�룺��������������ݾۼ���ͨ����
 * 
 * �ַ�����CharSet
 * ����
 * ����
 * 
 * @author P1314023
 *
 */
public class NIOTest_1 {

	/**
	 * �ַ���
	 * @throws CharacterCodingException 
	 */
	@Test
	public void test6() throws CharacterCodingException{
		
		Charset cs1 = Charset.forName("GBK");
		//��ȡ������
		CharsetEncoder cEncoder = cs1.newEncoder();
		//��ȡ������
		CharsetDecoder cDecoder = cs1.newDecoder();
		
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		charBuffer.put("����������");
		charBuffer.flip();
		//����
		ByteBuffer buffer = cEncoder.encode(charBuffer);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(buffer.get());
		}
		//����
		buffer.flip();
		CharBuffer charBuffer2 = cDecoder.decode(buffer);
		
		System.out.println(charBuffer2.toString());
		
		System.out.println("------------------------");
		//����벻һ�£�����
		Charset charset = Charset.forName("UTF-8");
		buffer.flip();
		CharBuffer charBuffer3 = charset.decode(buffer);
		
		System.out.println(charBuffer3.toString());
		
		
	}
	
	
	@Test
	public void test5(){
		
		Map<String, Charset> map = Charset.availableCharsets();
		
		Set<Entry<String, Charset>> set = map.entrySet();
		
		for (Entry<String, Charset> entry : set) {
			
			System.out.println(entry.getKey() + "= " + entry.getValue());
			
		}
		
	}
	
	
	/**
	 * ��ɢ��Scatter����ۼ���Gatter��
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException{
		
		RandomAccessFile raf =  new RandomAccessFile("file/1.txt", "rw");
		
		FileChannel channel = raf.getChannel();
		
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		
		ByteBuffer[] byteBuffers  = {buffer1,buffer2};
		//��ɢ��ȡ
		channel.read(byteBuffers);
		
		for (ByteBuffer byteBuffer : byteBuffers) {
			byteBuffer.flip();
		}
		
		RandomAccessFile raf1 =  new RandomAccessFile("file/2.txt", "rw");
		
		FileChannel channel2 = raf1.getChannel();
		//�ۼ�д��
		channel2.write(byteBuffers);
		
		channel2.close();
		channel.close();
		
		raf1.close();
		raf.close();
		
		
	}
	
	/**
	 * ͨ��������ݴ���
	 * @throws IOException 
	 */
	@Test
	public void test2() throws IOException{
		
		FileChannel inChannel = FileChannel.open(Paths.get("file/1.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("file/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

		//inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());
		
		outChannel.close();
		inChannel.close();
		
	}
	
	/**
	 * ʹ��ֱ�ӻ���������ļ��ĸ��ƣ��ڴ�ӳ���ļ���
	 * @throws IOException 
	 */
	@Test
	public void test1() throws IOException{
		
		FileChannel inChannel = FileChannel.open(Paths.get("file/1.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("file/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		
		//�ڴ�ӳ���ļ�
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		//ֱ�ӻ������������ݵĶ�д����
		byte[] bs = new byte[inMappedBuf.limit()];
		inMappedBuf.get(bs);
		outMappedBuf.put(bs);
		
		inChannel.close();
		outChannel.close();
		
		
	}
	

	/**
	 * ����ͨ������ļ��ĸ��ƣ���ֱ�ӻ�������
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException{
		
		FileInputStream fis = null;
		FileOutputStream fos = null;

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		
		try {
			fis = new FileInputStream("file/1.txt");
			fos = new FileOutputStream("file/2.txt");
			//��ȡͨ��
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			//����ָ����С������
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//��ͨ�����ݴ��뻺����
			while (inChannel.read(buffer) != -1) {
				//�л�������ģʽ
				buffer.flip();
				//��������������д��ͨ��
				outChannel.write(buffer);
				//��ջ�����
				buffer.clear();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (outChannel != null) {
				outChannel.close();
			}
			if (inChannel !=  null) {
				inChannel.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		
		
		
		
	}
	
	
}
