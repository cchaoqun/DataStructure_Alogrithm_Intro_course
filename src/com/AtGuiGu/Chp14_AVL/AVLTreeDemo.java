package com.AtGuiGu.Chp14_AVL;
/*
 * @Description: 平衡二叉树
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/29 16:55
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = new int[]{4,3,6,5,7,8};
//        int[] arr = new int[]{10,12, 8, 9, 7, 6};
        int[] arr = new int[]{10, 11, 7, 6, 8, 9 };
//        int[] arr = new int[]{2,1,6,5,7,3};
        AVLTree avl = new AVLTree();
        for(int i=0; i<arr.length; i++){
            avl.add(new Node(arr[i]));
        }

        //中序遍历
        System.out.println("中序遍历");
        avl.infixOrder();

        //查看树的高度
        System.out.println("在处理后~");
        System.out.println("avl树的高度: "+avl.getRoot().height());
        System.out.println("avl左子树的高度: "+avl.getRoot().leftHeight());
        System.out.println("avl右子树的高度: "+avl.getRoot().rightHeight());
        System.out.println("avl树的根结点: "+avl.getRoot());
        System.out.println("avl树的结点: "+avl.getRoot().right.right);
    }
}


class AVLTree{
    //根结点
    private Node root;

    public Node getRoot(){
        return this.root;
    }

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

    //左旋转方法
    public void leftRotate(){
        //1.创建一个新的结点,并将当前根结点的值赋给新节点
        Node newNode = new Node(this.value);
        //2.把新节点的左子树设置成当前结点左子树
        newNode.left = this.left;
        //3.把新节点的右子树设置成当前结点的右子树的左子树
        newNode.right = this.right.left;
        //4.把当前结点的值替换为右子节点的值
        this.value = this.right.value;
        //5.把当前结点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //6.把当前结点的左子树设置成新的结点
        this.left = newNode;
    }

    //右旋转方法
    public void rightRotate(){
        //1.创建一个新的结点,并将当前根结点的值赋给新节点
        Node newNode = new Node(this.value);
        //2.把新节点的右子树设置成当前根结点的右子树
        newNode.right = this.right;
        //3.把新节点的左子树设置成当前根结点左子树的右子树
        newNode.left = this.left.right;
        //4.把根结点的值设置成根结点的左子结点
        this.value = this.left.value;
        //5.把当前根结点的左子树设置成左子树的左子树
        this.left = this.left.left;
        //6.把当前根结点的右节点设置新节点
        this.right = newNode;
    }

    //返回当前结点左子树的高度
    public int leftHeight(){
        if(this.left==null){
            return 0;
        }
        return this.left.height();
    }

    //返回当前结点右子树的高度
    public int rightHeight(){
        if(this.right==null){
            return 0;
        }
        return this.right.height();
    }

    //返回以当前结点为根结点的树的高度
    public int height(){
        return Math.max(this.left==null? 0:this.left.height(), this.right==null? 0:this.right.height())+1;
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
        //添加完结点后需要判断是否需要左旋转
        //左旋转的条件是 rightHeight() - leftHeight() > 1
        if(rightHeight()-leftHeight() > 1){
            //如果当前结点的右节点的左子树高度大于右子树,需要对当前结点的右子树进行右旋转
            if(this.right!=null && this.right.leftHeight()>this.right.leftHeight()){
                //先对当前结点右子树进行右旋转
                this.right.rightRotate();
                //在对当前结点进行左旋转
                leftRotate();
            }else{
                //否则直接进行左旋转
                leftRotate();
            }
            //处理完必须return 因为后续没有必要
            return;

        }
        //添加完结点后需要判断是否需要右旋转
        //右旋转的条件是 leftHeight() - rightHeight() > 1
        if(leftHeight() - rightHeight() >1){
            //如果当前结点左子树的右子树高度大于左子树,需要先对当前结点的左子树进行左旋转
            if(this.left!=null && this.left.rightHeight()>this.left.leftHeight()){
                //先对当前结点左子树进行左旋转
                this.left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            }else{
                //否则直接进行右旋转
                rightRotate();
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