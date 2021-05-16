package com.AtGuiGu.Chp4_SinglyCircleLinkedList;


public class SinglyCircleLinkedListDemo {
    public static void main(String[] args) {
        //测试环形链表
        //1.创建环形链表
        SinglyCircleLinkedList sclist = new SinglyCircleLinkedList();

        //2.调用addBoy方法添加nums个小孩
        sclist.addBoy(5);

        //3.调用showBoy方法显示链表
        sclist.showBoy();

        //4.调用boyPop方法解决约瑟夫问题
        System.out.println("约瑟夫问题:");
        sclist.popBoy(10,20, 125);
    }
}

class SinglyCircleLinkedList {
    //创建一个first结点,不赋值
    Boy first = null;


    //----------添加小孩结点方法, nums表示要添加小孩的个数----------
    public void addBoy(int nums) {
        //效验添加数量是否合规,小于2,约瑟夫问题无意义
        if (nums < 2) {
            System.out.println("nums值不符合规定...");
            return;
        }
        //辅助结点
        Boy curBoy = null;
        //for循环添加小孩结点
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //判断是否添加的为第一个小孩结点
            if (i == 1) {
                //first指向该结点
                first = boy;
                //形成环形
                first.setNext(first);
                //辅助结点指向first
                curBoy = first;
            }
            //如果添加为后续节点
            //curBoy指向新添加结点
            curBoy.setNext(boy);
            //新添加结点指向first
            boy.setNext(first);
            //curboy指向最新添加的小孩结点
            curBoy = boy;
        }
    }

    //-----------------遍历单向环形链表-------------------
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空,无法遍历...");
            return;
        }
        //辅助结点
        Boy curboy = first;
        //结束循环条件为curboy.next == first
        while (true) {
            System.out.printf("小孩的编号 %d \n", curboy.getNum());
            //判断是否达到first前一个结点
            if (curboy.getNext() == first) {
                //遍历完成
                break;
            }
            //辅助结点后移
            curboy = curboy.getNext();
        }
    }

    //--------------实现约瑟夫问题,小孩出圈----------------
    /*
     * @Description:
     *
     * @param startNum 起始小孩编号
     * @param callNum 叫号次数
     * @param boyNum 围成圈的小孩个数
     * @return void
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/9 15:17
     */

    public void popBoy(int startNum, int callNum, int boyNum) {
        //效验每次叫号数,起始数与小孩个数的合规性
        if(first == null || startNum>boyNum || callNum <= 0 || startNum < 1){
            System.out.println("输入数据不符合规定,无法游戏...");
            return;
        }
        //创建指定个数的小孩圈
        addBoy(boyNum);
        //辅助结点
        Boy start = first;
        Boy before = first;
        //1.遍历找到起始小孩结点,让start指向起始小孩结点,before指向起始小孩结点前一个
        while(true){
            //先判断statrNum==1
            if(startNum == 1){
                //遍历链表,让before指向first前一个结点
                while(before.getNext() != first){
                    before = before.getNext();
                }
                //退出循环,完成start指向first,before指向first前一个
                break;
            }
            //起始编号不等于1
            if(before.getNext().getNum() == startNum){
                //如果下一个是起始小孩,start后移一位,before不动
                start = start.getNext();
                break;
            }
            //下一个不是起始小孩, start before都后移一位
            start = start.getNext();
            before = before.getNext();

        }
        //2.小孩开始报数,起始结点报1,start before一共移动 callNum-1次
        while(boyNum!=0){
            for(int i = 0; i<callNum-1; i++){
                start = start.getNext();
                before = before.getNext();
            }

            //结束循环后,start指向待出圈结点,打印待出圈结点num
            System.out.printf("%d号小孩出圈\n",start.getNum());
            //start再次向后移动一次
            start = start.getNext();
            //让before指向start,即完成结点出圈
            before.setNext(start);
            //完成一次出圈,可出圈小孩数量减1
            boyNum--;
        }
    }
}

class Boy{
    //编号
    private int num;
    //next域
    private Boy next;

    //创建构造方法
    public Boy (int no) {
        this.num = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "num=" + num +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}


