package com.AtGuiGu.Chp8_Sort.Insert;

import java.util.Arrays;

public class InsertTest {
    public static void main(String[] args) {
        int[] insert = new int[]{101, 34, 119, 1, -2, -101, 70};
        System.out.println(Arrays.toString(insert1223(insert)));
    }

    /*
     * @Description:
     *
     * @param null
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/23 9:17
     */
    public static int[] insert1223(int[] insert){
        int minVal = 0;
        int insertIndex = 0;
        int len = insert.length;
        for(int i=1; i<len; i++){
            minVal = insert[i];
            insertIndex = i-1;

            while(insertIndex>=0 && insert[insertIndex]>=minVal){
                insert[insertIndex+1] = insert[insertIndex];
                insertIndex--;
            }
            if(insertIndex != i-1){
                insert[insertIndex+1] = minVal;
            }
        }

        return insert;
    }
}
