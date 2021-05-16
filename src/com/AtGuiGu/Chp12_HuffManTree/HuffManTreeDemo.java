package com.AtGuiGu.Chp12_HuffManTree;

import java.util.ArrayList;
import java.util.Collections;

/*
 * @Description: 创建霍夫曼树
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/28 10:23
 */
public class HuffManTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println("霍夫曼树前序遍历的结果:");
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("空树,无法遍历~");
        }
    }

    public static Node createHuffmanTree(int[] arr){
        //创建一个ArrayList<Node>
        ArrayList<Node> nodes = new ArrayList<>();

        //将数组中元素赋给结点并添加到ArrayList中
        for(int value:arr){
            nodes.add(new Node(value));
        }

        //排序 从小到大
        Collections.sort(nodes);
        //遍构建HuffmanTree
        while(nodes.size()>1){
            //取出集合中最小的结点变成左节点
            Node leftNode = nodes.get(0);
            //取出集合中第二小的结点变成右结点
            Node rightNode = nodes.get(1);

            //将取出的两个结点构成一二叉树
            Node parentNode = new Node(leftNode.value+rightNode.value);
            //将左右子节点与父节点连接
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //从集合中删除已经处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新生成的结点添加到集合中
            nodes.add(parentNode);

            //对处理后的结合进行重新排序
            Collections.sort(nodes);
        }
        //循环结束后,集合中只剩一个结点为霍夫曼树的根结点
        return nodes.get(0);
    }
}




class Node implements Comparable<Node>{
    //结点的权值
    int value;
    //左子节点
    Node left;
    //右子节点
    Node right;

    //前序遍历
    public void preOrder(){
        //输出当前结点
        System.out.println(this);
        //向左子节点递归遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //向右子节点递归遍历
        if(this.right!=null) {
            this.right.preOrder();
        }
    }

    //构造方法
    public Node(int value){
        this.value = value;
    }

    //toString
    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    //compareTo
    public int compareTo(Node o){
        //从小到大排列
        return this.value - o.value;
        //从大到小排列
//        return -(this.value - o.value);

    }
}
