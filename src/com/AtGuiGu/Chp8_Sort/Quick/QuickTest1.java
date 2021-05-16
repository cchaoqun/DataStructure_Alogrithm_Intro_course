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
 * @date 2020/12/22 20:09
 */
public class QuickTest1 {
    public static void main(String[] args) {

        //创建待排序数组
        int[] quick = new int[]{-9,78,0,23,-567,70};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(quick));
        qsort2(quick, 0, quick.length-1);
        System.out.printf("排序后数组: %s\n", Arrays.toString(quick));
        efficiencyTest();

    }

    private static void qsort(int[] arr, int low, int high){
        if (low < high){
            int pivot=partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot-1);                   //递归排序左子数组
            qsort(arr, pivot+1, high);                  //递归排序右子数组
        }
    }
    private static int partition(int[] arr, int low, int high){
        int pivot = arr[low];     //枢轴记录
        while (low<high){
            while (low<high && arr[high]>=pivot) {
                high--;
            }
            arr[low]=arr[high];             //交换比枢轴小的记录到左端
            while (low<high && arr[low]<=pivot){
                low++;
            }
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }


    //获取当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        return sdf.format(date);
    }

    //测试排序效率
    public static void efficiencyTest() {
        int[] randomArr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        System.out.println("排序前时间为: " + currentTime());
        long start = System.currentTimeMillis();
        qsort2(randomArr, 0, randomArr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间为: " + currentTime());
        System.out.println("排序用时: " + (end - start) + "毫秒");
    }

    //----------------------------SelfTest---------------------------------
    public static void qsort2(int[] arr, int low, int high){
        if(low<high){
            int pivot = partition2(arr, low, high);
            //对左边进行快速排序
            qsort2(arr, low, pivot-1);
            //对右边进行快速排序
            qsort2(arr, pivot+1, high);
        }


    }
    //将数组分成两个部分
    public static int partition2(int[] arr, int low, int high) {
        //数组最左边元素为中轴
        int pivot = arr[low];
        while(low<high){
            //比较右边元素与pivot的大小
            while(low<high && arr[high] >= pivot){
                high--;
            }
            //将右边比pivot小的元素放到左边
            //arr[low]的值已经存储到pivot
            arr[low] = arr[high];

            //比较左边与pivot的大小
            while(low<high && arr[low] <= pivot){
                low++;
            }
            //将左边比pivot小的元素放到右边
            // arr[high]已经存储到了之前pivot的位置
            arr[high] = arr[low];
        }
        //退出循环即low>=high
        //low所在的位置即为pivot应该在的位置
        arr[low] = pivot;
        //到这里完成了根据pivot将数组分成大于和小于pivot的两个部分 后续继续对两边进行快速排序
        return low;
    }
}
