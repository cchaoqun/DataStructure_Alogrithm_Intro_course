package com.AtGuiGu.Chp8_Sort.Shell;

import java.util.Arrays;

public class ShellTest {
    public static void main(String[] args) {
        int[] shell = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shell1223(shell);
        System.out.println(Arrays.toString(shell));
    }

    /*
     * @Description:
     *
     * @param null
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/23 9:22
     */
    public static void shell1223(int[] shell){
        int temp = 0;
        int len = shell.length;
        int size = len / 2;
        while(size!=0){
            for(int i=size; i<len; i++){
                for(int j=i-size; j>=0; j-=size){
                    if(shell[j+size] < shell[j]){
                        temp = shell[j];
                        shell[j] = shell[j+size];
                        shell[j+size] = temp;
                    }
                }
            }
            size /= 2;
        }
    }
}
