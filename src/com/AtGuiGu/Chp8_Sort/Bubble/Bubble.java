package com.AtGuiGu.Chp8_Sort.Bubble;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 冒泡排序
 * 1.两个指针一前一后依次索引数组
 * 2.发现逆序的情况就交换
 * 3.每轮排序将最大值或最小值放到数组末尾
 * 4.一共进行 数组大小-1 轮排序
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/11 16:31
 */
public class Bubble {
    public static void main(String[] args) {
        //创建待排序数组
        int[] bubble = new int[]{3, 9, -1, 10, -2};
        System.out.printf("排序之前的数组:\n%s\n", Arrays.toString(bubble));
        int[] result = bubbleSort(bubble);
        System.out.printf("排序之后的数组:\n%s\n", Arrays.toString(result));

        //测试冒泡排序的效率
        efficiencyTest();

    }
    
    //冒泡排序
    public static int[] bubbleSort(int[] bubble){
        //临时变量用于交换
        int temp = 0;
        //判断本轮排序是否进行交换
        boolean flag = false;
        //排序轮次 为数组大小-1
        int round = bubble.length - 1;
        for (int i = 0; i < round; i++) {
            for (int j = 0; j < round-i; j++) {
                //如果前面的数大就交换
                if(bubble[j] > bubble[j+1]){
                    flag = true;
                    temp = bubble[j];
                    bubble[j] = bubble[j+1];
                    bubble[j+1] = temp;
                }
            }
            if(!flag){
                //flag = false 说明本轮未进行交换,无需进行下一轮
                return bubble;
            }else{
                //flag = true; 本轮进行了交换还需进行下一轮
                flag = false;
            }
        }
        //返回排序后的数组
        return bubble;
    }

    //记录当前时间
    public static String currenTime(){
        //获取当前时间
        Date date = new Date();
        //时间格式化类
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        return sdf.format(date);
    }


    //测试排序时间
    public static void efficiencyTest(){
        //测试排序的时间复杂度 排序80000个数所用的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }

        //排序前时间
        System.out.printf("排序前时间为: %s\n", currenTime());
        long start = System.currentTimeMillis();
        //冒泡排序
        int[] result = bubbleSort(arr);
        long end = System.currentTimeMillis();
        //排序后时间
        System.out.printf("排序后时间为: %s\n", currenTime());
        System.out.printf("排序用时: %d秒", (end-start)/1000);
    }

}
