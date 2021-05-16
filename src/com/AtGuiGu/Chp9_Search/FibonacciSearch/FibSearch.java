package com.AtGuiGu.Chp9_Search.FibonacciSearch;

import java.util.Arrays;

/*
 * @Description: 斐波那契查找
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/23 17:32
 */
public class FibSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,8, 10, 89, 1000, 1234};
//        System.out.println(Arrays.toString(fib()));
        int ind = fibSearch(arr,123445);
        System.out.println("searchIndex= "+ind);
    }

    //构建斐波那契数列
    static int maxSize = 20;
    public static int[] fib(){
        int[] fibArr = new int[maxSize];
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibArr[i] = fibArr[i-1] + fibArr[i-2];
        }
        return fibArr;
    }

    public static int fibSearch(int[] array, int val){
        //数组左索引
        int left = 0;
        //数组右索引
        int right = array.length-1;
        //黄金分割点下标
        int gold = 0;
        int k = 0;
        //获取斐波那契数列
        int[] f = fib();
        //获取与待查找数组长度匹配的斐波那契数
        while(right > f[k]-1){
            k++;
        }
        //如果数组长度小于斐波那契数,需要对数组进行补齐
        int[] suppArr = Arrays.copyOf(array,f[k]);
        //需要数组有序,用数组最高位数据进行补齐
        for(int i=right+1; i<suppArr.length; i++){
            suppArr[i] = array[right];
        }

        //开始查找
        while(left <= right){
            //黄金分割点下标为数组长度前一个斐波那契数-1
            gold = left+f[k-1]-1;
            if(val < suppArr[gold]){
                //向左查找
                right = gold-1;
                //当前数组长度为f[k] = f[k-1] + f[k-2]
                //目标数落在左边的部分, 左边部分长度为f[k-1] 所以对 f[k-1]部分继续使用斐波那契数查找
                //k变成k-1
                k = k-1;
            }else if(val > suppArr[gold]){
                //向右查找
                left = gold+1;
                //当前数组长度为f[k] = f[k-1] + f[k-2]
                //目标数落在左边的部分, 右边部分长度为f[k-2]所以对 f[k-2]部分继续使用斐波那契数查找
                //k变成k-2
                k = k-2;
            }else{
                //找到匹配的目标值
                //此处需要判断找到的下标是否大于原数组的长度
                //因为我们对原数组利用最大值即数组最后一个值进行了补齐,
                //有可能查找到的下标落在了补齐部分
                if(gold <= right){
                    //在原始数组长度范围内
                    return gold;
                }else{
                    //在补齐的部分,目标值等于原始数组最右边的值
                    return right;
                }
            }
        }
        //退出循环未返回说明未找到
        return -1;

    }
}
