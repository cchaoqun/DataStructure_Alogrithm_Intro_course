package com.AtGuiGu.Chp9_Search.InterpolationSearch;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 插值查找
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/23 16:22
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] interp = new int[100];
        for (int i = 0; i < 90; i++) {
            interp[i] = i+2;
        }
        for (int i = 90; i < 100; i++) {
            interp[i] = 95 ;
        }
        int[] binary = new int[]{1,13,500,1000,1000,1000,1000,2000,4000};
        ArrayList<Integer> res = interpolationSearch(binary,0,binary.length-1,3000);
        Collections.sort(res);
        System.out.println("resIndex= "+res);
    }

    static int count;

    public static ArrayList<Integer> interpolationSearch(int[] interp, int left, int right, int searchVal){
        System.out.printf("查找%d次\n",++count);
        //判断是否找到
        if(left > right || searchVal < interp[left] || searchVal > interp[right]){
            return new ArrayList<Integer>();
        }
        //利用插值法确定比较的下标
        int searchPoint = left + (right-left)*(searchVal-interp[left])/(interp[right]-interp[left]);

        if(searchVal < interp[searchPoint]){
            //向左递归
            return interpolationSearch(interp, left, searchPoint-1,searchVal);
        }else if(searchVal > interp[searchPoint]){
            //向右递归
            return interpolationSearch(interp, searchPoint+1, right, searchVal);
        }else{
            //此时目标值与比较值相等
            ArrayList<Integer> arrayList = new ArrayList<>();
            //将找到的下标添加到ArrayList
            arrayList.add(searchPoint);
            int lIndex = searchPoint-1;
            int rIndex = searchPoint+1;
            //还需向左右两边查找是否有相同的值
            //向左找
            while(true){
                if(lIndex < left || interp[lIndex] != searchVal){
                    break;
                }
                arrayList.add(lIndex--);
            }
            //向右找
            while(true){
                if(rIndex > right || interp[rIndex] != searchVal){
                    break;
                }
                arrayList.add(rIndex++);
            }

            return arrayList;

        }

    }
}
