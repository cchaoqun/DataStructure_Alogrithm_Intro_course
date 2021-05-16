package com.AtGuiGu.Chp11_Tree;

/*
 * @Description: 二叉树的前中后序遍历
 *
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 11:51
 */
public class Tree {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        HeroNode node6 = new HeroNode(6,"关胜左1");
        HeroNode node7 = new HeroNode(7,"关胜左2");
        HeroNode node8 = new HeroNode(8,"关胜右1");
        HeroNode node9 = new HeroNode(9,"林冲左1");
        HeroNode node10 = new HeroNode(10,"林冲右1");
        HeroNode node11 = new HeroNode(11,"林冲右2");

        //链接结点
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        node4.setLeft(node9);
        node4.setRight(node10);
        node10.setRight(node11);
        node5.setLeft(node6);
        node5.setRight(node7);
        node6.setLeft(node8);


        //将根结点赋给二叉树的根结点
        binaryTree.setRoot(root);
        //前序遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();//1 2 3 5 4
        //中序遍历
        System.out.println("中序遍历");
        binaryTree.inOrder();//2 1 5 3 4
        //后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrder();//2 5 4 3 1

        //前序查找
        System.out.println("前序查找");
        binaryTree.preSearch(5);
        //中序查找
        System.out.println("中序查找");
        binaryTree.inSearch(5);
        //后序查找
        System.out.println("后序查找");
        binaryTree.postSearch(5);

        //删除结点
//        System.out.println("删除前,前序遍历");
//        binaryTree.preOrder();
//        binaryTree.delNode(3);
//        System.out.println("删除后,前序遍历");
//        binaryTree.preOrder();

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode2(4);
        System.out.println("删除后,前序遍历");
        binaryTree.preOrder();


    }
}


class BinaryTree{
    //创建根结点
    private HeroNode root;
    public HeroNode getRoot(){
        return this.root;
    }
    //设置根结点的方法
    public void setRoot(HeroNode root){
        this.root = root;
    }

    //--------------------------遍历--------------------------
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空,无法遍历...");
        }
    }
    //中序遍历
    public void inOrder(){
        if(this.root != null){
            this.root.inOrder();
        }else{
            System.out.println("二叉树为空,无法遍历...");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空,无法遍历...");
        }
    }

    //--------------------------查找--------------------------
    //前序查找
    public void preSearch(int id){
        if(this.root != null){
            HeroNode resNode = this.root.preSearch(id);
            if(resNode != null){
                System.out.printf("%d号结点为目标结点,信息如下:\n",resNode.getNo());
                System.out.println(resNode);
            }else{
                System.out.printf("未找到%d号结点\n",id);
            }
        }else{
            System.out.println("二叉树为空,无法查找...");
        }
    }
    //中序查找
    public void inSearch(int id){
        if(this.root != null){
            HeroNode resNode = this.root.inSearch(id);
            if(resNode != null){
                System.out.printf("%d号结点为目标结点,信息如下:\n",resNode.getNo());
                System.out.println(resNode);
            }else{
                System.out.printf("未找到%d号结点\n",id);
            }
        }else{
            System.out.println("二叉树为空,无法查找...");
        }
    }
    //后序查找
    public void postSearch(int id){
        if(this.root != null){
            HeroNode resNode = this.root.postSearch(id);
            if(resNode != null){
                System.out.printf("%d号结点为目标结点,信息如下:\n",resNode.getNo());
                System.out.println(resNode);
            }else{
                System.out.printf("未找到%d号结点\n",id);
            }
        }else{
            System.out.println("二叉树为空,无法查找...");
        }
    }

    //--------------------------删除--------------------------
    public void delNode(int no) {
        //先判断当前二叉树根结点是否为空
        if (this.root != null) {
            if(this.root.getNo() == no){
                this.root = null;
            }else{
                this.root.delNode(no);
            }
        } else{
            System.out.println("当前二叉树为空,无法删除...");
            return;
        }
    }

    public void delNode2(int no) {
        //先判断当前二叉树根结点是否为空
        if (this.root != null) {
            if(this.root.getNo() == no){
                this.root = null;
            }else{
                this.root.delNode2(no);
            }
        } else{
            System.out.println("当前二叉树为空,无法删除...");
            return;
        }
    }
}





class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name){
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //--------------------------删除--------------------------
    public void delNode(int no){
        //先判断当前结点左子节点是否为待删除结点
        if(this.left !=null && this.left.no == no){
            //左子节点为待删除结点,将当前结点的左子节点置为null
            this.left = null;
            return;
        }
        //再判断当前结点右子节点是否为待删除结点
        if(this.right !=null && this.right.no == no){
            //右子节点为待删除结点,将当前结点的右子节点置为null
            this.right = null;
            return;
        }
        //左右子结点都不是待删除结点
        //判断左子结点是否为空,不为空则在左子结点递归删除
        if(this.left != null){
            this.left.delNode(no);
        }
        //判断右子结点是否为空,不为空则在右子结点递归删除
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    /*
     * @Description:
     * 如果要删除的节点是非叶子节点，
     * 现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则, 假如规定如下:
     * (1)如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
     * (2)如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A
     * @param null
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/24 17:14
     */
    public void delNode2(int no){
        //判断左子节点
        if(this.left != null && this.left.no == no){
            HeroNode temp = this.left;
            temp.left = this.left.left;
            temp.right = this.left.right;
            //找到该结点, 如果该结点为叶子结点直接删除
            if(this.left.left == null && this.left.right == null){
                this.left = null;
            }else if(this.left.left == null){
                //如果左子结点为空,那么右子结点一定不空,让右子节点代替该结点
                this.left = temp.right;
            }else if(this.left.right == null){
                //如果右子结点为空,那么左子结点一定不空,让左子节点代替该结点
                this.left = temp.left;
            }else {
                //左右子结点都不为空,则让左子结点代替该结点
                this.left = temp.left;
                this.left.right = temp.right;
            }
        }

        //判断右子节点
        if(this.right != null && this.right.no == no){
            HeroNode temp = this.right;
            temp.left = this.right.left;
            temp.right = this.right.right;
            //找到该结点, 如果该结点为叶子结点直接删除
            if(this.right.left == null && this.right.right == null){
                this.right = null;
            }else if(this.right.left == null){
                //如果左子结点为空,那么右子结点一定不空,让右子节点代替该结点
                this.right = temp.right;
            }else if(this.right.right == null){
                //如果右子结点为空,那么左子结点一定不空,让左子节点代替该结点
                this.right = temp.left;
            }else {
                //左右子结点都不为空,则让左子结点代替该结点
                this.right = temp.left;
                this.right.right = temp.right;
            }
        }

        //左右子结点都不为目标结点
        //向左子节点递归删除
        if(this.left != null){
            this.left.delNode2(no);
        }
        //向右子节点递归删除
        if(this.right != null){
            this.right.delNode2(no);
        }
    }





    //--------------------------遍历--------------------------
    //前序遍历
    public void preOrder(){
        //输出父节点
        System.out.println(this);
        //如果左子结点不为空,向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //如果右子结点不为空,向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void inOrder(){
        //如果左子结点不为空,向左子树中序遍历
        if(this.left != null){
            this.left.inOrder();
        }
        //输出父节点
        System.out.println(this);
        //如果右子节点不为空,向右子树中序遍历
        if(this.right != null){
            this.right.inOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //如果左子结点不为空,向左子树中序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //如果右子节点不为空,向右子树中序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //--------------------------查找--------------------------
    static int countPre = 0;
    static int countIn = 0;
    static int countPost = 0;
    //前序查找
    public HeroNode preSearch(int id){
        System.out.printf("第 %d 次查找, 当前查找 %d 号结点\n",++countPre,this.no);
        //先比较当前结点
        if(this.no == id){
            return this;
        }
        //用于后续递归return的结点
        HeroNode temp = null;
        //判断左子节点是否空,不空则向左子节点递归前序查找
        if(this.left != null){
            temp = this.left.preSearch(id);
        }
        //判断左递归是否找到
        if(temp != null){
            return temp;
        }
        //判断右子节点是否空,不空则向右子节点递归前序查找
        if(this.right != null){
            temp = this.right.preSearch(id);
        }
        return temp;
    }
    //中序查找
    public HeroNode inSearch(int id){
        //用于后续递归return的结点
        HeroNode temp = null;
        //先判断左子结点是否为空,不空则向左子节点递归中序查找
        if(this.left != null){
            temp = this.left.inSearch(id);
        }
        //判断左递归是否找到
        if(temp != null){
            return temp;
        }

        //此处为比较过程
        System.out.printf("第 %d 次查找, 当前查找 %d 号结点\n",++countIn,this.no);
        //判断当前结点是否为查找结点
        if(this.no == id){
            return this;
        }

        //判断右子节点是否为空,不为空则向右子节点递归中序遍历
        if(this.right != null){
            temp = this.right.inSearch(id);
        }

        return temp;
    }

    //后序查找
    public HeroNode postSearch(int id){
        //用于后续递归return的结点
        HeroNode temp = null;

        //先判断左子结点是否为空,不空则向左子节点递归中序查找
        if(this.left != null){
            temp = this.left.postSearch(id);
        }
        //判断左递归是否找到
        if(temp != null){
            return temp;
        }

        //判断右子节点是否为空,不为空则向右子节点递归中序遍历
        if(this.right != null){
            temp = this.right.postSearch(id);
        }
        //判断右递归是否找到
        if(temp != null){
            return temp;
        }

        //判断当前结点是否为查找结点
        System.out.printf("第 %d 次查找, 当前查找 %d 号结点\n",++countPost,this.no);
        if(this.no == id){
            return this;
        }
        return temp;
    }



}
