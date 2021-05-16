package com.AtGuiGu.Chp6_Stack;

import java.util.Scanner;

/*
 * @Description: 数组实现栈
 * 1.定义一个top变量模拟栈顶 默认-1
 * 2.入栈: top++, stack[top] = data
 * 3.出栈: int temp = stack[top] top-- return temp
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/9 16:41
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试ArrayStack
        //创建ArrayStack对象表示栈
        ArrayStack stack = new ArrayStack(4);
        //接受用户的选择
        String choice = null;
        Scanner input = new Scanner(System.in);
        //标记是否退出
        boolean flag = true;
        while(flag){
            System.out.println("show: 显示栈数据");
            System.out.println("exit: 退出程序");
            System.out.println("push: 向栈添加数据(入栈)");
            System.out.println("pop: 从栈取出数据(出栈)");
            System.out.println("请输入您的选择: ");
            choice = input.next();
            switch(choice) {
                case "show":
                    stack.stackShow();
                    break;
                case "exit":
                    input.close();
                    flag = false;
                    break;
                case "push":
                    System.out.println("请输入您的入栈数据:");
                    int data = input.nextInt();
                    stack.push(data);
                    break;
                case "pop":
                    //这里存在异常
                    try {
                        int pop = stack.pop();
                        System.out.printf("取出的数据为 %d \n", pop);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序已经退出...");
    }


}

//定义一个 ArrayStack表示栈
class ArrayStack{
    //数组大小
    private int maxSize;
    //模拟栈的数组
    private int[] stack;
    //模拟栈顶的变量
    private int top = -1;

    //构造方法
    public ArrayStack(int size){
        this.maxSize = size;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull(){
        //栈顶与数组容量大小相同
        return (top == maxSize-1);
    }

    //判断栈空
    public boolean isEmpty(){
        //top = -1
        return top == -1;
    }

    //入栈 push
    public void push(int data){
        //判断是否满
        if(isFull()){
            System.out.println("栈满,无法入栈...");
            return;
        }
        //栈未满
        top++;
        stack[top] = data;
    }

    //出栈 pop
    public int pop(){
        //判断是否空
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空,无数据可出栈...");
        }
        //临时变量存储出栈数据
        int temp = stack[top];
        top--;
        return temp;
    }

    //栈的遍历,需要从栈顶开始从上往下显示数据
    public void stackShow(){
        //判断是否空
        if(isEmpty()){
            System.out.println("栈空,无数据可出栈...");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n",i,stack[i]);
        }
    }

}

