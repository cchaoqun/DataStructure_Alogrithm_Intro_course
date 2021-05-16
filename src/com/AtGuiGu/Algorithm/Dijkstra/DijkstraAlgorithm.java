package com.AtGuiGu.Algorithm.Dijkstra;

import java.util.Arrays;

/*
 * @Description: 迪杰斯特拉算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2021/1/4 9:25
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        //顶点数组
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        //N表示两个顶点不连通
        final int N = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex,matrix);
        graph.showGraph();

        graph.dijkstra(2);
        graph.showDijkstra();
    }
}

//图类
class Graph{
    //顶点数组
    private char[] vertex;
    //图的邻接矩阵
    private int[][] matrix;
    //已访问结点类
    private VisitedVertex vv;

    //构造器
    public Graph(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图的邻接矩阵
    public void showGraph(){
        for(int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法
    public void dijkstra(int index){
        vv = new VisitedVertex(vertex.length,index);
        //更新index结点到周围结点的距离以及周围结点的前驱结点
        update(index);
        for(int i=1; i<matrix[index].length; i++){
            //获取新的访问结点
            index = vv.updateArr();
            //更新index结点到周围结点的距离以及周围结点的前驱结点
            update(index);
        }
    }

    //更新当前结点到周围结点的距离以及周围结点的前驱结点
    public void update(int index){
        int len = 0;
        for(int i=0; i<matrix[index].length; i++){
            //len表示当前结点到index结点的距离 + index结点到i结点的距离之和
            len = vv.getDis(index) + matrix[index][i];
            //如果i结点未被访问,且当前结点->index->i的距离小于其他路径的距离,则更新距离及前驱结点
            if(!vv.in(i) && len<vv.getDis(i)){
                //更新当前结点到i结点的距离
                vv.updateDis(i,len);
                //更新i结点的前驱结点
                vv.updatePre(i,index);
            }
        }
    }

    //显示结果
    public void showDijkstra(){
        vv.show();
    }
}

//已经访问过的结点类
class VisitedVertex{
    //表示结点是否访问过得数组
    private int[] already_arr;
    //表示下标对应结点的前驱结点
    private int[] pre_arr;
    //表示当前结点到其他各个顶点的距离,与自己的距离为0 与不直接相连的结点距离为65535
    private int[] dis;

    //构造方法
    /*
     * @param length 顶点的数量
     * @param index 起始顶点下标
     */
    public VisitedVertex(int length, int index){
        this.already_arr = new int[length];
        this.pre_arr = new int[length];
        this.dis = new int[length];
        //初始化当前顶点与其他顶点距离都为65535
        Arrays.fill(dis,65535);
        //与自己的距离为0
        this.dis[index] = 0;
        //设置当前结点已访问
        already_arr[index] = 1;
    }

    //判断index顶点是否被访问过
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    //更新出发顶点到index顶点的距离len
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    //更新index结点的前驱结点为pre结点
    public void updatePre(int index, int pre){
        pre_arr[index] = pre;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }

    //继续选择并访问新的访问结点,这里选择的是权值最小的新的访问结点
    public int updateArr(){
        int min = 65535, index = 0;
        for(int i=0; i<already_arr.length; i++){
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //设置新的访问结点已经访问
        already_arr[index] = 1;
        return index;
    }

    //显示三个数组
    public void show(){
        System.out.println("==============");
        for(int i : already_arr){
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("==============");
        for(int i : pre_arr){
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("==============");
        for(int i : dis){
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("==============");
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for(int i: dis){
            if(i!=65535){
                System.out.print(vertex[count]+"("+i+")");
            }else{
                System.out.print("N ");
            }
            count++;
        }
        System.out.println();
    }

}