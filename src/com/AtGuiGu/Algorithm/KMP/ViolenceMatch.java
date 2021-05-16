package com.AtGuiGu.Algorithm.KMP;

import java.util.Arrays;

/*
 * @Description: 暴力匹配
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 19:59
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
//        String str2 = "尚硅谷你尚硅你";
        String str2 = " 尚硅谷你尚硅 ";
        int[] res = ViolenMatch(str1,str2);
        System.out.println("匹配的下标为: "+Arrays.toString(res));
    }

    public static int[] ViolenMatch(String str1, String str2){

        //获取待匹配字符串的字符数组
        char[] s1 = str1.toCharArray();
        //获取匹配字符串的字符数组
        char[] s2 = str2.toCharArray();
        //获取两个字符数组的长度
        int s1len = s1.length;
        int s2len = s2.length;
        //定义两个指针索引分别指向两个数组
        int i = 0;
        int j = 0;
        //定义匹配到str2在str1中的下标
        int[] match = new int[2];
        //====我的方法====
//        for(i=0; i<s1len; ){
//            if((s1len-1)-i < s2len){
//                //s1数组剩余数量小于s2数组长度,不可能匹配
//                return match;
//            }
//            if(s1[i] != s2[j]){
//                i++;
//            }else{
//                while(s1[i] == s2[j]) {
//                    if (j == s2len - 1) {//完全匹配
//                        match[0] = i - j;
//                        match[1] = i;
//                        return match;
//                    }
//                    i++;
//                    j++;
//                }
//                //退出循环说明不匹配
//                i = i-(j-1);
//                j = 0;
//            }
//        }
//        return match;

        while(i<s1len && j<s2len){
            //匹配
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else{
                //没有匹配
                i = i-(j-1);
                j = 0;
            }
        }
        //退出循环根据j的值判断是否匹配全部字符
        if(j==s2len){
            //完全匹配
            match[0] = i-j;
            match[1] = i-1;
            return match;
        }else{
            //未完全匹配
            return match;
        }
    }
}
