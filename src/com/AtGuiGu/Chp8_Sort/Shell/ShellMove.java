package com.AtGuiGu.Chp8_Sort.Shell;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 希尔排序移位法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/22 13:05
 */
public class ShellMove {
    public static void main(String[] args) {
        //创建待排序数组
        int[] shell = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(shell));
        int[] result = ShellSortMove(shell);
        System.out.printf("排序后数组: %s\n", Arrays.toString(result));

        //希尔移位排序效率测试
        System.out.println("希尔排序效率测试");
        efficiencyTest();

    }

    public static int[] ShellSortMove(int[] shell){
        //临时变量用于存储需要插入的元素
        int temp = 0;
        //每轮分组的组数
        int size = shell.length / 2;
        //统计分组次数
        int count = 0;

        while (size != 0){
            count++;
            //从第size个元素开始逐个对其所在组进行直接插入排序
            for(int i = size; i<shell.length; i++){
                temp = shell[i];
                //从前一个元素开始比较
                int j = i-size;
                // j>=0 确保数组下标在范围内
                // shell[j] > minVal 确保了前一个元素比当前元素大
                while(j>=0 && shell[j] > temp){
                    //前一个元素后移一位
                    shell[j+size] = shell[j];
                    //继续向该组前一个元素比较
                    j -= size;
                }
                //退出循环表示找到位置,应该插入到当前元素后一个位置
                shell[j+size] = temp;
            }
//            System.out.printf("第%d次分组后结果: %s\n", count, Arrays.toString(shell));
            //分组
            size = size/2;
        }

        return shell;
    }

    //获取当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //计算排序算法的效率
    public static void efficiencyTest(){
        int[] randomArr = new int[80000];
        for (int i=0; i<80000; i++){
            randomArr[i] = (int) (Math.random()*8000000);
        }

        System.out.println("排序前时间: "+currentTime());
        long start = System.currentTimeMillis();
        ShellSortMove(randomArr);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间: "+currentTime());
        System.out.println("排序用时: "+(end-start)+"毫秒");
    }

}
