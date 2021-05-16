package com.AtGuiGu.Chp8_Sort.Radix;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/*
 * @Description: 基数排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 8:48
 */
public class Radix {
    public static void main(String[] args) {
        int[] radix = new int[]{53, 3, 542, 748, 14, 214};
        radixSort(radix);
        System.out.println(Arrays.toString(radix));

        efficiencyTest();
    }

    public static void radixSort(int[] radix){
        //找到数组中最大的数
        int maxVal = radix[0];
        int len = radix.length;
        for(int i=1; i<len; i++){
            if (radix[i] > maxVal) {
                maxVal = radix[i];
            }
        }

        //获取数组最大值的位数
        int maxLen = (maxVal+"").length();

        //初始化存放数据的二维数组(桶),每一行代表一个桶 从0-9共10行,每一行的不同列代表存放的数据
        //列数为待排序数组的长度
        int[][] bucket = new int[10][len];

        //初始化存放每个桶索引下标的一位数组,长度为桶的数量,数字代表存放了几个数据
        int[] bucketElementCounts = new int[10];

        //依次获取每个元素的最小位到最大位数字
        for(int i=0,n=1; i<maxLen; i++, n*=10){
            //遍历依次获取待排序数组元素
            for (int j = 0; j < len; j++) {
                //获取指定位置上的数字即需要存放进入的桶的下标
                int bucketNum = radix[j] / n % 10;
                //将该元素放入对应的桶的第个位置,存放位置根据对应桶的下标确定
                bucket[bucketNum][bucketElementCounts[bucketNum]] = radix[j];
                //存放一个数据,对应桶的下标后移一位
                bucketElementCounts[bucketNum]++;
            }
            //索引,代表已经取出了几个数据放入原数组
            int index = 0;
            //数据全部存放入桶后,按顺序从桶中取出数据放入原数组
            for(int k=0; k<10; k++){
                //通过判断桶中的数据数量确定是否需要取数据
                if(bucketElementCounts[k] != 0){
                    //根据对应桶的数量,通过循环将数据取出
                    for(int l=0; l<bucketElementCounts[k]; l++){
                        //存入一个数据, index后移一位
                        radix[index++] = bucket[k][l];
                    }
                    //当一个桶的数据取完了以后,将该桶的下标索引重置到0
                    bucketElementCounts[k] = 0;
                }
            }
        }
    }

    //获取当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //测试排序效率
    public static void efficiencyTest() {
        int[] randomArr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            randomArr[i] = (int) (Math.random() * 800000000);
        }

        System.out.println("排序前时间为: " + currentTime());
        long start = System.currentTimeMillis();
        radixSort(randomArr);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间为: " + currentTime());
//        System.out.println("排序后数组为: "+Arrays.toString(randomArr));
        System.out.println("排序用时: " + (end - start) + "毫秒");
    }
}
