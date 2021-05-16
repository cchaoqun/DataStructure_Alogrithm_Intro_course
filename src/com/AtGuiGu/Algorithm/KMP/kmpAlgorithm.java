package com.AtGuiGu.Algorithm.KMP;

import java.util.Arrays;

/*
 * @Description: KMP
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 22:22
 */
public class kmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("部分匹配表: "+ Arrays.toString(next));

        int index = kmpSearch(str1,str2,next);
        System.out.println("index = "+index);


    }

    //KMP搜索算法
    public static int kmpSearch(String str1, String str2, int[] next){

        for(int i=0,j=0; i<str1.length(); i++){
            //需要处理str1.charAt(i) != str2.charAt(j) 调整j的大小
            while(j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            //匹配到
            if(str1.charAt(i) == str2.charAt(j)){

                //匹配字符串指针后移一位
                j++;
            }
            //判断匹配字符串指针与匹配字符串长度相等
            if(j==str2.length()){
                //匹配字符串在待匹配字符串中匹配的第一个下标
                return i-j+1;
            }
        }
        return -1;

    }



    //获取字符串的部分匹配表
    public static int[] kmpNext(String str){
        //创建用于存放部分匹配表的数组
        int[] next = new int[str.length()];
        //部分匹配第一个元素为0,因为一个字符的字符串没有前缀和后缀,不可能相同
        next[0] = 0;
        for(int i=1,j=0; i<str.length(); i++){
            //当str.charAt(i) != str.charAt(j),需要从next[j-1]获取j
            //直到发现str.charAt(i) == str.charAt(j)才退出
            //??????????????????????????????????????????
            while(j>0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }
            //str.charAt(i) == str.charAt(j)满足,部分匹配值+1
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
