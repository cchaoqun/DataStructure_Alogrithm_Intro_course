package com.AtGuiGu.Chp8_Sort.SelectSorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 选择排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/11 21:09
 */
public class Select {
    public static void main(String[] args) {
        //创建待排序数组
        int[] select = new int[]{101, 34, 119, 1, -2, -101, 70};
        //排序前
        System.out.printf("排序前数组: %s\n", Arrays.toString(select));
        int[] result = selectSort(select);
        System.out.printf("排序后数组: %s\n", Arrays.toString(result));

        //选择排序效率测试
//        efficiencyTest();
    }


    //选择排序 (从小到大)
    public static int[] selectSort(int[] select){
        //用于接收最小值
        int minVal = 0;
        //用于接收最小值的下标
        int index = -1;
        //数组长度
        int len = select.length;
        //选择交换轮次
        int round = len-1;
        for (int i = 0; i < round; i++) {
            //临时变量存储当前轮次起始位置的元素值和下标(假定当前起始位置为最小值)
            minVal = select[i];
            index = i;
            for (int j = i+1; j < len; j++) {
                //从起始位置下一个开始依次与当前最小值比较
                if(minVal > select[j]){
                    minVal = select[j];
                    index = j;
                }
            }
            //结束本轮, minVal index 分别存储本轮最小值和最小值对应的下标,
            //将最小的元素与本轮起始位置元素交换
            if(index != i){
                //最小值元素下标不等于起始位置下标说明进行了最小值不为假定初始位置值,需要进行交换
                select[index] = select[i];
                select[i] = minVal;
            }
            //最小值刚好为起始位置元素,无需交换
//            System.out.printf("第%d轮排序得到的数组为: %S\n", i, Arrays.toString(select));
        }
        return select;
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
        int[] result = selectSort(arr);
        long end = System.currentTimeMillis();
        //排序后时间
        System.out.printf("排序后时间为: %s\n", currenTime());
        System.out.printf("排序用时: %d秒", (end-start)/1000);
    }

}
