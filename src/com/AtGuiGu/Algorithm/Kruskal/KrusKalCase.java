package com.AtGuiGu.Algorithm.Kruskal;

import java.util.Arrays;

/*
 * @Description: 克鲁斯卡尔算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2021/1/3 21:08
 */
public class KrusKalCase{
    //顶点数组
    private char[] vertexs;
    //边的数量
    private int edgeNums;
    //邻接矩阵
    private int[][] matrix;
    //用Integer类型的最大值表示两个顶点不连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        KrusKalCase kruskal = new KrusKalCase(vertexs, matrix);
        kruskal.print();

        //获取边的数组
        EData[] edges = kruskal.getEdges();
        System.out.println("排序前边的数组:");
        System.out.println(Arrays.toString(edges));
        //排序后的数组
        kruskal.sortEdges(edges);
        System.out.println("排序后边的数组:");
        System.out.println(Arrays.toString(edges));

        //kruskal
        System.out.println("最小生成树:");
        kruskal.kruskal();

    }

    //Kruskal
    public void kruskal(){
        //记录最小生成树中边的数量
        int index = 0;
        //记录每个顶点的终点的数组
        int[] ends = new int[edgeNums];
        //获取图中边的数组
        EData[] edges = getEdges();
        //最终最小生成树的边的数组
        EData[] res = new EData[edgeNums];
        //对边按照权重进行排序
        sortEdges(edges);

        for(int i=0; i<edgeNums; i++){
            //获取边的第一个顶点
            int v1 = getPosition(edges[i].start);
            //获取边的第二个顶点
            int v2 = getPosition(edges[i].end);

            //获取还未加入该边时,两个顶点在最小生成树中的终点
            int m = getEnd(ends,v1);
            int n = getEnd(ends,v2);

            if(m!=n){
                //如果边的两个顶点在最小生成树中的终点不相等.
                //将该边的起点在最小生成树中的终点设置成这条边的终点,
                //该边的终点无需设置
                ends[m] = n;
                res[index++] = edges[i];
            }
        }
        for(int i=0; i<index; i++){
            System.out.println(res[i]);
        }
    }





    //构造器
    public KrusKalCase(char[] vertexs, int[][] matrix){
        //初始化顶点数
        int vertexLen = vertexs.length;

        //初始化顶点数组
        this.vertexs = new char[vertexLen];
        for(int i=0; i<vertexLen; i++){
            this.vertexs[i] = vertexs[i];
        }

        //初始化邻接矩阵
        this.matrix = new int[vertexLen][vertexLen];
        for(int i=0; i<vertexLen; i++) {
            for (int j = 0; j < vertexLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //初始化边的数量
        for(int i=0; i<vertexLen; i++){
            //j=i+1 每个顶点不和自己比较
            for(int j=i+1; j<vertexLen; j++){
                if(matrix[i][j] != INF){
                    edgeNums++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print(){
        for(int i=0; i< vertexs.length; i++){
            for(int j=0; j<vertexs.length; j++){
                System.out.printf("%12d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //根据边的权重进行排序
    //利用冒泡排序
    private void sortEdges(EData[] edges){
        for(int i=0; i<edges.length-1; i++){
            for(int j=0; j<edges.length-1-i; j++){
                if(edges[j].weight > edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    //根据顶点数据获取下标
    //如果不存在,return -1
    private int getPosition(char vertex){
        for(int i=0; i<vertexs.length; i++){
            if(vertexs[i] == vertex){
                return i;
            }
        }
        return -1;
    }

    //获取边
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNums];
        for(int i=0; i< vertexs.length; i++){
            for(int j=i+1; j<vertexs.length; j++){
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /*
     * @Description: 获取下标为i的顶点的终点下标的数组,用于后面判断两个顶点的终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是那个, end数组是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标,
     */
    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

//图中边的类
class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start + "," + end +"> = " + weight;
    }
}
