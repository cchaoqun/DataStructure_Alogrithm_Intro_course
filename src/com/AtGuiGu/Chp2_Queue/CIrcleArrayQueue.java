package com.AtGuiGu.Chp2_Queue;

import java.util.Scanner;

public class CIrcleArrayQueue {
    public static void main(String[] args) {
        circleQueue circle = new circleQueue(4);
        Scanner input = new Scanner(System.in);
        char choice;
        boolean loop = true;
        while(loop){
            System.out.println("s(show): 显示队列数据");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 向队列添加数据");
            System.out.println("g(get): 从队列获取数据");
            System.out.println("h(head): 查看队列头数据");
            System.out.println("请选择...");
            choice = input.next().charAt(0);
            switch(choice) {
                case 's':
                    circle.show();
                    break;
                case 'e':
                    input.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入添加的数据:");
                    int data = input.nextInt();
                    circle.addQueue(data);
                    break;
                case 'g':
                    System.out.println("获取数据："+circle.getQueue());
                    break;
                case 'h':
                    System.out.println("头数据："+circle.headQueue());
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

class circleQueue {
    //数组容量最大值
    private int maxSize;
    //存放数据的数组
    private int[] circleArr;
    //头指针，指向数组第一个元素的位置，初始front=0
    private int front;
    //尾指针，指向数组最后一个元素的后一个位置
    private int rear;

    //构造方法
    public circleQueue(int maxSize){
        this.maxSize = maxSize;
        circleArr = new int[this.maxSize];
        //可以省略，因为默认值为0
        front = 0;
        rear = 0;
    }

    //判断数组是否满
    public boolean isFull(){
        if((rear+1) % maxSize == front){
            return true;
        }
        return false;
    }

    //判断数组是否空
    public boolean isEmpty(){
        if (rear == front) {
            return true;
        }
        return false;
    }

    //添加数据
    public void addQueue(int data){
        if(isFull()){
            System.out.println("数组满，无法添加数据...");
            return;
        }
        //rear指向了数组最后一个元素的后一位，直接将新的元素添加到rear指向的位置
        circleArr[rear] = data;
        //考虑取模，因为rear一直增加可能会超过数组容量
        rear = (rear + 1) % maxSize;
    }

    //获取数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("数组空，无法获取数据...");
        }
        //front指向数组第一个元素，返回玩第一元素需要front增加1
        //将front的值保存到一个临时变量
        //front+1考虑取模防止超出数组容量的情况发生
        int get = front;
        front = (front + 1) % maxSize;
        return circleArr[get];
    }

    //显示全部数据
    public void show(){
        if(isEmpty()){
            System.out.println("数组空，无法获取数据...");
            return;
        }
        //思路：从front开始遍历
        for (int i = front; i < front + size(); i++) {
            //i考虑取模，因为为环形数组，重复一圈后下标可能超过数组最大容量
            System.out.printf("circleArr[%d] = %d\n",i % maxSize,circleArr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear - front + maxSize) % maxSize;
    }

    //查看头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("数组空，无法获取数据...");
        }
        return circleArr[front];
    }
}
