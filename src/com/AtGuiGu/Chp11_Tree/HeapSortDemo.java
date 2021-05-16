package com.AtGuiGu.Chp11_Tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * @Description: 堆排序
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/25 16:40
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        //临时变量用于大顶堆顶点与末尾值交换
        int temp = 0;
        int[] arr = new int[]{4,6,8,5,9,2,3,10,11,-999,20,21,4,5,10,1000,2};
        int len = arr.length;
        //1.将无需列表变成一个大顶堆
        for(int i=len/2-1; i>=0; i--){
            adjustHeap(arr,i,len);
        }
        //此时,整个序列的最大值就是大顶堆的顶点
        //2.将顶点值与末尾值交换
        //3.除去末尾最大值以后将剩余元素继续构成大顶堆,循环
        for(int j = len-1; j>=0; j--){
            //交换
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            //重复
            adjustHeap(arr,0, j);
        }
        System.out.println(Arrays.toString(arr));

        //效率测试
        efficiencyTest();
    }


    //将待排序序列变成一个大顶堆
    /*
     * @Description: 将以非叶子节点i为顶点的数变成大顶堆
     *
     * @param arr    待排序数组
     * @param i      将数组以顺序二叉树存储时,对应的非叶子结点的索引下标
     * @param length 表示对多少个元素进行调整,每次调整后需要调整的元素个数-1
     * @return void
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/25 16:44
     */
    public static void adjustHeap(int[] arr, int i, int length){

        //保存当前非叶子节点的值,防止后续需要进行与左右子节点交换
        int temp = arr[i];
        //将非叶子结点与左右子节点比较,如果如果左右子节点中的最大值大于非叶子节点的值,则交换两个元素
        //左子节点下标 2*i+1
        //右子节点下标 2*i+2
        for( int k=2*i+1; k<length; k = 2*k+1){
            //对比得到左右子节点的最大值
            if(k+1<length && arr[k]<arr[k+1]){
                //左子节点值小于右子节点的值
                k++;
            }
            //比较非叶子节点与子节点最大值的大小
            if(arr[i] < arr[k]){
                //子节点最大值大于非叶子节点,将最大值赋给非叶子节点
                arr[i] = arr[k];
                //将i指向子节点中的较大值,用于本轮结束后,将较大值位置的值替换为非叶子节点原来的值
                //因为实际上是两个值的交换
                i = k;
            }else{
                //如果非叶子节点的值已经是最大值,则非叶子节点的值已经该顶点下的最大值
                break;
            }
            //将较大值位置的值替换为非叶子节点原来的值
            arr[i] = temp;
        }

    }

    //获取当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //计算排序算法的效率
    public static void efficiencyTest(){
        int[] randomArr = new int[8000000];
        for (int i=0; i<8000000; i++){
            randomArr[i] = (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间: "+currentTime());
        long start = System.currentTimeMillis();

        //堆排序
        int temp = 0;
        int len = randomArr.length;
        for(int i=len/2-1; i>=0; i--){
            adjustHeap(randomArr,i,len);
        }
        for(int j = len-1; j>=0; j--){
            //交换
            temp = randomArr[0];
            randomArr[0] = randomArr[j];
            randomArr[j] = temp;
            //重复
            adjustHeap(randomArr,0, j);
        }

        long end = System.currentTimeMillis();
        System.out.println("排序后时间: "+currentTime());
        System.out.println("排序用时: "+(end-start)+"毫秒");
//        System.out.println(Arrays.toString(randomArr));
    }
}
