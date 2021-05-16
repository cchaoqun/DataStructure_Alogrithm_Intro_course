package com.AtGuiGu.Chp8_Sort.Insert;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 插入排序
 * 1.创建一个有序数组与存放待排序数组长度相同,第一个元素相同,依次将待排序数组后续元素插入有序数组
 * 2.将待插入元素依次从后往前与有序数组元素比较
 * 3.如果当前元素大于待插入元素,将当前元素后移一位,然后将待插入元素与前一位比较
 * 4.当待插入元素大于当前比较元素时,应该插入的位置为当前元素后一位
 *
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/22 10:27
 */
public class InsertSort {
    public static void main(String[] args) {
        //创建待排序数组
        int[] insert = new int[]{101, 34, 119, 1, -2, -101, 70};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(insert));
        int[] result = insertSort(insert);
        System.out.printf("排序后数组: %s\n", Arrays.toString(result));


        //测试插入排序效率
        System.out.println("插入排序效率测试\n");
        efficiencyTest();
    }

    public static int[] insertSort(int[] insert){
        int len = insert.length;
        int insertVal = 0;
        int insertIndex = 0;
        //将待排序数组的第二个到最后一个元素依次插入有序数组
        for(int i=1; i<len; i++){
            insertVal = insert[i];
            insertIndex = i-1;
            //找到该无序表的元素在有序表中的位置
            while(insertIndex>=0 && insertVal>insert[insertIndex]){
                //如果当前比较元素大于待插入元素,将当前元素后移一位
                insert[insertIndex + 1] = insert[insertIndex];
                //比较前一位元素
                insertIndex -= 1;
            }
            //退出循环找到位置,待插入元素大于当前元素,应该插入到当前元素后一位
            if(insertIndex+1 != i){
                insert[insertIndex+1] = insertVal;
            }
            System.out.printf("插入第%d个元素后的有序数组: %s\n", i, Arrays.toString(insert));
        }
        return insert;
    }

    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //测试插入排序的效率
    public static void efficiencyTest(){
        int[] randomArr = new int[8];
        for (int i = 0; i < 8; i++) {
            randomArr[i] = (int) (Math.random()*8000000);
        }

        //测试前时间
        System.out.printf("测试前时间: %s\n", currentTime());
        long start = System.currentTimeMillis();
        int[] res = insertSort(randomArr);
        long end = System.currentTimeMillis();
        System.out.printf("测试后时间: %s\n", currentTime());
        System.out.printf("测试用时: %d", (end-start)/1000);
    }
}
