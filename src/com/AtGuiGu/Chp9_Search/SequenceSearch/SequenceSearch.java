package com.AtGuiGu.Chp9_Search.SequenceSearch;

import java.util.Arrays;

/*
 * @Description: 线性查找
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/23 14:29
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] search = new int[]{1,23,0,100,90,0,108};
        int[] resultArr = new int[search.length];
        int searchVal = 0;
        seqSearch(search,0,resultArr);
        System.out.println(Arrays.toString(resultArr));
    }

    public static void seqSearch(int[] search, int searchVal, int[] resultArr){
        //存放结果数组的索引,代表待查找数组里有几个相同目标值
        int index = 0;
        for(int i=0; i<search.length; i++){
            if(search[i] == searchVal){
                resultArr[index] = i;
                index++;
            }
        }
    }
}
