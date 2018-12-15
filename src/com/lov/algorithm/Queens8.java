package com.lov.algorithm;

import javax.xml.soap.Detail;

/**
 * @author Administrator
 *八皇后（Queens8）
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
					//当前解法结束，清除当前最后一个的皇后
				}else {
					
					detail(row+1);
					//下一行都不可行，当前行的皇后清除，进行下次遍历
				}
				//清除提取
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
