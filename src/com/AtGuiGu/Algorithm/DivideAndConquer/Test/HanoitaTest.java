package com.AtGuiGu.Algorithm.DivideAndConquer.Test;
/*
 * @Description: 汉诺塔练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 13:19
 */
public class HanoitaTest {
    public static void main(String[] args) {
        HanoitaGame(5,'A','B','C');
    }

    static int count = 0;
    public static void HanoitaGame(int num, char a, char b, char c){
        if(num == 1){
            System.out.printf("Step%d 第%d个盘子 %s -> %s\n",++count, 1, a,c);
        }else{
            HanoitaGame(num-1, a,c,b);
            System.out.printf("Step%d 第%d个盘子 %s -> %s\n",++count, num,a,c);
            HanoitaGame(num-1,b,a,c);
        }
    }
}
