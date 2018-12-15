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
 * 通道（Channel），用于源节点与目标节点的连接，在Java NIO中负责缓冲区中数据的传输，Channel本身不存储数据，需要配合缓冲区进行传输
 * 
 * 通道的主要实现类
 * java.nio.channel.Channel 接口:
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 * 
 * 获取通道
 * 1.java针对支持通道的类提供了getChannel()方法
 * 		本地IO
 * 			FileInputStream/FileOutputStream
 * 			RandomAccessFile
 * 		网络IO	
 * 			Socket
 * 			SreverSocket
 * 			DatagramSocket
 * 2.针对各个通道提供静态方法open()
 * 3.File工具类的newByteChannel()
 * 
 * 通道之间数据传输
 * transferFrom()
 * trandferTo()
 * 
 * 分散（Scatter）与聚集（Gatter）
 * 分散读取：通道中的数据分散到多个缓冲区
 * 聚集写入：多个缓冲区的数据聚集到通道中
 * 
 * 字符集：CharSet
 * 编码
 * 解码
 * 
 * @author P1314023
 *
 */
public class NIOTest_1 {

	/**
	 * 字符集
	 * @throws CharacterCodingException 
	 */
	@Test
	public void test6() throws CharacterCodingException{
		
		Charset cs1 = Charset.forName("GBK");
		//获取编码器
		CharsetEncoder cEncoder = cs1.newEncoder();
		//获取解码器
		CharsetDecoder cDecoder = cs1.newDecoder();
		
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		charBuffer.put("所发生的你");
		charBuffer.flip();
		//编码
		ByteBuffer buffer = cEncoder.encode(charBuffer);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(buffer.get());
		}
		//解码
		buffer.flip();
		CharBuffer charBuffer2 = cDecoder.decode(buffer);
		
		System.out.println(charBuffer2.toString());
		
		System.out.println("------------------------");
		//编解码不一致，乱码
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
	 * 分散（Scatter）与聚集（Gatter）
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException{
		
		RandomAccessFile raf =  new RandomAccessFile("file/1.txt", "rw");
		
		FileChannel channel = raf.getChannel();
		
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		
		ByteBuffer[] byteBuffers  = {buffer1,buffer2};
		//分散读取
		channel.read(byteBuffers);
		
		for (ByteBuffer byteBuffer : byteBuffers) {
			byteBuffer.flip();
		}
		
		RandomAccessFile raf1 =  new RandomAccessFile("file/2.txt", "rw");
		
		FileChannel channel2 = raf1.getChannel();
		//聚集写入
		channel2.write(byteBuffers);
		
		channel2.close();
		channel.close();
		
		raf1.close();
		raf.close();
		
		
	}
	
	/**
	 * 通道间的数据传输
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
	 * 使用直接缓冲区完成文件的复制（内存映射文件）
	 * @throws IOException 
	 */
	@Test
	public void test1() throws IOException{
		
		FileChannel inChannel = FileChannel.open(Paths.get("file/1.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("file/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		
		//内存映射文件
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		//直接缓冲区进行数据的读写操作
		byte[] bs = new byte[inMappedBuf.limit()];
		inMappedBuf.get(bs);
		outMappedBuf.put(bs);
		
		inChannel.close();
		outChannel.close();
		
		
	}
	

	/**
	 * 利用通道完成文件的复制（非直接缓冲区）
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
			//获取通道
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			//分配指定大小缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//将通道数据存入缓冲区
			while (inChannel.read(buffer) != -1) {
				//切换读数据模式
				buffer.flip();
				//将缓冲区的数据写入通道
				outChannel.write(buffer);
				//清空缓冲区
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
