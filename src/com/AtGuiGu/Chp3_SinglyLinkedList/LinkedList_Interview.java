package com.AtGuiGu.Chp3_SinglyLinkedList;

import java.util.Stack;

public class LinkedList_Interview {

    public static void main(String[] args) {

    }

    //------------------------面试题1--------------------------
    /**
     * @Description: 获取单链表有效结点的个数
     *
     *
     * @para:链表的头结点
     * @return:链表有效结点的个数(如果有头结点,不包括)
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public static int getNodeNum(HeroNode head){
        if(head.next == null){
            System.out.println("链表为空...");
            return 0;
        }
        //借助临时结点遍历链表
        HeroNode current = head.next;
        //记录结点个数
        int len = 0;
        while(true){
            if(current != null){
                //当前结点不为空
                len++;
                current = current.next;
            }else{
                break;
            }
        }
        return len;
    }

    //------------------------面试题2--------------------------
    /**
     * @Description: 查找单链表中倒数第K个结点
     * 1. 编写一个方法接受head和一个index
     * 2. 遍历链表,获取链表的结点个数len
     * 3. 从链表的第一个开始遍历到(len-index)个结点
     * 4. 找到了返回该遍历到的结点,否则返回null
     *
     * @para:head链表的头结点 index链表的倒数第index个结点
     * @return:返回倒数第k个结点
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        //1.判断链表是否为空
        if(head.next == null){
            System.out.println("空链表...");
            return null;
        }
        //2.直接调用上一个获取单链表结点个数的方法,获取单链表结点个数
        int len = getNodeNum(head);
        //3.校验index是否有效(index <= 0? index<len?)
        if(index <= 0 || index > len){
            return null;
        }
        //4.确定需要遍历到 len-index个结点
        HeroNode returnNode = head.next;
        int iterNum = len - index;
        for (int i = 0; i < iterNum; i++) {
            returnNode = returnNode.next;
        }
        return returnNode;
    }

    //------------------------面试题3--------------------------
    /**
     * @Description: 将一个单链表改成相反的方向
     * 1.先定义一个新的结点 HeroNode reverseNode = new HeroNode()
     * 2.遍历原来的链表,每遍历一个结点,将该结点放到新链表reverseNode的最前端
     * 3.head.next = reverseNode.next
     *
     *
     * @para:原链表
     * @return:
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public static void reverseLinkedList(HeroNode head){
        //判断链表是否空,及是否只有一个结点
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助结点帮助遍历原链表
        HeroNode cur = head.next;
        //定义另一个辅助结点指向当前结点[cur]的下一个结点,保存后续链表信息,否则链表断掉
        HeroNode next = null;
        //定义一个新节点为新链表的头
        HeroNode reverseHead = new HeroNode(0,"","");
        while(cur != null){
            //保存后续链表信息
            next = cur.next;
            //后续链表指向新链表最前端的位置
            cur.next = reverseHead.next;
            //将当前结点插入到新链表的最前端
            reverseHead.next = cur;
            //当前结点向后移一位
            cur = next;
        }
        //原链表头指向新链表头的下一个结点
        head.next = reverseHead.next;
    }

    //------------------------面试题4--------------------------
    /** 
     * @Description: 将一个单链表逆序打印 
     * 利用栈数据结构,将各个结点压入栈内,利用栈先进后出的原理,逆序打印链表
     *
     * @para:
     * @return:
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public static void reversePrint(HeroNode head){
        //判断链表是否为空
        if(head.next == null){
            return;
        }
        //定义辅助结点遍历链表
        HeroNode temp = head.next;
        //定义栈,将结点压入栈中,利用先进后出的原理逆序打印
        Stack<HeroNode> stack = new Stack();
        //将链表的所有结点压入栈内
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中的结点进行打印, pop出栈
        while(stack.size() > 0){
            //先进后出逆序打印结点信息
            System.out.println(stack.pop());
        }
    }

    //------------------------面试题5--------------------------
    /**
     * @Description: 合并两个有序的单链表,合并后依旧是有序的
     * 1.创建一个新链表
     * 2.比较两个单链表的大小,把小的加进去,再发现下一个更小的再加进去
     * 3.如果一个链表全部合并进新链表说明剩余链表的所有结点都大于新链表,依次存入即可
     *
     * @para:将要被合并的两个链表
     * @return:合并以后的链表
     * @Author: CCQ
     * @Date: 2020/12/8
     */
    public static SingleLinkedList combineByOrder(SingleLinkedList list1, SingleLinkedList list2) {
        //创建一个新链表
        SingleLinkedList linkedList = new SingleLinkedList();
        //合并链表的表头
        HeroNode head = linkedList.getHead();
        //定义辅助结点,指向当前排序结点的前一个结点即合并链表的头
        HeroNode cur = head;
        //定义两个辅助结点,指向两个有序链表的首个结点
        HeroNode temp1 = list1.getHead().next;
        HeroNode temp2 = list2.getHead().next;
        //如果temp1 temp2其中一个为 null,代表其中一个链表遍历到末尾了
        while (temp1 != null && temp2 != null) {
            if (temp1.num < temp2.num) {
                //将两个链表中具有较小值的结点放在前,并且链表后移一位
                cur.next = temp1;
                temp1 = temp1.next;
            } else {
                cur.next = temp2;
                temp2 = temp2.next;
            }
            //cur结点后移一位
            cur = cur.next;
        }
        //退出循环表示某一链表结点遍历为null,将cur指向还有剩余结点的链表即可
        cur = (temp1 == null)? temp2:temp1;
//        if (temp1 == null && temp2 != null) {
//                cur = temp2;
//        } else{
//            cur = temp1;
//        }
        return linkedList;
    }
}