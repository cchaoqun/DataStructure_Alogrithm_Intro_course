package com.AtGuiGu.Chp8_Sort.Quick;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 快速排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/22 15:30
 */
public class QuickSort {
    public static void main(String[] args) {
        //创建待排序数组
        int[] quick = new int[]{-9,78,0,23,-567,70};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(quick));
        quickSort(quick, 0, quick.length-1 );
        System.out.printf("排序后数组: %s\n", Arrays.toString(quick));

        //测试快速排序效率
        System.out.println("测试快速排序效率");
        efficiencyTest();
    }

//

    public static void quickSort(int[] quick, int left, int right){
        int l = left;
        int r = right;

        //中轴
        int pivot = quick[(right + left)/2];
        //中间变量
        int temp = 0;

        //左指针 < 右指针 则继续循环
        while(l<r){

            //左边找到一个大于中轴得元素
            while(quick[l] < pivot){
                //找到才退出循环,否则继续向右遍历
                l++;
            }
            //右边边找到一个小于中轴得元素
            while(quick[r] > pivot){
                //找到才退出循环,否则继续向左遍历
                r--;
            }

            //如果 左指针 > 右指针 说明左右已经完全按顺序排序
            if( l >= r){
                break;
            }

            //左指针 < 右指针  则将找到的两个元素进行交换
            temp = quick[l];
            quick[l] = quick[r];
            quick[r] = temp;

            //判断左边交换后的元素是否与 pivot相等,相等则有可能右边还有与pivot相等的元素,因此 r--, 右指针再向左移一位
            if(quick[l] == pivot){
                r--;
            }
            //判断右边交换后的元素是否与 pivot相等,相等则有可能左边还有与pivot相等的元素,因此 l++, 左指针再向右移一位
            if(quick[r] == pivot){
                l++;
            }

            //完成交换后 判断两个指针是否相等, 如果相等则将指针错开
            if( l == r) {
                l++;
                r--;
            }
            //在左边递归继续上述步骤
            if(left < r){
                quickSort(quick, left, r);
            }
            //在右边递归继续上述步骤
            if(right > l){
                quickSort(quick, l, right);
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
    public static void efficiencyTest(){
        int[] randomArr = new int[80000];
        for(int i=0; i<80000; i++){
            randomArr[i] = (int) (Math.random()*8000000);
        }

        System.out.println("排序前时间为: "+currentTime());
        long start = System.currentTimeMillis();
        quickSort(randomArr, 0, randomArr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间为: " + currentTime());
        System.out.println("排序用时: "+(end-start)+"毫秒");
//        System.out.println(Arrays.toString(randomArr));

    }

//    public static void quickSort2(int[] quick, int left, int right){
//        int l = left;
//        int r = right;
//        int temp = 0;
//        int pivot = quick[r];
//
//        while(l <= r){
//            while(l<=r && quick[l] < pivot){
//                l++;
//            }
//            while(l<=r && quick[r] > pivot){
//                r--;
//            }
//
//            if(l <= r){
//                temp = quick[l];
//                quick[l] = quick[r];
//                quick[r] = temp;
//                l++;
//                r--;
//            }
//        }
//        temp = quick[l];
//        quick[l] = quick[right];
//        quick[right] = temp;
//
//        quickSort2(quick, left, l-1);
//        quickSort2(quick, left+1, right);
//    }
}
