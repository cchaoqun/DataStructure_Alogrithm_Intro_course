package com.AtGuiGu.Chp3_SinglyLinkedList;

import static com.AtGuiGu.Chp3_SinglyLinkedList.LinkedList_Interview.*;

/**
 * @Description: 单链表实现水浒传英雄排行榜管理
 *
 *
 *
 * 
 * @Author: CCQ
 * @Date: 2020/12/8
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //1.创建几个结点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //2.创建一个链表
        SingleLinkedList list = new SingleLinkedList();

//        //3.添加结点到链表
//        list.addByOrder(hero1);
//        list.addByOrder(hero3);
//        list.addByOrder(hero4);
//        list.addByOrder(hero2);

        //3.测试每次添加结点都添加到链表头部
        list.addAfterHead(hero1,list.getHead());
        list.addAfterHead(hero3,list.getHead());
        list.addAfterHead(hero4,list.getHead());
        list.addAfterHead(hero2,list.getHead());

        //4.显示链表
        list.show();

        //5.更新链表结点信息
        list.update(new HeroNode(4, "林冲1", "豹子头1"));
        System.out.println("结点更新后:");
        list.show();

        //6.删除结点
//        list.del(1);
//        list.del(2);
//        System.out.println("删除后链表的情况");
//        list.show();

        //-----------------------面试题测试---------------------------
        //7.判断当前链表的有效结点个数
        int lenResult = getNodeNum(list.getHead());
        System.out.printf("该单链表的有效结点个数为: %d\n",lenResult);

        //8.查找单链表中倒数第K个结点
        HeroNode resultNode = findLastIndexNode(list.getHead(),2);
        System.out.println("倒数第?个结点为:"+resultNode);

        //9.将一个单链表反转
        System.out.println("反转后链表为：");
        reverseLinkedList(list.getHead());
        list.show();

        //10.逆序打印单链表
        System.out.println("逆序打印单链表");
        reversePrint(list.getHead());

        //11.按顺序合并两个有序链表

        SingleLinkedList list2 = new SingleLinkedList();
        SingleLinkedList list3 = new SingleLinkedList();

        HeroNode h1 = new HeroNode(1,"xxx", "xxx");
        HeroNode h2 = new HeroNode(3,"xxx", "xxx");
        HeroNode h3 = new HeroNode(6,"xxx", "xxx");
        HeroNode h4 = new HeroNode(11,"xxx", "xxx");
        HeroNode h5 = new HeroNode(20,"xxx", "xxx");
        list2.addByOrder(h1);
        list2.addByOrder(h2);
        list2.addByOrder(h3);
        list2.addByOrder(h4);
        list2.addByOrder(h5);
        HeroNode e1 = new HeroNode(1,"xxx", "xxx");
        HeroNode e2 = new HeroNode(2,"xxx", "xxx");
        HeroNode e3 = new HeroNode(5,"xxx", "xxx");
        HeroNode e4 = new HeroNode(15,"xxx", "xxx");
        HeroNode e5 = new HeroNode(17,"xxx", "xxx");
        HeroNode e6 = new HeroNode(22,"xxx", "xxx");
        HeroNode e7 = new HeroNode(23,"xxx", "xxx");
        list3.addByOrder(e1);
        list3.addByOrder(e2);
        list3.addByOrder(e3);
        list3.addByOrder(e4);
        list3.addByOrder(e5);
        list3.addByOrder(e6);
        list3.addByOrder(e7);

        System.out.println("合并的链表1");
        list2.show();
        System.out.println("合并的链表2");
        list3.show();
        SingleLinkedList combineList = combineByOrder(list2,list3);
        System.out.println("按顺序合并两个有序链表");
        combineList.show();
    }

}

class SingleLinkedList{
    //创建头结点，头结点不可动且不存储数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead(){
        return this.head;
    }

    //------------------方法1不按顺序添加结点----------------------
    /**
     * @Description:  添加结点到单链表的思路(不考虑顺序编号)
     * 1. 找到当前链表的最后结点
     * 2， 将最后一个结点的 next指向新的结点
     * @para:
     * @return:
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public void addNode(HeroNode heroNode){
        //头结点不可动，依赖临时结点，遍历链表
        HeroNode temp = head;
        while(true){
            //判断 temp.next == null
            if(temp.next == null){
                //temp.next == null，说明temp就指向了链表的最后
                //将最后结点的next指向新的结点
                temp.next = heroNode;
                break;
            }
            //temp结点依次移动，直到找到最后一个结点
            temp = temp.next;
        }

    }
    //------------------方法2按顺序添加结点----------------------
    /**
     * @Description:  按顺序添加结点到链表,根据新英雄的排名添加
     * 如果该排名的英雄已经存在,则添加失败,并给出提示
     *
     * @para:
     * @return:
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能移动,依赖临时结点完成链表的遍历
        //因为是单链表,找到的位置是应当添加位置的前一个结点,否则无法插入该新结点
        HeroNode temp = head;
        //flag用来判断链表是否已经存在排名相同的结点,默认false
        boolean flag = false;
        while(true){
            if(temp.next == null){
                //已经遍历到链表的末尾,则退出循环
                break;
            }
            if(temp.next.num > heroNode.num){
                //如果temp后一个结点的排名大于新添加结点的排名,则该temp为应该添加位置的前一个结点
                break;
            }else if(temp.next.num == heroNode.num){
                //如果该结点下一个结点的排名等于新添加的结点的排名,改变flag为true,添加失败
                //此处必须使用当前结点的下一个结点排名判断,因为如果下一个结点为最后一个结点,首先第一个判断条件就已经退出了循环体,无法确认该结点已经存在
                flag = true;
                break;
            }
            //以上情况都未发生,则temp后移一位
            temp = temp.next;
        }
        //退出循环.首先判断是否存在排名相同的结点即 flag=true
        if(flag){
            //给出提示
            System.out.printf("%d号英雄%s已经存在,添加失败...\n",heroNode.num,heroNode.name);
            //退出方法
            return;
        }else {
            //另外两种情况,temp后一个位置就是新添加元素应该插入的位置
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //------------------方法2.1添加结点到链表得最前面----------------------
    /**
     * @Description: 每次添加结点都直接加到链表的最前面即head得后面
     *
     *
     * @para:
     * @return:
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public void addAfterHead(HeroNode newHeroNode,HeroNode head){
        //判断链表是否空
        if(head.next == null){
            //空链表，直接把新节点加到头节点后面
            head.next = newHeroNode;
            return;
        }
        //将新的结点加到head之后，第一个结点之前
        newHeroNode.next = head.next;
        head.next = newHeroNode;
    }

    //------------------方法3修改结点信息----------------------
    public void update(HeroNode newHeroNode){
        //根据给予结点的编号,修改结点的信息
        //1.先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空,无可修改结点...");
            return;
        }
        //2.借助临时结点temp遍历链表
        HeroNode temp = head.next;
        //标记找到了需要更新的结点
        boolean flag = false;
        while(true){
            if(temp == null){
                //不是链表的最后一个结点而是链表已经遍历结束
                break;
            }
            if(temp.num == newHeroNode.num){
                //找到需要修改信息的结点,改变flag为true
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //退出while循环,判断flag,是否找到对应结点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            //未找到
            System.out.printf("未找到%d结点,不能修改\n",newHeroNode.num);
        }

    }

    //------------------方法4删除结点----------------------
    public void del(int no){
        //删除编号未no的结点
        //1.判断链表是否为空
        if(head.next == null){
            System.out.println("空链表,无法执行操作...");
            return;
        }
        //2.遍历链表,找到需要删除的结点前一个结点temp
        HeroNode temp = head;
        //标记找到需要删除的结点
        boolean flag = false;
        while(true){
            if(temp.next == null){
                //遍历到链表的最后
                break;
            }
            //注意，我们需要判断的时 temp.next.num 和 no
            if(temp.next.num == no){
                //找到需要删除的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //退出循环，判断flag
        if(flag){
            //3.temp.next = temp.next.next
            temp.next = temp.next.next;
            //被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
        }else{
            System.out.printf("未找到需要删除的%d号结点\n",no);
        }
    }

    //------------------方法5显示链表----------------------
    //显示链表[遍历]
    public void show(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空...");
            return;
        }
        //借助临时结点遍历链表
        HeroNode temp = head.next;
        while(true){
            //当temp为空时退出循环
            if(temp == null){
                break;
            }
            //打印结点信息
            System.out.println(temp);
            //将结点后移
            temp = temp.next;
        }
    }

}


//-----------------------结点类-----------------------
class HeroNode{
    //英雄编号
    public int num;
    //英雄名称
    public String name;
    //英雄昵称
    public String nickName;
    //英雄结点 next域
    public HeroNode next;

    //创建构造方法
    public HeroNode(int no, String name, String nickname){
        this.num = no;
        this.name = name;
        this.nickName = nickname;
    }

    //方便显示，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
