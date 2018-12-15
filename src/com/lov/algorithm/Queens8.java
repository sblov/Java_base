package com.lov.algorithm;

import javax.xml.soap.Detail;

/**
 * @author Administrator
 *�˻ʺ�Queens8��
 *
 */
public class Queens8 {

	private static int[][] maps;

	public static void main(String[] args) {
		
		maps = new int[8][8];
		
		//show(map);
		detail(0);
	
	
	}
	
	
	static void detail(int q) {
		int row = q;
		for(int col = 0 ;  col < maps.length;  col++) {
			if (check(row, col)) {
				maps[row][col] = 1;
				
				if (row==7) {
					
					show(maps);
					//��ǰ�ⷨ�����������ǰ���һ���Ļʺ�
				}else {
					
					detail(row+1);
					//��һ�ж������У���ǰ�еĻʺ�����������´α���
				}
				//�����ȡ
				maps[row][col] = 0;
			}
			
			
		}
		
	}
	
	public static boolean check(int row,int col) {
		
		for (int i = row; i >= 0; i--) {
			if (maps[i][col] == 1) {
				return false;
			}
		}
		for (int i = row-1,j = col-1; i >=0 && j>=0; i--,j--) {
			
				if (maps[i][j] == 1) {
					return false;
				
			}
		}
		for (int i = row-1,j = col+1; i >=0 && j< maps.length; i--,j++) {
				if (maps[i][j] == 1) {
					return false;
				
			}
		}
		
		
		return true;
		
	}
	
	static int count = 0;
	public static void show(int[][] map) {
		
		System.out.println("---"+count+"---");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]+" ");
				
			}
			System.out.println();
			
		}
		count++;
	}
	
}
