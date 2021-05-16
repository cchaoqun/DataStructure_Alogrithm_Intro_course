package com.AtGuiGu.Algorithm.Floyd;

import java.util.Arrays;

/*
 * @Description: 弗洛伊德算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2021/1/4 13:35
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        //大值表示两个顶点不连通
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertex.length, vertex, matrix);
        graph.floyd();
        graph.show();
    }
}


class Graph{
    //顶点数组
    private char[] vertex;
    //存放从各个顶点出发到其他顶点距离的矩阵,最后的结果也是保存在这个数组
    private int[][] dis;
    //保存到达目标顶点的前驱结点,保存的是前驱结点下标
    private int[][] pre;


    public Graph(int length, char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.dis = matrix;
        pre = new int[length][length];
        for(int i=0; i<length; i++){
            Arrays.fill(pre[i],i);
        }
    }

    //floyd算法
    public void floyd(){
        //保存经过中间结点的两点间的距离
        int len = 0;
        //经过k中间顶点,对k中间顶点的遍历, k是中间顶点的下标
        for(int k=0; k<dis.length; k++){
            //从i顶点出发
            for(int i=0; i<dis.length; i++){
                //到达j顶点
                for(int j=0; j<dis.length; j++){
                    //代表i->k的距离 + k->j的距离
                    len = dis[i][k]+dis[k][j];
                    //如果经过中间顶点k的i->j的距离 小于 i直接到j的距离
                    if(len<dis[i][j]){
                        //更新最短距离矩阵
                        dis[i][j] = len;
                        //更新终点j的前驱结点为A
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }


    public void show(){
        for(int i=0; i< dis.length; i++){
            //打印pre
            for(int j=0; j<dis.length; j++){
                System.out.print(vertex[pre[i][j]]+" ");
            }
            System.out.println();
            //打印dis
            for(int j=0; j<dis.length; j++){
                System.out.print("("+vertex[i]+"到"+vertex[j]+"的距离:"+dis[i][j]+")");
            }
            System.out.println();
        }
    }
}
