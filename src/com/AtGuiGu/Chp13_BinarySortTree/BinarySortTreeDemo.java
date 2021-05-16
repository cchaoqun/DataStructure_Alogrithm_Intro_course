package com.AtGuiGu.Chp13_BinarySortTree;
/*
 * @Description: 二叉排序树
 *
 * @param null 
 * @return 
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/29 11:55
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree bst = new BinarySortTree();
        for(int i=0; i<arr.length; i++){
            bst.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树:");
        bst.infixOrder();

        //测试删除叶子结点
//        bst.delNode(2);
//        bst.delNode(5);
//        bst.delNode(9);
//        bst.delNode(12);
//        bst.delNode(1);

        //测试删除有一个子树的结点
//        bst.delNode(1);

        //测试删除有两个子树的结点
        bst.delNode(10);

        //全部删除
        bst.delNode(7);
        bst.delNode(3);
        bst.delNode(10);
        bst.delNode(1);
        bst.delNode(5);
        bst.delNode(9);
        bst.delNode(12);
        bst.delNode(2);
        System.out.println("删除结点后~");
        bst.infixOrder();
    }
}

class BinarySortTree{
    //根结点
    private Node root;

    //查找结点
    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找结点的父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    //找右子树的最小值
    //1.返回以指定 node为根结点的二叉排序树的最小结点值
    //2.并删除最大结点
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点,就会找到最小值
        while(target.left!=null){
            target = target.left;
        }
        //退出循环,target为根结点或者最左子节点 都为该树种的最小值
        int temp = target.value;
        delNode(temp);
        return temp;
    }

    //找左子树的最大值
    //1.返回以指定 node为根结点的二叉排序树的最大结点值
    //2.并删除最大结点
    public int delLeftTreeMax(Node node){
        Node target = node;
        //循环查找右子节点,就会找到最大值
        while(target.right!=null){
            target = target.right;
        }
        //退出循环,target为根结点或者最右子节点, 都为该树的最大值
        //删除该最大值节点
        int temp = target.value;
        delNode(temp);
        return temp;
    }

    //删除结点
    public void delNode(int value){
        if(root == null){
            return;
        }else {
            //查找待删除结点
            Node targetNode = search(value);
            //判断是否找到待删除结点
            if (targetNode == null) {
                //未找到
                return;
            }
            //判断要删除的结点是否刚好是根结点,即没有父节点的情况
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //查找父节点
            Node parentNode = searchParent(value);
            //情况一 待删除结点为叶子结点
            if (targetNode.left == null && targetNode.right == null) {//待删除结点左右子节点均为null
                //判断待删除结点为父节点的 左子节点还是右子节点
                if (parentNode.left != null && value == parentNode.left.value) {
                    //是左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    //是右子节点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//情况二 待删除结点有两个子节点
                //方法一
                //从target的右子树找到最小的结点 返回最小结点的值并删除最小结点
                int rightTreeMin = delRightTreeMin(targetNode.right);
                //将target的值替换成找到的右子树的最小值
                targetNode.value = rightTreeMin;
                //方法二
//                //从target的左子树找到最大的结点,返回最大结点的值并删除最大结点
//                int leftTreeMax = delLeftTreeMax(targetNode.left);
//                //将target的值替换成找到的左子树的最大值
//                targetNode.value = leftTreeMax;

            } else {//情况三 待删除结点有一个子节点
                //1.targetNode的子节点是左子节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //1.1targetNode是parent的左子节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else {
                            //1.2targetNode是parent的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//2.targetNode的子节点时右子节点
                    if (parentNode != null) {
                        //2.1targetNode是parent的左子节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else {
                            //2.2targetNode是parent的右子节点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }

            }
        }

    }


    //添加结点
    public void add(Node node){
        if(node == null){
            return;
        }
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root==null){
            System.out.println("当前二叉树为空~~");
        }else{
            root.infixOrder();
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断待添加结点与当前结点的value的大小关系
        if(node.value < this.value){
            //待添加结点value小于当前结点
            //判断当前结点左子节点是否为空
            if(this.left == null){
                this.left = node;
            }else{
                //在左子节点递归添加
                this.left.add(node);
            }
        }else{
            //待添加结点value大于等于当前结点
            //判断当前结点右子节点是否为空
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    //查找待查找的结点
    public Node search(int value){
        if(this.value == value){
            //该节点为待查找结点
            return this;
        }else if(value<this.value){//查找值小于当前结点,向左子节点递归查找
            //如果左子节点为空
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else{//查找值大于等于当前结点,向右子节点递归查找
            //如果右子节点为空
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找待查找结点的父节点
    public Node searchParent(int value){
        if((this.left!=null && this.left.value==value)
                || (this.right!=null && this.right.value==value)){
            //当前结点为待查找结点的父节点
            return this;
        }else if(value < this.value && this.left!=null){
            //查找值小于当前结点值并且左子节点存在,向左子节点递归查找
            return this.left.searchParent(value);
        }else if(value >= this.value && this.right!=null){
            //查找值大于当前结点值并且右子节点存在,向右子节点递归查找
            return this.right.searchParent(value);
        }else{
            //查找结点不存在
            return null;
        }
    }
}
