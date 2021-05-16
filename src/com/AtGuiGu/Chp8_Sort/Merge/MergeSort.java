package com.AtGuiGu.Chp8_Sort.Merge;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 归并排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/22 20:40
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] merge = new int[]{8,4,5,7,1,3,6,2};
        int[] tempArr = new int[merge.length];
        System.out.println("排序前的数组: "+ Arrays.toString(merge));
        mergeSort(merge, 0,  merge.length-1, tempArr);
        System.out.println("排序后的数组: "+ Arrays.toString(merge));

//        efficiencyTest();
    }

    static int count = 0;

    public static void mergeSort(int[] merge, int left, int right, int[] tempArr){
        if (left < right){
            //中间索引
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(merge, left, mid, tempArr);
            //向右递归进行分解
            mergeSort(merge, mid+1, right, tempArr);
            //合并
            merge(merge, left, mid, right, tempArr);
        }
    }

    /*
     * @Description: 合并的方法
     *
     * @param merge 待排序数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的末尾索引
     * @param tempArr 中转数组
     * @return void
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/22 20:41
     */
    public static void merge(int[] merge, int left, int mid, int right, int[] tempArr) {
        count++;

        //左边序列初始下标
        int i = left;
        //右边序列初始下标
        int j = mid + 1;
        //中转数组初始下标
        int t = 0;

        // 1.
        //先把左右两边的序列按照规则插入到中转数组
        //直到有一边处理完毕
        while(i <= mid && j <= right){
            //比较左右两边序列第一个位置的大小
            if(merge[i] <= merge[j]){
                //将较小值存放到中转数组
                tempArr[t] = merge[i];
                t += 1;
                i += 1;
            }else{
                tempArr[t] = merge[j];
                t += 1;
                j += 1;
            }
        }

        //2.
        //退出循环说明有一边全部添加到中转数组,此时需判断是那一边
        if(i > mid){
            //说明左边处理完毕
            //依次将右边序列加入到中转数组
            while(j <= right){
                tempArr[t] = merge[j];
                t += 1;
                j += 1;
            }
        }else{
            //右边处理完毕
            //依次将左边序列加入到中转数组
            while(i <= mid){
                tempArr[t] = merge[i];
                t += 1;
                i += 1;
            }
        }

        //3.
        //将tempArr Copy到原始数组
        //并不是每次都将整个数组拷贝到原数组,取决于本次往中转数组添加了多少元素
        //第一次 0,1
        //第二次 2,3
        //第三次 0,3
        //...第七次 0,7
        t = 0;
        int originLeft = left;
        System.out.println("第"+count+"次合并 "+"originLeft= "+originLeft+" right= "+right);
        while(originLeft <= right){
            merge[originLeft] = tempArr[t];
            t += 1;
            originLeft += 1;
        }
    }

    //当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //测试归并排序的效率
    public static void efficiencyTest(){
        int[] randomArr = new int[8000000];
        for(int i=0; i< randomArr.length; i++){
            randomArr[i] = (int) (Math.random()*8000000);
        }
        int[] tempArr = new int[randomArr.length];
        System.out.println("排序前时间为: "+currentTime());
        long start = System.currentTimeMillis();
        mergeSort(randomArr, 0, randomArr.length-1, tempArr);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间为: " + currentTime());
        System.out.println("排序用时: "+(end-start)+"毫秒");
//        System.out.println("排序后数组: "+Arrays.toString(randomArr));
    }
}
