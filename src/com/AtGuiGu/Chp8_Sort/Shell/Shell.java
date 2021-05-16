package com.AtGuiGu.Chp8_Sort.Shell;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description:
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/22 12:19
 */
public class Shell {
    public static void main(String[] args) {
        //创建待排序数组
        int[] shell = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(shell));
        int[] result = ShellSort(shell);
        System.out.printf("排序后数组: %s\n", Arrays.toString(result));

        //希尔交换排序效率测试
        System.out.println("希尔排序效率测试");
        efficiencyTest();

    }

    public static int[] ShellSort(int[] shell){
        //临时变量用于交换
        int temp = 0;
        //每轮分组的组数
        int size = shell.length / 2;
        //统计分组次数
        int count = 0;

        while (size != 0){
            count++;
            //从分组的第一组第二个元素开始遍历到数组结尾
            for(int i = size; i<shell.length; i++){
                //遍历各组所有元素,从后往前遍历
                for(int j = i-size; j>=0; j -= size){
                    //如果逆序则交换
                    if (shell[j+size] < shell[j]) {
                        temp = shell[j+size];
                        shell[j+size] = shell[j];
                        shell[j] = temp;
                    }
                }
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
        ShellSort(randomArr);
        long end = System.currentTimeMillis();
        System.out.println("排序后时间: "+currentTime());
        System.out.println("排序用时: "+(end-start)/1000+"秒");
    }
}
