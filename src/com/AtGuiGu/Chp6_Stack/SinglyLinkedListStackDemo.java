package com.AtGuiGu.Chp6_Stack;

import java.util.Scanner;

/*
 * @Description: 用单链表模拟栈
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/9 19:10
 */
public class SinglyLinkedListStackDemo {
    public static void main(String[] args) {
        //测试单链表模拟栈
        //1.创建一个SinglyLinkedListStack类
        SinglyLinkedListStack stack = new SinglyLinkedListStack(2);
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
                    String data = input.next();
                    stack.push(data);
                    break;
                case "pop":
                    //这里存在异常
                    try {
                        String pop = stack.pop();
                        System.out.printf("取出的数据为 %s \n", pop);
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

//创建一个类来模拟栈存储数据
class SinglyLinkedListStack {
    //栈的大小
    private int maxSize;
    //单链表模拟栈
    private SinglyLinkedList stack;
    //定义变量表示栈顶
    private int top = -1;
    //构造方法
    public SinglyLinkedListStack(int size){
        this.maxSize = size;
        stack = new SinglyLinkedList();
        //创建一个指定长度的单链表
        stack.createList(maxSize);
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(String data){
        if(isFull()){
            System.out.println("栈满,无法入栈...");
            return;
        }
        top++;
        stack.pushNode(top, data);
    }
    //出栈
    public String pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空,无法出栈...");
        }
        String temp = stack.popNode(top);
        top--;
        return temp;
    }
    //栈的遍历
    public void stackShow(){
        //判断栈空
        if(isEmpty()){
            System.out.println("栈空,无法展示...");
            return;
        }
        stack.showStack(top);
    }

}

//创建一个链表
class SinglyLinkedList{
    //创建链表头,不存放数据
    StackNode head = new StackNode(-1);

    //根据需求创建指定长度的单链表来模拟存放数据的栈 类似于创建maxSize的数组
    public void createList (int maxSize){
        //判断maxSize的合规性
        if (maxSize < 1) {
            System.out.println("输入数据不合格...");
            return;
        }
        //辅助结点temp
        StackNode temp = head;
        for (int i = 0; i < maxSize; i++) {
            //创建一个结点
            StackNode node = new StackNode(i);
            //新节点加入到链表末尾
            temp.setNext(node);
            //指针指向链表末尾
            temp = node;

        }
    }

    //根据栈顶的位置添加数据到链表栈顶 入栈
    public void pushNode(int top, String data){
        //辅助结点
        StackNode temp = head;
        //让temp指向栈顶的前一个结点,
        for(int i=0; i<top; i++){
            temp = temp.getNext();
        }
        //为栈顶的结点赋值
        temp.getNext().setData(data);
    }

    //根据栈顶的位置,取出栈顶位置的数据,无需删除结点,取出数据即可 出栈
    public String popNode(int top){
        //辅助结点
        StackNode temp = head;
        //让temp指向栈顶的结点即可,
        for(int i=0; i<top+1; i++){
            temp = temp.getNext();
        }
        //获取栈顶结点的数据
        return temp.getData();
    }

    //从栈顶开始遍历链表
    public void showStack(int top){
        if(top == -1){
            System.out.println("栈空,无法出栈...");
            return;
        }
        StackNode temp = head;
        while(top>=0){
            //将指针移动到栈顶位置
            for (int i = 0; i < top+1; i++) {
                temp = temp.getNext();
            }
            //打印栈顶数据
            System.out.printf("stack[%d] = %s \n",top, temp.getData());
            top--;
            //将temp移回head
            temp = head;
        }

    }

}



//结点类
class StackNode{
    //结点编号
    private int no;
    //结点值
    private String data;
    // next 域
    private StackNode next;

    //构造方法
    public StackNode(int no){
        this.no = no;
    }

    //toString
    @Override
    public String toString() {
        return "StackNode{" +
                "no=" + no +
                ", data=" + data +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
