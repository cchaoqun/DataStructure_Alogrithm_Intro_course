package com.AtGuiGu.Chp9_Search.FibonacciSearch;

import java.util.Arrays;

/*
 * @Description: 斐波那契查找练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 10:16
 */
public class FibTest {
    public static void main(String[] args) {
        int[] arr = new int[]{1,8, 10, 89, 1000, 1234};
//        System.out.println(Arrays.toString(fib()));
        int ind = fibSearch1224(arr,0,arr.length-1, 0);
        System.out.println("searchIndex= "+ind);
    }
    static int maxSize = 20;
    public static int[] fibonacci(){
        int[] fibo = new int[maxSize];
        fibo[0] = 1;
        fibo[1] = 1;
        for(int i=2; i<maxSize; i++){
            fibo[i] = fibo[i-1] + fibo[i-2];
        }
        return fibo;
    }


    public static int fibSearch1224(int[] fib, int left, int right, int val){
        if(left > right){
            return -1;
        }
        int len = fib.length;
        int k = 0;
        int[] F = fibonacci();
        while(len > F[k]-1){
            k++;
        }
        int[] temp = Arrays.copyOf(fib, F[k]);
        for(int i=len; i<F[k]; i++){
            temp[i] = fib[len-1];
        }

        while(left<=right){
            int gold = left + F[k-1]-1;
            int goldVal = temp[gold];

            if(val<goldVal){
                right = gold-1;
                k = k-1;
            }else if(val>goldVal){
                left = gold+1;
                k = k-2;
            }else{
                if(gold<=right){
                    return gold;
                }else{
                    return right;
                }
            }
        }
        return -1;

    }
}
