package com.AtGuiGu.Chp8_Sort.Merge;

import java.util.Arrays;

public class MergeTest {
    public static void main(String[] args) {
        int[] merge = new int[]{8,4,5,7,1,3,6,2};
        int[] tempArr = new int[merge.length];
        mergeSort1223(merge,0,merge.length-1, tempArr);
        System.out.println(Arrays.toString(merge));
    }

    /*
     * @Description:
     *
     * @param null
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/23 10:23
     */
    public static void mergeSort1223(int[] merge, int left, int right, int[] tempArr){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort1223(merge, 0,mid,tempArr);
            mergeSort1223(merge, mid+1,right,tempArr);
            merge(merge, left,mid,right,tempArr);
        }


    }


    public static void merge(int[] merge, int left, int mid, int right, int[] tempArr){
        int i = left;
        int j = mid+1;
        int t = 0;
        while(i<=mid && j<=right){
            if(merge[i] <= merge[j]){
                tempArr[t] = merge[i];
                t++;
                i++;
            }else{
                tempArr[t] = merge[j];
                t++;
                j++;
            }
        }
        if(i>mid){
            while(j<=right){
                tempArr[t] = merge[j];
                t++;
                j++;
            }
        }else{
            while(i<=mid){
                tempArr[t] = merge[i];
                t++;
                i++;
            }
        }
        t=0;
        int mergeleft = left;
        while(mergeleft<=right){
            merge[mergeleft] = tempArr[t];
            mergeleft++;
            t++;
        }

    }
}
