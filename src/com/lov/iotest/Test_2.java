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
 * �ļ��ָ���ϲ�
 * ˼·��
 * 1���ָ�׼��
 *  1������
 *  2��ȷ��ÿ���С
 *  3��ÿ�������
 * 2���ָ�
 * 	1���ڼ���
 * 	2��ÿ������
 * 	3��ʵ�ʴ�С
 * 
 * @author P1314023
 *
 */
/**
 * @author P1314023
 *
 */
public class Test_2 {

	//�ļ�·��
	private String filePath;
	//�ļ���
	private String fileName;
	//�ļ���С
	private long length;
	//����
	private int size;
	//ÿ��Ĵ�С
	private long blockSize;
	//�ָ��Ŀ¼
	private String destBlockPath;
	//ÿ�������
	private List<String> blockPath;
	
	public Test_2() {
		blockPath = new ArrayList<String>();
		init();
	}
	public Test_2(String filePath,String destBlockPath) {
		this(filePath,1024,destBlockPath);
		
	}
	
	/**
	 * @param filePath	�ָ�Դ�ļ�
	 * @param blockSize	�ָ��С
	 * @param destBlockPath	�ָ����Ŀ¼
	 */
	public Test_2(String filePath,long blockSize,String destBlockPath) {
		this();
		this.filePath = filePath;
		this.blockSize = blockSize;
		this.destBlockPath = destBlockPath;
		init();//����ʱ��ʼ��
	}
	

	/**
	 * ��ʼ�������������ȷ���ļ���
	 */
	private void init(){
		File src = null;
		//�����ļ������ڣ�����
		if (null == filePath || !((src = new File(filePath)).exists())) {
			return;
		}//�����ļ�ΪĿ¼������
		if (src.isDirectory()) {
			return;
		}
		//�ļ���
		fileName = src.getName();
		//�ļ���С
		length = src.length();
//		System.out.println(length);
		//�������С
		if (this.blockSize > length) {
			blockSize = length;
		}
		//ȷ������
		size = (int)(Math.ceil(length*1.0/blockSize));
		initPathName();
	}
	
	//��ʼ���ָ��ļ�·��
	private void initPathName(){
		for (int i = 0; i < size; i++) {
			blockPath.add(destBlockPath+""+fileName+".part"+i);
		}
	}
	
	/**
	 * �ļ��ķָ�
	 * 1���ڼ���
	 * 2����ʼλ��
	 * 3��ʵ�ʴ�С
	 * @param destPath	�ָ��Ŀ��Ŀ¼
	 */
	public void split(){
		//��ʼ�ָ�λ��
		long beginPos = 0 ;
		//ÿ���ʵ�ʴ�С
		long actualBlockSize = blockSize;
		//�ָ�
		for (int i = 0; i < size; i++) {
			//�ж����һ�飬����ʣ���СС�ڷָ��С
			if (i == size-1) {
				actualBlockSize = length - beginPos;
			}
			splitDetail(i, beginPos, actualBlockSize);
			beginPos += actualBlockSize;//��һ�ηָ����
		}
		
	}
	
	
	/**
	 * 
	 * ����ָ�ʵ��
	 * @param idx �ڼ���
	 * @param beginPos ��ʼ�ָ��
	 * @param actualBlockSize ʵ�ʷָ��С
	 */
	private void splitDetail(int idx,long beginPos,long actualBlockSize){
		//����Դ�ļ�
		File src = new File(filePath);
		//����Ŀ���ļ�
		File dest = new File(blockPath.get(idx));
		
//		RandomAccessFile raf = null;
//		BufferedOutputStream bos = null;
		try(
				//ֻ����ȡԴ�ļ�
				RandomAccessFile raf= new RandomAccessFile(src, "r");
		
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(dest));
				
				){
			
			raf.seek(beginPos);//�Ӹõ㿪ʼ��ȡ����
			byte[] flush = new byte[1024];//������
			int len = 0;//ÿ�ζ�ȡ�ĳ���
			
			while (-1!=(len = raf.read(flush))) {
				//�ж��Ƿ�ﵽ��Ĵ�Сֵ
				if (actualBlockSize-len >= 0) {
					//д��
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
	 * �ļ��ĺϲ�
	 * @param destPath
	 * @throws IOException 
	 */
	public void mergeFile(String destPath) throws IOException{
		//�ϲ�����ļ�
		File dest = new File(destPath);
		BufferedOutputStream bos = null;
//		BufferedInputStream bis = null;
		
		Vector<InputStream> vi = new Vector<>();
		SequenceInputStream sis = null;//���յ������ϲ�Ϊһ����
		try{
				bos = new BufferedOutputStream(
						new FileOutputStream(dest,true));//����׷��
				
				for (int i = 0; i < blockPath.size(); i++) {
					vi.add(new BufferedInputStream(new FileInputStream(new File(blockPath.get(i)))));
					System.out.println(blockPath.get(i));
				}
				
				sis = new SequenceInputStream(vi.elements());
					//������
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
