package com.AtGuiGu.Chp8_Sort.Shell;

import java.util.Arrays;

public class ShellMoveTest {
    public static void main(String[] args) {
        int[] shell = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellMove1223(shell);
        System.out.println(Arrays.toString(shell));
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
    public static void shellMove1223(int[] shell){
        int len = shell.length;
        int size = len / 2;
        int temp = 0;
        while(size != 0){
            for(int i=size; i<len; i++){
                int j = i-size;
                temp = shell[i];
                while(j>=0 && temp<shell[j]){
                    shell[j+size] = shell[j];
                    j -= size;
                }
                if( j != i-size){
                    shell[j+size] = temp;
                }
            }
            size /= 2;
        }
    }
}
