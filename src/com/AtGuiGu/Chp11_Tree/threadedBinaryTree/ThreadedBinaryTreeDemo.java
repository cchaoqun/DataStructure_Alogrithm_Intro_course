package com.AtGuiGu.Chp11_Tree.threadedBinaryTree;

/*
 * @Description: 线索化二叉树
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/24 20:25
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //创建结点
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //链接结点
        root.setLeft(node2);
        root.setRight(node3);
        node2.setParent(root);
        node3.setParent(root);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);

        //创建中序线索二叉树
//        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
//        //设置线索二叉树的根节点为创建的root结点
//        threadedBinaryTree.setRoot(root);

        //--------------------中序线索化二叉树--------------------
//        System.out.println("--------------------中序线索化二叉树--------------------");
//        //线索化整个二叉树
//        threadedBinaryTree.infixthreadedNode();
//        //获取被线索化的结点的左右结点
//        System.out.println("node4的左右结点");
//        System.out.println("node4.left= "+node4.getLeft());
//        System.out.println("node4.right= "+node4.getRight());
//        System.out.println("node5的左右结点");
//        System.out.println("node5.left= "+node5.getLeft());
//        System.out.println("node5.right= "+node5.getRight());
//        //线索化二叉树的遍历
//        System.out.println("中序线索化二叉树的遍历");
//        threadedBinaryTree.infixOrderThreadedNode();

        //--------------------前序序线索化二叉树--------------------
//        System.out.println("--------------------前序线索化二叉树--------------------");
//        ThreadedBinaryTree preThreaded = new ThreadedBinaryTree();
//        preThreaded.setRoot(root);
//        preThreaded.preThreadedNode();
//        System.out.println("node4的左右结点");
//        System.out.println("node4.left= "+node4.getLeft());
//        System.out.println("node4.right= "+node4.getRight());
//        System.out.println("node5的左右结点");
//        System.out.println("node5.left= "+node5.getLeft());
//        System.out.println("node5.right= "+node5.getRight());
//        preThreaded.preOrderThreadedNode();

        //--------------------后序序线索化二叉树--------------------
        System.out.println("--------------------后序线索化二叉树--------------------");
        ThreadedBinaryTree postThreaded = new ThreadedBinaryTree();
        postThreaded.setRoot(root);
        postThreaded.postThreadedNode();
        System.out.println("node4的左右结点");
        System.out.println("node4.left= "+node4.getLeft());
        System.out.println("node4.right= "+node4.getRight());
        System.out.println("node5的左右结点");
        System.out.println("node5.left= "+node5.getLeft());
        System.out.println("node5.right= "+node5.getRight());
        postThreaded.postOrderThreadedNode();
    }
}

//线索二叉树
class ThreadedBinaryTree{
    //创建根结点
    private HeroNode root;

    //需要一个指向前驱结点的指针 1 2 3分别分配给前中后序线索化
    private HeroNode pre1 = null;
    private HeroNode pre2 = null;
    private HeroNode pre3 = null;

    //----------重载线索化的方法,直接从根结点开始线索化------------
    public void infixthreadedNode(){
        this.infixthreadedNode(root);
    }
    public void preThreadedNode(){
        this.preThreadedNode(root);
    }
    public void postThreadedNode(){
        this.postThreadedNode(root);
    }

    //-----------遍历前序线索化二叉树的方法-----------
    public void preOrderThreadedNode(){
        HeroNode node = root;
        while(node!=null){
            System.out.println(node);
            while(node.getLeftType() == 0){
                node = node.getLeft();
                System.out.println(node);
            }
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //-----------遍历中序线索化二叉树的方法-----------
    public void infixOrderThreadedNode(){
        //从根结点开始遍历
        HeroNode node = root;
        //在当前结点不为空的情况下循环遍历
        while(node!=null){
            //中序线索化二叉树的遍历
            //首先判断结点的左指针是否被线索化
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //退出循环表示找到了第一个左指针被线索化的结点
            //输出该结点
            System.out.println(node);
            //然后判断该结点的右指针是否被线索化,如果被线索化,则按照右指针指向的方向依次打印
            //因为该右指针指向的就是中序情况下的遍历顺序
            while(node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            //退出循环表示当前结点右指针未被线索化
            //让当前结点变成右子节点,继续循环遍历
            node = node.getRight();
        }

    }

    //-----------遍历后序线索化二叉树的方法-----------
    public void postOrderThreadedNode(){
        HeroNode node = root;

        //先找到最左子节点
        while(node.getLeftType()==0){
            node = node.getLeft();
        }
        HeroNode pre = null;
        while(node!=null){
            //找到最左子节点
            if(node.getRightType()==1){
                System.out.println(node);
                pre = node;
                node = node.getRight();
            }else{
                if(node.getRight() == pre){
                    System.out.println(node);
                    if(node == root){
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                }else{
                    //从树的另一边进入找到最左子节点
                    node = node.getRight();
                    while(node!=null && node.getLeftType()==0){
                        node = node.getLeft();
                    }
                }
            }
        }


    }


    //---------------前序线索化二叉树的方法-------------------
    public void preThreadedNode(HeroNode node){
        if(node==null){
            return;
        }
        //线索化当前结点
        if(node.getLeft()==null){
            node.setLeft(pre1);
            node.setLeftType(1);
        }
        if(pre1!=null && pre1.getRight()==null){
            pre1.setRight(node);
            pre1.setRightType(1);
        }
        pre1 = node;
        //线索化左子节点
        //如果左子节点的左指针已经被线索化则无需线索化
        if(node.getLeftType()!=1){
            preThreadedNode(node.getLeft());
        }

        //线索化右子节点
        //如果右子节点的右指针已经被线索化则无需线索化
        if(node.getRightType()!=1){
            preThreadedNode(node.getRight());
        }

    }



    //---------------中序线索化二叉树的方法-------------------
    //实际是线索化结点,当结点的左右指针为空时,用来指向对应顺序遍历的前驱后继结点
    public void infixthreadedNode(HeroNode node){
        //先判断当前结点是否为空
        if(node == null){
            System.out.println("当前结点为空,无法线索化...");
            return;
        }
        //当前结点不为空
        //线索化左子节点
        infixthreadedNode(node.getLeft());

        //线索化当前结点
        //先判断当前结点的左子节点是否为空
        if(node.getLeft() == null){
            //左子节点为空,让其指向pre 前驱结点
            node.setLeft(pre2);
            //改变左子节点类型为线索指针
            node.setLeftType(1);
        }
        //判断前序结点的右子节点是否为空,并且需要前序结点不能为空
        if(pre2!=null && pre2.getRight() == null){
            //前驱结点右子节点为空,让前驱结点的右指针指向当前结点
            pre2.setRight(node);
            //将前驱结点的右子节点的类型设为线索指针
            pre2.setRightType(1);
        }

        //处理完当前结点以后让前序结点变成当前结点!!!!!!!!!!!!!!!!!
        pre2 = node;

        //线索化右子节点
        infixthreadedNode(node.getRight());
    }

    //---------------后序线索化二叉树的方法-------------------
    public void postThreadedNode(HeroNode node){
        if(node==null){
            return;
        }
        //线索化左子节点
        postThreadedNode(node.getLeft());


        //线索化右子节点
        postThreadedNode(node.getRight());

        //线索化当前结点
        if(node.getLeft()==null){
            node.setLeft(pre3);
            node.setLeftType(1);
        }
        if(pre3!= null && pre3.getRight()==null){
            pre3.setRight(node);
            pre3.setRightType(1);
        }
        pre3 = node;
    }





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




//节点类
//需要添加左右结点的类型判定属性
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //为了后序遍历线索化二叉树
    private HeroNode parent;

    //规定:如果 type==0, 指向左右子节点, type==1 指向前驱后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name){
        this.no = no;
        this.name = name;
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

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
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