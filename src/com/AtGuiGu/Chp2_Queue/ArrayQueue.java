package com.AtGuiGu.Chp2_Queue;

import java.util.Scanner;

/**
 * @Description: 数组实现队列
 *
 *
 *
 *
 * @Author: CCQ
 * @Date: 2020/12/7
 */
public class ArrayQueue {
    public static void main(String[] args) {
        //队列测试
        //创建队列，并给予最大容量
        Queue queue = new Queue(3);
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        //接受用户的选择
        char choice;
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
                    queue.show();
                    break;
                case 'e':
                    input.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入添加的数据");
                    int data = input.nextInt();
                    queue.addQueue(data);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("从队列获得数据%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int head = queue.headQueue();
                        System.out.printf("头数据为%d\n",head);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
        System.out.println("退出队列...");
    }
}
class Queue{
    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //数组，用于存放数据
    private int[] arr;

    //构造方法，传入最大数组大小
    public Queue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        //front 指向队列头的前一个位置
        this.front = -1;
        //rear 指向队列尾的数据
        this.rear = -1;
    }

    //判断队列是否满
    public boolean isFull(){
        //队列尾指向数组最后一个位置
        return rear == this.maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        //front 和 rear相等
        return front == rear;
    }

    //向队列添加数据
    public void addQueue(int data){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，无法添加数据...");
            return;
        }
        //队列尾后移一位
        rear++;
        arr[rear] = data;
    }

    //从队列取数据
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //队列空则抛出异常并且自动退出了方法
            throw new RuntimeException("队列空，无法获取数据...");
        }
        front++;
        return arr[front];
    }

    //显示队列数据
    public void show(){
        if(isEmpty()){
            System.out.println("队列空，没有数据...");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

    //显示队列头数据
    public int headQueue(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列空，没有数据...");
        }
        return arr[front + 1];
    }


}
