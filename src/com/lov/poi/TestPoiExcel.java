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
		
		//�����¹�����
		HSSFWorkbook workbook = new HSSFWorkbook();
		//�����¹�����
		HSSFSheet  sheet = workbook.createSheet("hello");
		//������
		HSSFRow row  =sheet.createRow(0);
		//������Ԫ��
		HSSFCell cell = row.createCell(2);
		//���õ�Ԫ���ֵ
		cell.setCellValue("hello sheet");
		
		//����ļ�
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
        //����������
        FileInputStream fis = new FileInputStream(new File("d:/11.xls"));
        //ͨ�����캯������
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        //��ȡ������
        HSSFSheet sheet = workbook.getSheetAt(0);
        //��ȡ��,�к���Ϊ�������ݸ�getRow����,��һ�д�0��ʼ����
        HSSFRow row = sheet.getRow(0);
        //��ȡ��Ԫ��,row�Ѿ�ȷ�����к�,�к���Ϊ�������ݸ�getCell,��һ�д�0��ʼ����
        HSSFCell cell = row.getCell(2);
        //���õ�Ԫ���ֵ,��C1��ֵ(��һ��,������)
        String cellValue = cell.getStringCellValue();
        System.out.println("��һ�е����е�ֵ��"+cellValue);
       
        fis.close();
    }
	
	
}
