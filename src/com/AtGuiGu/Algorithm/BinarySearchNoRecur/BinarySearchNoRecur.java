package com.AtGuiGu.Algorithm.BinarySearchNoRecur;
/*
 * @Description: 二分查找非递归
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 15:48
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr,0);
        System.out.println("index = "+index);
    }


    /*
     * @Description: 数组必须有序,该情况适用于升序数组
     *
     * @param arr 待查询数组
     * @param target 查找的值
     * @return 目标值在数组中的下标
     */
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;

        while(left<=right){
            //中间结点
            int mid = (left+right)/2;
            if(target == arr[mid]){
                //目标值等于中间结点向左查找
                return mid;
            }else if(target < arr[mid]){
                //目标值小于中间结点的值
                right = mid - 1;
            }else if(target > arr[mid]){
                //目标值大于中间结点的值
                left = mid + 1;
            }
        }

        //退出循环还未找到 则目标值不在数组
        return -1;
    }
}
