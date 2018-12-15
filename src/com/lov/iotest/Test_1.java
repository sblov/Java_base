package com.lov.iotest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;




//字符流
public class Test_1 {

	
	//纯文本读取
	@Test
	public void Test_1() throws IOException{

		
		File src = new File("file/test.txt");
		
		
		//try-with-resources ,自动关闭声明对象
		try (
			
			Reader reader = new FileReader(src);
		){
			char[] flash = new char[10];
			int len = 0;
			while ((len= reader.read(flash))!=-1) {
				String string = new String(flash, 0, len);
				System.out.println(string);
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		

		
	}
	
	//写纯文本
	@Test
	public void Test_2() throws IOException{
		
		File file  = new File("file/test2.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		 Writer writer = null;
		 
		 writer = new FileWriter(file);
		 String string = "hello";
		 writer.write(string);
		 writer.append("file");
		 writer.flush();
		 
		 if (writer!=null) {
			writer.close();
		}
		
		
	}
	
	
	
}
