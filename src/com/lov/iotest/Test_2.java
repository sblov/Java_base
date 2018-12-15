package com.lov.iotest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



/**
 * 
 * 文件分割与合并
 * 思路：
 * 1、分割准备
 *  1）块数
 *  2）确定每块大小
 *  3）每块的名称
 * 2、分割
 * 	1）第几块
 * 	2）每块的起点
 * 	3）实际大小
 * 
 * @author P1314023
 *
 */
/**
 * @author P1314023
 *
 */
public class Test_2 {

	//文件路径
	private String filePath;
	//文件名
	private String fileName;
	//文件大小
	private long length;
	//块数
	private int size;
	//每块的大小
	private long blockSize;
	//分割后目录
	private String destBlockPath;
	//每块的名称
	private List<String> blockPath;
	
	public Test_2() {
		blockPath = new ArrayList<String>();
		init();
	}
	public Test_2(String filePath,String destBlockPath) {
		this(filePath,1024,destBlockPath);
		
	}
	
	/**
	 * @param filePath	分割源文件
	 * @param blockSize	分割大小
	 * @param destBlockPath	分割后存放目录
	 */
	public Test_2(String filePath,long blockSize,String destBlockPath) {
		this();
		this.filePath = filePath;
		this.blockSize = blockSize;
		this.destBlockPath = destBlockPath;
		init();//构造时初始化
	}
	

	/**
	 * 初始化，计算块数，确定文件名
	 */
	private void init(){
		File src = null;
		//关联文件不存在，返回
		if (null == filePath || !((src = new File(filePath)).exists())) {
			return;
		}//关联文件为目录，返回
		if (src.isDirectory()) {
			return;
		}
		//文件名
		fileName = src.getName();
		//文件大小
		length = src.length();
//		System.out.println(length);
		//修正块大小
		if (this.blockSize > length) {
			blockSize = length;
		}
		//确定块数
		size = (int)(Math.ceil(length*1.0/blockSize));
		initPathName();
	}
	
	//初始化分割文件路径
	private void initPathName(){
		for (int i = 0; i < size; i++) {
			blockPath.add(destBlockPath+""+fileName+".part"+i);
		}
	}
	
	/**
	 * 文件的分割
	 * 1、第几块
	 * 2、起始位置
	 * 3、实际大小
	 * @param destPath	分割后目标目录
	 */
	public void split(){
		//起始分割位置
		long beginPos = 0 ;
		//每块的实际大小
		long actualBlockSize = blockSize;
		//分割
		for (int i = 0; i < size; i++) {
			//判断最后一块，可能剩余大小小于分割大小
			if (i == size-1) {
				actualBlockSize = length - beginPos;
			}
			splitDetail(i, beginPos, actualBlockSize);
			beginPos += actualBlockSize;//下一次分割起点
		}
		
	}
	
	
	/**
	 * 
	 * 具体分割实现
	 * @param idx 第几块
	 * @param beginPos 起始分割点
	 * @param actualBlockSize 实际分割大小
	 */
	private void splitDetail(int idx,long beginPos,long actualBlockSize){
		//关联源文件
		File src = new File(filePath);
		//关联目标文件
		File dest = new File(blockPath.get(idx));
		
//		RandomAccessFile raf = null;
//		BufferedOutputStream bos = null;
		try(
				//只读读取源文件
				RandomAccessFile raf= new RandomAccessFile(src, "r");
		
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(dest));
				
				){
			
			raf.seek(beginPos);//从该点开始读取数据
			byte[] flush = new byte[1024];//缓冲区
			int len = 0;//每次读取的长度
			
			while (-1!=(len = raf.read(flush))) {
				//判断是否达到块的大小值
				if (actualBlockSize-len >= 0) {
					//写出
					bos.write(flush,0,len);
					actualBlockSize -= len;
				}else {
					bos.write(flush, 0, (int)actualBlockSize);
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
 		
	}
	
	/**
	 * 文件的合并
	 * @param destPath
	 * @throws IOException 
	 */
	public void mergeFile(String destPath) throws IOException{
		//合并后的文件
		File dest = new File(destPath);
		BufferedOutputStream bos = null;
//		BufferedInputStream bis = null;
		
		Vector<InputStream> vi = new Vector<>();
		SequenceInputStream sis = null;//将收到的流合并为一个流
		try{
				bos = new BufferedOutputStream(
						new FileOutputStream(dest,true));//允许追加
				
				for (int i = 0; i < blockPath.size(); i++) {
					vi.add(new BufferedInputStream(new FileInputStream(new File(blockPath.get(i)))));
					System.out.println(blockPath.get(i));
				}
				
				sis = new SequenceInputStream(vi.elements());
					//缓冲区
				byte[] flush = new byte[1024];
				int len = 0;
				while (-1!=(len = sis.read(flush))) {
//					System.out.println(new String(flush,0,len));
					bos.write(flush, 0, len);
				}
					
				bos.flush();
				
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			
			if (bos!=null && sis!=null) {
				sis.close();
				bos.close();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Test_2 test_2 = new Test_2("file/test.txt", 70,"file/");
		//System.out.println(test_2.size);
//		test_2.split();
		test_2.mergeFile("file/test-1.txt");
	}

}
