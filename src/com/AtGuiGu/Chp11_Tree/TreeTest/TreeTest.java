package com.AtGuiGu.Chp11_Tree.TreeTest;

/*
 * @Description: 手写二叉树练习 用时40分钟
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/25 10:45
 */
public class TreeTest {
    public static void main(String[] args) {

        //创建一个二叉树
        BiTree binaryTree = new BiTree();

        //创建结点
        Node root = new Node(1,"宋江");
        Node node2 = new Node(2,"吴用");
        Node node3 = new Node(3,"卢俊义");
        Node node4 = new Node(4,"林冲");
        Node node5 = new Node(5,"关胜");

        //链接结点
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        binaryTree.setRoot(root);

        //遍历
        binaryTree.preOrder();
        //查找
        System.out.println("查找:");
        System.out.println(binaryTree.preSearch(2));
        //删除
        binaryTree.delNodePart(3);
        System.out.println("删除后");
        binaryTree.preOrder();


    }
}

class BiTree{
    private Node root;
    private Node pre = null;
    public void setRoot(Node root){
        this.root = root;
    }
    public Node getRoot(){
        return this.root;
    }
    //前序遍历
    public void preOrder(){
        if(this.root == null){
            return;
        }else{
            this.root.preOrder();
        }
    }

    //前序查找
    public Node preSearch(int no){
        if(this.root == null){
            return null;
        }else if(this.root.preSearch(no) != null){
            return this.root.preSearch(no);
        }else{
            return null;
        }
    }

    //删除
    public void delNodePart(int no){
        if(this.root != null){
            if(this.root.getNo() == no){
                this.root = null;
            }else{
                this.root.delNodePart(no);
            }
        }else{
            return;
        }
    }

    //线索化
    public void threadedNodes(Node node){
        if(node == null){
            return;
        }
        threadedNodes(node.getLeft());
        if(node.getLeft()!=null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getRight()!=null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadedNodes(node.getRight());
    }

}


class ArrBiTree{
    private int[] arr;
    public ArrBiTree(int[] arr){
        this.arr = arr;
    }
    //前序遍历
    public void preOrder(int index){
        if(arr==null || arr.length == 0){
            return;
        }
        System.out.println(arr[index]);
        if(2*index+1 < arr.length){
            preOrder(2*index + 1);
        }
        if(2*index+2 < arr.length){
            preOrder(2*index+2);
        }
    }
}


class Node{
    private int no;
    private String name;
    private Node left;
    private Node right;

    private int leftType;
    private int rightType;

    public Node(int no, String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" + "no=" + no + ", name='" + name + '\'' + '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //前序查找
    public Node preSearch(int no){
        if(this.no == no){
            return this;
        }
        Node temp = null;
        if(this.left != null){
            temp = this.left.preSearch(no);
        }
        if(temp!=null){
            return temp;
        }
        if(this.right != null){
            temp = this.right.preSearch(no);
        }
        return temp;
    }

    //删除结点
    public void delNodePart(int no){
        if(this.left!=null && this.left.no == no){
            Node temp = this.left;
            temp.left = this.left.left;
            temp.right = this.left.right;
            if(temp.left==null && temp.right==null){
                this.left = null;
            }else if(temp.left==null){
                this.left = temp.right;
            }else if(temp.right==null){
                this.left = temp.left;
            }else{
                this.left = temp.left;
                this.left.right = temp.right;
            }
        }

        if(this.right!=null && this.right.no==no){
            Node temp = this.right;
            temp.left = this.right.left;
            temp.right = this.right.right;
            if(temp.left==null && temp.right==null){
                this.right = null;
            }else if(temp.left == null){
                this.right = temp.right;
            }else if(temp.right == null){
                this.right = temp.left;
            }else{
                this.right = temp.left;
                this.right.right = temp.right;
            }
        }
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
