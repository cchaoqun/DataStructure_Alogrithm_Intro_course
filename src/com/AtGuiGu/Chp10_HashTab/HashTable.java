package com.AtGuiGu.Chp10_HashTab;

import java.util.Scanner;

/*
 * @Description: 哈希表
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/23 21:06
 */
public class HashTable {
    public static void main(String[] args) {
        //创建HashTab
        HashTab hashTab = new HashTab(7);
        //用户的选择
        Scanner input = new Scanner(System.in);
        String key = "";

        while(true){
            //用户界面
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出");
            key = input.next();
            switch(key) {
                case "add":
                    System.out.println("请输入id:");
                    int id = input.nextInt();
                    System.out.println("请输入姓名");
                    String name = input.next();
                    //创建一个雇员添加到hashTab
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入查找雇员id:");
                    id = input.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    input.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建HashTab管理empLinkedList
class HashTab{
    //创建 empLinkedList[]数组管理empLinkedList
    private empLinkedList[] empLinkedListArr;
    //数组的大小
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        //这一步只初始化了hashTab的链表数组,未对数组中的每个数组进行初始化
        this.empLinkedListArr = new empLinkedList[size];
        //初始化链表数组中的每个链表
        for(int i=0; i<size; i++){
            empLinkedListArr[i] = new empLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据雇员的id决定添加到哪一条链表中
        //此处需要借助散列函数确定链表号
        int no = hashFunc(emp.id);
        //调用empLinkedList的add方法添加雇员
        empLinkedListArr[no].add(emp);
    }

    //遍历雇员信息
    public void list(){
        for(int i=0; i<size; i++){
            //调用empLinkedList的list方法遍历雇员信息
            empLinkedListArr[i].list(i);
        }
    }

    //查找雇员
    public void findEmpById(int id){
        //通过散列函数判断对应id应该被分配的链表
        int empLinkedListNo = hashFunc(id);
        //得到返回的Emp
        Emp emp = empLinkedListArr[empLinkedListNo].findEmpById(id);
        //判断返回值是否为null
        if(emp == null){
            //未找到
            System.out.println("未找到该雇员信息...");
        }else{
            //找到了,返回雇员信息
            System.out.printf("在第 %d 号链表找到: => id=%d name=%s\n",empLinkedListNo+1, emp.id,emp.name);
        }
    }

    //散列函数
    public int hashFunc(int no){
        return no % size;
    }
}

//雇员类
class Emp {
    public int id;
    public String name;
    //指向下一个结点
    public Emp next;
    //构造方法
    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }
}

//雇员链表类
class empLinkedList{
    //头结点
    private Emp head;
    //----------------添加雇员的方法----------------
    public void add(Emp emp){
        //需要找到当前链表的最后一个结点
        //先判断链表是否为空
        if (head == null) {
            //空链表,直接让添加的结点为头结点
            head = emp;
            return;
        }
        //链表不为空,需要借助临时指针找到链表的最后一个结点
        Emp curEmp = head;
        while (true) {
            //当前结点下一位为null 则找到末尾结点 break
            if (curEmp.next == null){
                break;
            }
            //当前结点不是末尾,指针后移一位
            curEmp = curEmp.next;
        }
        //退出循环时,指针指向的结点为末尾结点
        //将新节点添加到链表中
        curEmp.next = emp;
    }

    //----------------遍历链表的方法----------------
    //no代表遍历的是链表数组中的几号链表
    public void list(int no){
        //判断链表是否为空
        if(head == null){
            System.out.printf("第 %d 号链表为空...\n",no+1);
            return;
        }
        //链表不为空,需要借助临时指针遍历链表各个结点获取对应信息
        System.out.printf("第 %d 号链表结点信息为:",no+1);
        Emp curEmp = head;
        while(true){
            System.out.printf(" => id:%d name:%s\t",curEmp.id, curEmp.name);
            if(curEmp.next == null){
                //判断是否到达末尾结点
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //----------------通过id寻找雇员的方法----------------
    //找到则返回雇员, 未找到则返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head == null){
            System.out.println("当前链表为空...");
            return null;
        }
        //链表不为空,借助临时指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                //找到对应雇员
                return curEmp;
            }
            //没有找到,需判断curEmp是否为末尾结点,
            if(curEmp.next == null){
                //如果是末尾结点,则把末尾结点置为 null 并跳出循环
                curEmp = null;
                break;
            }
            //当前结点不是目标结点,且不是末尾结点,则后一一位
            curEmp = curEmp.next;
        }
        //结束循环,可能为
        //1.找到目标雇员,当前雇员为目标雇员
        //2.未找到目标雇员,遍历到链表结尾,且curEmp被置为null
        //两种情况下,都返回curEmp即可
        return curEmp;
    }

}
