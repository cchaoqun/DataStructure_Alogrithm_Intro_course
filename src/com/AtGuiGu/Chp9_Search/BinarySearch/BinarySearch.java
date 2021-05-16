package com.AtGuiGu.Chp9_Search.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 二分查找
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/23 14:36
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] binary = new int[]{1,13,500,1000,1000,1000,1000,2000,4000};

        ArrayList res = binarySearch(binary,0,binary.length-1,1000);
        //对arrayList进行排序
        Collections.sort(res);
        System.out.println("resultIndex= "+res);

    }

    public static ArrayList<Integer> binarySearch(int[] binary, int left, int right, int searchVal) {
        //确定左索引小于右索引,这里是找不到的情况则返回-1,保证递归有终止条件
        if (left > right) {
            return new ArrayList<Integer>();
        }
        //确定中间比较的位置
        int mid = (left + right) / 2;
        int midVal = binary[mid];

        //比较待查找值与中间值的关系
        if (searchVal > midVal) {
            //向右递归查找
            return binarySearch(binary, mid + 1, right, searchVal);
        } else if (searchVal < midVal) {
            //向左递归
            return binarySearch(binary, left, mid - 1, searchVal);
        } else {
            //此时中间值与待查找值相等
            int lIndex = mid-1;
            int rIndex = mid+1;
            //将找到的中间索引添加到arraylist
            ArrayList<Integer> arrayList = new ArrayList();
            arrayList.add(mid);
            //需要再向两边查找到是否有多个相同的值
            //向左找
            while(true){
                if(lIndex<left || binary[lIndex] != searchVal){
                    break;
                }
                arrayList.add(lIndex);
                lIndex--;
            }
            //向右找
            while(true){
                if(rIndex>right || binary[rIndex] != searchVal){
                    break;
                }
                arrayList.add(rIndex);
                rIndex++;
            }
            return arrayList;
        }
    }

}
