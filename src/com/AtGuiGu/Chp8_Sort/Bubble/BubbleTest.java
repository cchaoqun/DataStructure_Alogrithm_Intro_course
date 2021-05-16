package com.AtGuiGu.Chp8_Sort.Bubble;

import java.util.Arrays;

/*
 * @Description:冒泡排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/11 17:36
 */
public class BubbleTest {
    public static void main(String[] args) {
        //创建待排序数组
        int[] bubble = new int[]{3, 9, -1, 10, -2};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(bubble));
        //排序后
        bubbleSort(bubble);

    }

    //冒泡排序
    public static void bubbleSort(int[] bubble){
        //创建临时变量
        int temp = 0;
        //判断本轮是否交换
        boolean flag = false;
        //排序轮次
        int len = bubble.length -1;
        for (int i = 0; i < len; i++) {
            //每一轮比上一轮少判断依次
            for (int j = 0; j < len - i; j++) {
                if (bubble[j] > bubble[j + 1]) {
                    //本轮交换过
                    flag = true;
                    //逆序 交换两数
                    temp = bubble[j];
                    bubble[j] = bubble[j + 1];
                    bubble[j + 1] = temp;
                }
            }
            if(!flag){
                //本轮未交换,已经是按顺序排列的数组
                break;
            }else{
                //本轮交换过,重置标志
                flag = false;
            }
        }
        System.out.printf("排序后数组: %s\n", Arrays.toString(bubble));

    }
}
