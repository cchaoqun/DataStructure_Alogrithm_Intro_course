package com.AtGuiGu.Chp9_Search.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 二分查找练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 9:18
 */
public class BinaryTest {
    public static void main(String[] args) {
        int[] binary = new int[]{1,13,500,1000,1000,1000,1000,1000,1000};

        ArrayList res = binarySearch1224(binary,0,binary.length-1,1000);
        //对arrayList进行排序
        Collections.sort(res);
        System.out.println("resultIndex= "+res);
    }

    public static ArrayList<Integer> binarySearch1224(int[] binary, int left, int right, int val){
        if(left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;
        int midVal = binary[mid];
        if(val < midVal){
            //向左递归查找
            return binarySearch1224(binary, left, mid-1,val);
        }else if(val > midVal){
            //向右递归查找
            return binarySearch1224(binary, mid+1, right, val);
        }else{
            ArrayList<Integer> arrayList = new ArrayList<>();
            int lIndex = mid-1;
            int rIndex = mid+1;
            //添加找到的元素下标
            arrayList.add(mid);
            //向左查找相同元素
            while(true){
                if(lIndex<left || binary[lIndex] != val){
                    break;
                }
                arrayList.add(lIndex--);
            }
            //向右查找相同元素
            while(true){
                if(rIndex>right || binary[rIndex] != val){
                    break;
                }
                arrayList.add(rIndex++);
            }
            return arrayList;
        }

    }
}
