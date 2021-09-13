package org.test;

import java.util.Arrays;
import java.util.Map;
import java.util.UnknownFormatConversionException;

//20 100=最大公约数*最大公倍数
// 100%20=0;20,100
// 4 6 6%4=2 4%2=0 4*6/2=12
public class Test {
    //最大公约数，最小公倍数
    public static int result(int m,int n){
        int k = 0;//余数
        if(n < m){
            int temp = m;
            m = n;
            n = temp;
        }
        while (m != 0){
            k = n%m;
            n = m;
            m = k;
        }
        return n;
    }


    //判断是否是质数
    public static boolean isPrime(int n){
//        10 = 2 * 5 = 5 * 2
    if (n <= 1) return  false;
    if (n == 2 || n == 3) return true;
        for (int i = 0; i < Math.sqrt(n); i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
    //123456
    //654321
    public static int[][] search(int m ,int n){
        int [][] arr = new int[m][n];
        int flag = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == n-1){
                    flag = -flag;
                }
                arr[i][j] +=flag;
            }
        }
        return arr;
    }

    //Miller-Rabin算法求质数

    public static void main(String[] args) {
//        int m = 100,n = 20;
//        System.out.println(result(m,n));
//        System.out.println(m*n/result(m,n));
        int[][] search = search(3, 4);
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <4 ; j++) {
                System.out.println(search[i][j]);
            }
        }
    }

}
