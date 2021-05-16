package com.AtGuiGu.Chp3_SinglyLinkedList;

import java.util.Stack;

/**
 * @Description:  测试栈数据结构的使用
 * 先进后出
 *
 *
 * @para:
 * @return:
 * @Author: CCQ
 * @Date: 2020/12/8
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("Kobe");
        stack.add("James");
        stack.add("Curry");
        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
