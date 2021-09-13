package org.test;


import javafx.scene.chart.NumberAxis;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.ref.SoftReference;
import java.util.Arrays;

public class Test02 {
    public int onequickSort(int[] nums,int begin,int end){
        int l = begin;
        int r = end;
        int key = nums[begin];
        while(l<r){
            while(l<r&&nums[r]>=key)
                r--;
            while(l<r&&nums[l]<=key)
                l++;
            if(l<r)
                swap(nums,l,r);
        }
        swap(nums,l,begin);
        return l;
    }

    public int[] quickSort(int[] nums,int begin,int end){
        if(begin<end){
            int q = onequickSort(nums,begin,end);
            System.out.println(q);
            quickSort(nums,begin,q-1);
            quickSort(nums,q+1,end);
        }
        return nums;
    }

    public void swap(int []nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,3,11,15,34,89,11};
//        int n = nums.length;
//        int count  = 0;
//        while (count < 10){
//            for (int i = 0; i < n; i++) {
//                System.out.print(i + " ");
//                count++;
//                if (count == 10) break;
//                if (i == n-1) continue;
//            }
//        }
//        int sum = new Test02().sum(nums, 0, nums.length - 1);
//        System.out.println(sum);
        new Test02().bubbleSort(nums,0,nums.length-2);
        System.out.println(Arrays.toString(nums));
    }
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                char c1 = text1.charAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    char c2 = text2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[m][n];
        }
    }
    public int sum(int []nums,int i ,int j){
        if ( i == j){
            return nums[i];
        }else{
            return nums[i] + sum(nums,i+1,j);
        }
    }
    public void bubbleSort(int [] nums,int i,int j){
        if (i == j) {
            return;
        }
        for (int k = i; i < j; k++) {
            if (nums[k] > nums[k+1]){
                int temp = nums[k];
                nums[k] = nums[k+1];
                nums[k+1] = temp;
            }
        }
        bubbleSort(nums,i,j-1);
    }
}
