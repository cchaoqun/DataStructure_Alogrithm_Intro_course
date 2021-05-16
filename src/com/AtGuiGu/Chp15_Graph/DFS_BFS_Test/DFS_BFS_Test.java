package com.AtGuiGu.Chp15_Graph.DFS_BFS_Test;


import java.util.ArrayList;
import java.util.LinkedList;

/*
 * @Description: DFS BFS练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 11:31
 */
public class DFS_BFS_Test {
    private ArrayList<String> vertexs;
    private int[][] edges;
    private int edgeNums;
    private boolean[] isVisited;

    public static void main(String[] args) {
        //创建顶点数组
//        String[] vertexs = {"A","B","C","D","E"};
        String[] vertexs1 = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        DFS_BFS_Test graph = new DFS_BFS_Test(vertexs1.length);
        //添加顶点
        for(String vertex:vertexs1){
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

    public DFS_BFS_Test(int n){
        vertexs = new ArrayList<>(n);
        edges = new int[n][n];
        edgeNums = 0;

    }

    public void insertVertex(String vertex){
        vertexs.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNums++;
    }

    public int getVertexsNums(){
        return vertexs.size();
    }

    public int getEdgeNums(){
        return edgeNums;
    }

    public String getVertexByIndex(int i){
        return vertexs.get(i);
    }

    public int getFirstNeighbor(int index){
        for(int i=0; i<getVertexsNums(); i++){
            if(edges[index][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int index, int first){
        for(int i=first+1; i<getVertexsNums(); i++){
            if(edges[index][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public void dfs(boolean[] isVisited, int index){
        System.out.print(vertexs.get(index)+"->");
        isVisited[index] = true;
        int first = getFirstNeighbor(index);
        while(first!=-1){
            if(!isVisited[first]){
                dfs(isVisited,first);
            }
            first = getNextNeighbor(index, first);
        }
    }

    public void dfs(){
        isVisited = new boolean[getVertexsNums()];
        for (int i=0; i<getVertexsNums(); i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }

        }
    }

    public void bfs(boolean[] isVisited, int index){
        int head;
        int next;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        System.out.print(vertexs.get(index)+"->");
        isVisited[index] = true;
        queue.addLast(index);
        while(!queue.isEmpty()){
            head = queue.removeFirst();
            next = getFirstNeighbor(head);
            while(next!=-1){
                if(!isVisited[next]){
                    System.out.print(vertexs.get(next)+"->");
                    isVisited[next] = true;
                    queue.addLast(next);
                }
                next = getNextNeighbor(head,next);
            }
        }
    }

    public void bfs(){
        boolean[] isVisited = new boolean[getVertexsNums()];
        for(int i=0; i<getVertexsNums(); i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }

        }

    }

    public void showGraph(){
        for(int[] edgeRow:edges){
            for(int edgeColunme:edgeRow){
                System.out.print(edgeColunme+" ");
            }
            System.out.println();
        }
    }
}
