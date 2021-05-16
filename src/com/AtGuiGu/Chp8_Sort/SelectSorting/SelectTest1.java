package com.AtGuiGu.Chp8_Sort.SelectSorting;

import java.util.Arrays;

public class SelectTest1 {
    public static void main(String[] args) {
        //创建待排序数组
        int[] select = new int[]{101, 34, 119, 1, -2, -101, 70};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(select));
        int[] result = selectSort(select);
        System.out.printf("排序后数组: %s\n", Arrays.toString(result));
    }

    public static int[] selectSort(int[] select){
        //初始化变量用于存放检索时最小值元素的信息
        int minVal = 0;
        int minIndex = 0;
        int temp = 0;
        int len = select.length;
        int round = len-1;
        boolean flag = false;
        for(int i=0; i<round; i++){
            //假设本轮初始遍历位置为最小值
            minVal = select[i];
            minIndex = i;
            //将后续位置元素与最小值比较,遇到更小的则记录最小值与下标
            for(int j=i+1; j<len; j++){
                //判断是否小于minVal
                if(select[j]<minVal){
                    //判断本轮初始位置是否为最小值
                    flag = true;
                    minVal = select[j];
                    minIndex = j;
                }
            }
            //判断初始位置是否为最小值
            if(flag){
                //交换
                temp = select[i];
                select[i] = minVal;
                select[minIndex] = temp;
                //重置flag
                flag = false;
            }
            //flag = false 本轮初始值即为最小值
            System.out.printf("第%d轮排序后结果为: %s\n",i+1, Arrays.toString(select));
        }

        return select;
    }
}
