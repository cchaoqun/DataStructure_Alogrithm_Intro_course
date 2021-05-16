package com.AtGuiGu.Chp8_Sort.Quick;

import java.util.Arrays;

public class QuickTest {
    public static void main(String[] args) {
        int[] quick = new int[]{-9,78,0,23,-567,70};
        int len = quick.length;
        int[] tempArr = new int[len];
        quick1223(quick, 0, len-1);
        System.out.println(Arrays.toString(quick));
    }

    /*
     * @Description:
     *
     * @param null
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/23 9:27
     */
    public static void quick1223(int[] quick, int l, int r){
        if(l<r){
            int pivot = partition1223(quick, l, r);
            quick1223(quick, l, pivot-1);
            quick1223(quick, pivot+1, r);
        }

    }

    public static int partition1223(int[] quick, int l, int r){
        int pivot = quick[l];
        while(l<r){

            while(l<r && quick[r] >= pivot){
                r--;
            }
            quick[l] = quick[r];

            while(l<r && quick[l] <= pivot){
                l++;
            }
            quick[r] = quick[l];

        }
        quick[l] = pivot;
        return l;
    }
}
