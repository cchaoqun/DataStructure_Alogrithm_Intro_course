package com.AtGuiGu.Algorithm.Prim;

import java.util.Arrays;

/*
 * @Description: Prim 算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2021/1/3 19:01
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //顶点数据
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        //顶点个数
        int vertexNum = data.length;
        //邻接矩阵边的数据 10000表示两个顶点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};
        //创建图对象
        MGraph graph = new MGraph(vertexNum);
        //创建最小生成树
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertexNum,data,weight);
        //图的邻接矩阵
        minTree.showGraph(graph);

        //普利算法
        minTree.prim(graph,1);
    }
}

//创建最小生成树
class MinTree{
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int vertexNum, char[] data, int[][] weight){
        int i, j;
        for(i=0; i<vertexNum; i++){
            //顶点数据
            graph.data[i] = data[i];
            for(j=0; j<vertexNum; j++){
                //边的数据
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for(int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //Pirm算法
    //vertex表示开始的顶点下标
    public void prim(MGraph graph, int vertex){
        //判断是否被访问过的数组
        //默认值为0表示未被访问过,1表示被访问过
        int[] visited = new int[graph.vertexNum];

        //将当前顶点设置为已访问
        visited[vertex] = 1;

        //定义变量保存遍历子图时找到的最小路径的值
        int minWeight = 10000;

        //定义两个变量来存储每次子图的最短路径的两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        //vertexNum个顶点的最小生成树有 vertexNum-1条边, 需要遍历vertexNum-1次
        for(int k=1; k<graph.vertexNum; k++){
            //遍历顶点数组的每个顶点,找到被访问过得顶点
            for(int i=0; i<graph.vertexNum; i++){
                //遍历顶点数组的每个顶点,找到未被访问过得顶点
                for(int j=0; j<graph.vertexNum; j++){
                    if(visited[i]==1 && visited[j]==0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        //保存最小路径的两个下标
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //退出循环,找到当前轮次子图的最小路径
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">"+"路径长度: "+minWeight);
            //重置minWeight
            minWeight = 10000;
            //将最小路径的未访问的顶点设置为已经访问
            visited[h2] = 1;
        }
    }
}



//创建图对象
class MGraph{
    //表示图中顶点的个数
    int vertexNum;
    //表示顶点的数据
    char[] data;
    //表示边
    int[][] weight;

    public MGraph(int vertexNum){
        this.vertexNum = vertexNum;
        data = new char[vertexNum];
        weight = new int[vertexNum][vertexNum];
    }
}