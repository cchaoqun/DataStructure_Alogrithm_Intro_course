package com.AtGuiGu.Algorithm.DivideAndConquer;
/*
 * @Description: 汉诺塔
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 17:22
 */
public class Hanoita {
    public static void main(String[] args) {
        HanoitaGame(5,'A','B','C');
    }
    static int count = 0;
    public static void HanoitaGame(int nums, char a, char b, char c){
        if(nums == 1){
            //一个盘从a->c
            System.out.printf("Step%d 第%d个盘 : %s->%s\n",++count,1,a,c);
        }else{
            //num >= 2, 先把上面的num-1个盘从a->b
            HanoitaGame(nums-1, a,c,b);
            //把最后一个盘从a->c
            System.out.printf("Step%d 第%d个盘 : %s->%s\n",++count,nums,a,c);
            //把最上面的nums-1个盘从b->c
            HanoitaGame(nums-1,b,a,c);
        }
    }
}
