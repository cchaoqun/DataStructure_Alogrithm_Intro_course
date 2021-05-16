package com.AtGuiGu.Chp15_Graph;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * @Description: 图
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 10:56
 */
public class GraphDemo {
    //集合保存顶点
    public ArrayList<String> vertexs;
    //边数量
    private int edgeNums;
    //邻接矩阵
    private int[][] edges;
    //判断结点是否被访问
    private boolean[] isVisited;


    public static void main(String[] args) {
        //创建顶点数组
//        String[] vertexs = {"A","B","C","D","E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        GraphDemo graph = new GraphDemo(vertexs.length);
        //添加顶点
        for(String vertex:vertexs){
            graph.vertexs.add(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //遍历图
        System.out.println("图的遍历");
        graph.showGraph();

        //图的深度优先遍历 dfs
        System.out.println("图的深度优先遍历");
        graph.dfs();
        System.out.println();

        //图的广度优先遍历 bfs
        System.out.println("图的广度优先遍历");
        graph.bfs();


    }

    //构造方法
    public GraphDemo(int n){
        //利用传入的顶点数量初始化图
        edges = new int[n][n];
        edgeNums = 0;
        vertexs = new ArrayList<String>(n);

    }

    //插入节点的方法
    public void insertVertex(String vertex){
        //把顶点添加到顶点集合
        vertexs.add(vertex);
    }

    //插入边的方法
    /*
     * @param v1 边的第一个顶点
     * @param v2 边的第二个顶点
     * @param weight 两顶点相连在邻接矩阵中的数字表示 1
     */
    public void insertEdge(int v1, int v2, int weight){
        //两个方向都需要赋值为1因为为无向图
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        //边的数量+1
        edgeNums++;
    }

    //返回节点数量的方法
    public int getVertexsNums(){
        return vertexs.size();
    }

    //返回边的数量的方法
    public int getEdgeNums(){
        return edgeNums;
    }

    //返回下标对应的顶点数据
    public String getVertexByIndex(int index){
        return vertexs.get(index);
    }

    //图的遍历
    public void showGraph(){
        for(int[] vertexsRow:edges){
            for(int edge:vertexsRow){
                System.out.printf("%d\t",edge);
            }
            System.out.println();
        }
    }

    //返回当前顶点的第一个邻接节点的下标
    public int getFirstNeighbor(boolean[] isVisited, int index){
        //从顶点index这一行从头开始遍历找到第一个邻接节点
        for(int i=0; i<getVertexsNums(); i++){
            //如果edge=1,表示两个顶点链接
            if(edges[index][i] == 1){
                //返回第一个邻接节点的下标
                return i;
            }
        }
        //遍历完未找到,则返回-1
        return -1;
    }

    //返回当前顶点的下一个邻接节点的下标
    public int getNextNeighbor(int v1, int v2){
        //从当前顶点的第一个邻接节点后面一个开始遍历
        for(int j=v2+1; j<getVertexsNums(); j++){
            if(edges[v1][j] == 1){
                //返回下一个邻接节点的下标
                return j;
            }
        }
        //未找到
        return -1;
    }

    //深度优先遍历 dfs
    /*
     * @param isVisited 判断结点是否被访问过
     * @param index 当前访问的结点
     */
    public void dfs(boolean[] isVisited, int index){
        //访问初始顶点, 输出
        System.out.print(getVertexByIndex(index)+"->");

        //将当前访问顶点设置成已访问
        isVisited[index] = true;
        //查找当前结点的第一个邻接节点
        int v1 = getFirstNeighbor(isVisited,index);
        //判断第一个邻接节点是否存在
        while(v1!=-1){//第一个邻接节点存在
            //判断邻接节点是否被访问
            if(!isVisited[v1]){//未被访问过
                //对v1进行递归dfs
                dfs(isVisited,v1);
            }
            //如果第一个邻接节点被访问过,查找index的下一个邻接节点
            v1 = getNextNeighbor(index,v1);
        }
        //退出循环说明第一个邻接节点不存在

    }

    //深度优先遍历的重构
    public void dfs(){
        isVisited = new boolean[getVertexsNums()];
        for(int i=0; i<getVertexsNums(); i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //广度优先遍历 bfs
    public void bfs(boolean[] isVisited, int index){
        //当前访问的顶点的下标,也是队列的第一个元素
        int head;
        //当前顶点的邻接节点
        int next;
        //访问当前顶点 输出
        System.out.print(getVertexByIndex(index)+"->");
        //当前顶点标记为已经访问
        isVisited[index] = true;
        //创建队列存储队列入列的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //将当前访问元素入队列
        queue.addLast(index);

        //队列非空,
        while(!queue.isEmpty()){
            //取出队列头结点
            head = queue.removeFirst();
            //查找头结点的第一个邻接节点
            next = getFirstNeighbor(isVisited,head);
            //邻接节点存在
            while(next!=-1){
                //判断是否被访问过
                if(!isVisited[next]){
                    //输出该结点
                    System.out.print(getVertexByIndex(next)+"->");
                    //标记成已访问
                    isVisited[next] = true;
                    //入队列
                    queue.addLast(next);
                }
                //如果被访问过,继续寻找下一个邻接节点
                next = getNextNeighbor(head,next);
            }
        }
    }

    //广度优先的重构
    public void bfs(){
        boolean[] isVisited = new boolean[getVertexsNums()];
        for(int i=0; i<getVertexsNums(); i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

}
