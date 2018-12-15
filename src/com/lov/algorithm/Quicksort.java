package com.lov.algorithm;

import java.util.stream.Stream;

/**
 * @author Administrator
 * øÏÀŸ≈≈–Ú(Quicksort)
 *
 */
public class Quicksort {

	public static void main(String[] args) {
		
		int[] sort = {5,3,6,7,3,8,0,34,4,6,9,200};
		
		quickSort(sort, 0, sort.length-1);
		for (int i = 0; i < sort.length; i++) {
			System.out.print(sort[i]+",");
			
		}
		
	}
	
	public static void quickSort(int[] sort,int left,int right) {
		if (left <=  right) {
			
			int i = bySort(sort, left, right);
			quickSort(sort, left, i-1);
			quickSort(sort, i+1, right);
		}
		
		
	}
	
	public  static int bySort(int[] sort,int left,int right) {
		
		
		int l = left;
		int r =right;
		int flag = sort[left];
		
		while (l < r) {
			
			while (l<r) {
				if (sort[r] < flag) {
					sort[l++] = sort[r];
					break;
				}
				r--;
			}
			
			while (l<r) {
				if (sort[l] > flag) {
					sort[r--] = sort[l];
					break;
				}
				l++;
			}
		}
		
		sort[l] = flag;
		return l; 
		
	}
	
}
