package com.lov.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestPoiExcel {

	private static FileOutputStream fos;

	public static void main(String[] args) {
		
		//创建新工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建新工作表
		HSSFSheet  sheet = workbook.createSheet("hello");
		//创建行
		HSSFRow row  =sheet.createRow(0);
		//创建单元格
		HSSFCell cell = row.createCell(2);
		//设置单元格的值
		cell.setCellValue("hello sheet");
		
		//输出文件
		try {
			fos = new FileOutputStream(new File("d:/11.xls"));
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
    public void testReadExcel() throws Exception
    {
        //创建输入流
        FileInputStream fis = new FileInputStream(new File("d:/11.xls"));
        //通过构造函数传参
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        //获取工作表
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
        HSSFRow row = sheet.getRow(0);
        //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
        HSSFCell cell = row.getCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        String cellValue = cell.getStringCellValue();
        System.out.println("第一行第三列的值是"+cellValue);
       
        fis.close();
    }
	
	
}
