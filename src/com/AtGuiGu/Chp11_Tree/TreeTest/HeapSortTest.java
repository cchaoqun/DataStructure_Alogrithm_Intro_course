package com.AtGuiGu.Chp11_Tree.TreeTest;

import java.util.Arrays;

/*
 * @Description: 堆排序练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/28 9:00
 */
public class HeapSortTest {
    public static void main(String[] args) {
        int temp = 0;
        int[] arr = new int[]{4,6,8,5,9,2,3,10,11,-999,20,21,4,5,10,1000,2};
        int len = arr.length;
        for(int i=len/2-1; i>=0; i--){
            adjustHeap1228(arr,i,len);
        }

        for(int j=len-1; j>=0; j--){
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap1228(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }



    public static void adjustHeap1228(int[] arr, int i, int len){
        int temp = arr[i];
        for(int k=2*i+1; k<len; k=2*k+1){
            if(k+1 < len && arr[k] < arr[k+1]){
                k++;
            }
            if(arr[i]<arr[k]){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
            arr[i] = temp;
        }

    }
}
