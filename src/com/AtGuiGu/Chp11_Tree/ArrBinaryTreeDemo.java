package com.AtGuiGu.Chp11_Tree;
/*
 * @Description: 顺序存储二叉树
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 18:57
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("数组的顺序二叉树前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("\n数组的顺序二叉树中序遍历");
        arrBinaryTree.infixOrder();
        System.out.println("\n数组的顺序二叉树后序遍历");
        arrBinaryTree.postOrder();

    }

}


/*
 * @Description:
 * 顺序存储二叉树一般考虑完全二叉树
 * 左子节点为 2*n+1
 * 右子节点为 2*n+2
 * 父节点为 (n-1)/2
 * n表示数组中的第几个元素 从0开始
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 19:01
 */
class ArrBinaryTree{
    //存储对应的数组
    private int[] arr;

    //构造方法
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    //重载遍历方法,让其每次都从根结点开始遍历
    public void preOrder(){
        this.preOrder(0);
    }
    public void infixOrder(){
        this.infixOrder(0);
    }
    public void postOrder(){
        this.postOrder(0);
    }


    //前序遍历
    public void preOrder(int index){//index 代表开始遍历的结点
        //先判断数组是否为null或者数组长度是否为0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,无法按照二叉树前序遍历");
        }
        //数组不为空
        //输出当前结点
        System.out.printf("%d ",arr[index]);
        //向左子节点递归前序遍历
        //判断左子节点的下标是否超出数组下标范围
        if(2*index+1 < arr.length){
            preOrder(2*index+1);
        }

        //向右子节点递归前序遍历
        //判断右子节点的下标是否超出数组长度范围
        if(2*index+2 < arr.length){
            preOrder(2*index+2);
        }
    }

    //中序遍历
    public void infixOrder(int index){
        //先判断数组是否为null或者数组长度是否为0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,无法按照二叉树中序遍历");
        }

        //先判断左子节点下标是否超出数组长度,再向左子节点递归中序遍历
        if(2*index+1 < arr.length){
            infixOrder(2*index+1);
        }
        //打印当前结点
        System.out.printf("%d ",arr[index]);
        //再判断右子节点下标是否超出数组长度,再向右子节点递归中序遍历
        if(2*index+2 < arr.length){
            infixOrder(2*index+2);
        }
    }

    //后序遍历
    public void postOrder(int index){
        //先判断数组是否为null或者数组长度是否为0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,无法按照二叉树后序遍历");
        }

        //先判断左子节点下标是否超出数组长度,再向左子节点递归后序遍历
        if(2*index+1 < arr.length){
            postOrder(2*index+1);
        }
        //再判断右子节点下标是否超出数组长度,再向右子节点递归后序遍历
        if(2*index+2 < arr.length) {
            postOrder(2 * index + 2);
        }
        //打印当前结点
        System.out.printf("%d ",arr[index]);

    }
}
