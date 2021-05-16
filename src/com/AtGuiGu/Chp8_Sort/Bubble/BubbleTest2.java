package com.AtGuiGu.Chp8_Sort.Bubble;

import java.util.Arrays;

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
public class BubbleTest2 {
    public static void main(String[] args) {
        //创建待排序数组
        int[] bubble = new int[]{3, 9, -1, 10, -2};
        System.out.printf("排序之前的数组:\n%s\n", Arrays.toString(bubble));
        int[] res = bubbleSort1(bubble);
        System.out.printf("排序之后的数组:\n%s\n", Arrays.toString(bubble));

    }

    public static int[] bubbleSort1(int[] bubble){
        //临时变量用于逆序情况下的元素交换
        int temp = 0;
        //待排序数组长度
        int len = bubble.length;
        //排序轮数等于数组长度-1
        int round = len - 1;
        //排序round轮
        for(int i=0; i<round; i++){
            //每一轮从第一个元素到n-i个元素
            for(int j=0; j<round-i; j++){
                //判断是否逆序
                if (bubble[j] > bubble[j+1]) {
                    //逆序则交换两个元素
                    temp = bubble[j];
                    bubble[j] = bubble[j+1];
                    bubble[j+1] = temp;
                }
                //无逆序则指针向后移一位
            }
            //每一轮结束后打印当前轮次排序后结果
            System.out.printf("第%d轮排序后: %s\n", i+1, Arrays.toString(bubble));
        }
        //排序结束后返回排序后的数组
        return bubble;
    }
}
