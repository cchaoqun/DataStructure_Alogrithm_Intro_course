package com.AtGuiGu.Algorithm.KMP.ViolenceMatch_KMP_Test;

import java.util.Arrays;

/*
 * @Description: 暴力匹配 KMP练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 15:05
 */
public class KMP_Test {
    public static void main(String[] args) {
//        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "ABCDABD";
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = " 尚硅谷你尚硅 ";
        int[] res = violenceMatch(str1,str2);
        System.out.println("暴力匹配: "+Arrays.toString(res));

        int[] next = kmpNext(str2);
        int[] kmpRes = kmp(str1,str2,next);
        System.out.println("KMP: "+ Arrays.toString(kmpRes));
    }


    public static int[] violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        int[] match = new int[2];
        while(i<s1Len && j<s2Len){
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else{
                i = i-(j-1);
                j = 0;
            }
        }
        if(j == s2Len){
            match[0] = i-j;
            match[1] = i-1;
            return match;
        }else{
            return match;
        }
    }

    public static int[] kmpNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        int j = 0;
        for(int i=1; i<str.length(); i++){
            if(j>0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }

            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int[] kmp(String str1, String str2, int[] next){
        int[] match = new int[2];
        for(int i=0,j=0; i<str1.length(); i++){
            while(j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                match[0] = i+1-j;
                match[1] = i;
                return match;
            }
        }
        return match;
    }
}
