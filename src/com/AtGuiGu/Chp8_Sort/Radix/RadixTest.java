package com.AtGuiGu.Chp8_Sort.Radix;

import java.util.Arrays;

/*
 * @Description: Radix Sort Practice
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 8:56
 */
public class RadixTest {
    public static void main(String[] args) {
        int[] radix = new int[]{53, 3, 542, 748, 14, 214};
        radixSort1224(radix);
        System.out.println(Arrays.toString(radix));

    }

    public static void radixSort1224(int[] radix){
        int len = radix.length;
        int maxVal = radix[0];
        //找最大值
        for(int i=1; i<len; i++){
            if(radix[i] > maxVal){
                maxVal = radix[i];
            }
        }
        //获取最大值长度
        int maxLen = (maxVal+"").length();
        //桶 二维数组
        int[][] bucket = new int[10][len];
        //桶中存放数据数量 一维数组
        int[] bucketIndex = new int[10];
        for(int j=0,n=1; j<maxLen; j++,n*=10){
            for(int k=0; k<len; k++){
                //获取对应位置数字
                int num = radix[k] / n % 10;
                //入桶
                bucket[num][bucketIndex[num]] = radix[k];
                bucketIndex[num]++;
            }
            //本轮入桶结束，按顺序出桶
            int originIndex = 0;
            for(int m=0; m<10; m++){
                if(bucketIndex[m] != 0){
                    for(int index=0; index<bucketIndex[m]; index++){
                        radix[originIndex++] = bucket[m][index];
                    }
                    //每个桶取完数据后，下标重置
                    bucketIndex[m] = 0;
                }

            }
        }
    }
}
