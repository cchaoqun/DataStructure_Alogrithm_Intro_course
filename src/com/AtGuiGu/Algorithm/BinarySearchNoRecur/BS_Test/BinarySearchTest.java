package com.AtGuiGu.Algorithm.BinarySearchNoRecur.BS_Test;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 二分查找非递归练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 12:45
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10,10,10, 11, 67, 100};
        int index = BinarySearch(arr,10);
        System.out.println("index = "+index);

        ArrayList<Integer> list = BSRecur(arr,0,arr.length-1, 10);
        Collections.sort(list);
        System.out.println(list);
    }

    public static int BinarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;

        while(left<=right) {
            int mid = (left+right)/2;
            int midVal = arr[mid];
            if (target == midVal) {
                return mid;
            } else if (target < midVal) {
                right = mid - 1;
            } else if (target > midVal){
                left = mid + 1;
            }
        }
        return -1;
    }

    public static ArrayList<Integer> BSRecur(int[] arr, int left, int right, int target){
        if(left>right){
            return new ArrayList<Integer>();
        }

        int mid = (left+right)/2;
        int midVal = arr[mid];

        if(target < midVal){
            return BSRecur(arr, left, mid-1, target);
        }else if(target > midVal){
            return BSRecur(arr, mid+1, right, target);
        }else{
            int lIndex = mid-1;
            int rIndex = mid+1;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);

            while(lIndex>=left && arr[lIndex] == midVal){
                list.add(lIndex);
                lIndex--;
            }
            while(rIndex<=right && arr[rIndex] == midVal){
                list.add(rIndex);
                rIndex++;
            }
            return list;
        }
    }
}
