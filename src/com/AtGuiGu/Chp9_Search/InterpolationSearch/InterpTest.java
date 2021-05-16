package com.AtGuiGu.Chp9_Search.InterpolationSearch;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 插值查找练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 9:32
 */
public class InterpTest {
    public static void main(String[] args) {
        int[] interp = new int[100];
        for (int i = 0; i < 90; i++) {
            interp[i] = i+2;
        }
        for (int i = 90; i < 100; i++) {
            interp[i] = 95 ;
        }
        int[] binary = new int[]{1,13,500,1000,1000,1000,1000,2000,4000};
        ArrayList<Integer> res = interpolationSearch1224(binary,0,binary.length-1,3000);
        Collections.sort(res);
        System.out.println("resIndex= "+res);
    }
    static int count = 0;
    public static ArrayList<Integer> interpolationSearch1224(int[] interp, int left, int right, int val){
        System.out.printf("查找%d次\n",++count);
        if(left > right || val < interp[left] || val > interp[interp.length-1]){
            return new ArrayList<Integer>();
        }
        int mid = left + (right-left)*(val-interp[left])/(interp[right]-interp[left]);
        int midVal = interp[mid];
        if(val < midVal){
            return interpolationSearch1224(interp, left, mid-1, val);
        }else if(val > midVal){
            return interpolationSearch1224(interp, mid+1, right, val);
        }else{
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(mid);
            int lIndex = mid-1;
            int rIndex = mid+1;
            while(true){
                if(lIndex<0 || interp[lIndex] != val){
                    break;
                }
                arrayList.add(lIndex--);
            }
            while(true){
                if(rIndex<0 || interp[rIndex] != val){
                    break;
                }
                arrayList.add(rIndex++);
            }
            return arrayList;
        }



    }

}
