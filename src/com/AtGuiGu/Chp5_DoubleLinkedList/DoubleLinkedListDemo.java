package com.AtGuiGu.Chp5_DoubleLinkedList;

/**
 * @Description: 双向链表的增删改查
 *
 *
 * @para:
 * @return:
 * @Author: CCQ
 * @Date: 2020/12/9
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双向链表的测试

        //1.创建几个结点
        DoubleNode hero1 = new DoubleNode(1,"宋江","及时雨");
        DoubleNode hero2 = new DoubleNode(2, "卢俊义", "玉麒麟");
        DoubleNode hero3 = new DoubleNode(3, "吴用", "智多星");
        DoubleNode hero4 = new DoubleNode(4, "林冲", "豹子头");

        //2.创建双向链表
        DoubleLinkedList dlist = new DoubleLinkedList();

        //3.添加结点到双向链表
        dlist.addNodeByOrder(hero1);
        dlist.addNodeByOrder(hero3);
        dlist.addNodeByOrder(hero2);
        dlist.addNodeByOrder(hero4);
        dlist.addNodeByOrder(hero4);


        //4.显示链表
        System.out.println("显示链表:");
        dlist.showNode();

        //5.更新信息
        System.out.println("更新后链表:");
        dlist.updataNode(new DoubleNode(4, "林冲1", "豹子头1"));
        dlist.showNode();

        //6.删除结点
        System.out.println("删除后链表:");
        dlist.delNode(4);
        dlist.showNode();
    }
}

class DoubleLinkedList {
    //创建头节点
    DoubleNode head = new DoubleNode(0, "", "");


    //------------------添加结点(默认添加到链表末尾)-------------------
    public void addNode(DoubleNode newNode) {
        //判断链表是否空
        if (head.next == null) {
            head.next = newNode;
            return;
        }
        //辅助结点遍历双向链表
        DoubleNode temp = head.next;
        while (true) {
            if (temp.next == null) {
                //找到链表尾结点,让其与待添加结点相互指向
                temp.next = newNode;
                newNode.pre = temp;
                break;
            }
            //未到链表末尾,结点后移一位
            temp = temp.next;
        }
    }

    //------------------添加结点(按链表的序号顺序添加)-------------------
    public void addNodeByOrder(DoubleNode newNode){
        //判断链表是否为空
        if(head.next == null){
            head.next = newNode;
            return;
        }
        //辅助结点
        DoubleNode temp = head.next;
        //辅助结点
        DoubleNode next = null;
        //flag标记重复序号结点
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.num > newNode.num){
                //找到需要插入的结点前一个结点
                break;
            }
            if(temp.next.num == newNode.num){
                //序号重复
                flag = true;
                break;
            }
            //辅助结点后移一位
            temp = temp.next;
        }
        //退出循环,判断flag
        if(flag){
            System.out.printf("%d号英雄%s已经存在,无法添加...\n",newNode.num,newNode.name);
            return;
        }else if(temp.next == null){
            //如果加入到链表的末尾
            temp.next = newNode;
            newNode.pre = temp;
        }else{
            //加入到链表的中间
            //将temp后续链表信息存储到next
            next = temp.next;
            //正向链接
            temp.next = newNode;
            newNode.next = next;
            //反向链接
            next.pre = newNode;
            newNode.pre = temp;
        }
    }


    //------------------修改链表结点信息-------------------
    public void updataNode(DoubleNode newNode){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空,无法修改...");
            return;
        }
        //辅助结点
        DoubleNode temp = head.next;
        while(true){
            if(temp == null){
                //遍历完链表未找到待更新结点
                System.out.printf("未找到需要修改的%d结点%s\n",newNode.num,newNode.name);
                break;
            }
            if(temp.num == newNode.num){
                //找待更新结点
                temp.name = newNode.name;
                temp.nickName = newNode.nickName;
                break;
            }
            //后移一位
            temp = temp.next;
        }
    }

    //------------------删除结点-------------------
    public void delNode(int no){
        //判断链表是否空
        if(head.next == null){
            System.out.println("链表为空,无法删除...");
            return;
        }
        //辅助结点
        DoubleNode temp = head.next;
        //
        while(true){
            if(temp == null){
                //遍历完成未找到待删除结点
                System.out.printf("未找到需要删除的%d结点\n",no);
                break;
            }
            if(temp.num == no){
                //找到待删除结点,实现自我删除
                //让该结点的前后结点互相指向即可
                temp.pre.next = temp.next;
                //这里需判断待删除结点是否为最后一个结点,是的话 temp.next = null
                if(temp.next != null){
                    temp.next.pre = temp.pre;
                }
                break;
            }
            //后移一位
            temp = temp.next;
        }
    }

    //------------------遍历链表-------------------
    public void showNode() {
        //判断链表是否空
        if(head.next == null){
            System.out.println("链表为空...");
            return;
        }
        //辅助结点
        DoubleNode temp = head.next;
        while(true){
            if(temp == null){
                //遍历完
                break;
            }
            //打印结点信息
            System.out.println(temp);
            //结点后移一位
            temp = temp.next;
        }

    }

}

class DoubleNode{
    //结点编号
    public int num;
    //结点data域
    public String name;
    public String nickName;
    //结点 pre域 指向前一个结点 默认为null
    public DoubleNode pre;
    //结点 next域 指向后一个结点 默认为null
    public DoubleNode next;

    //构造方法
    public DoubleNode(int num, String name, String nickName){
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    //toString


    @Override
    public String toString() {
        return "DoubleNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
