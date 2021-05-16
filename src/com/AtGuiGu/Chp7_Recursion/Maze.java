package com.AtGuiGu.Chp7_Recursion;
/*
 * @Description: 数组实现小球寻找最短路径
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/11 10:18
 */
public class Maze {
    public static void main(String[] args) {
        int[][] maze = createMaze(15,20);

        //展示初始迷宫
        System.out.println("初始迷宫: ");
        showMaze(maze);

        //寻找路径
        findWay(maze, 1,1);
        System.out.println("最短路径: ");
        showMaze(maze);


    }

    /*
     * @Description:
     * 说明
     * 1.maze表示查找路径的迷宫数组
     * 2.i,j表示从哪个位置出发的坐标(i,j)
     * 3.出口在maze[6][5],达到这个位置即成功
     * 4.规定 当maze[i][j]=0 表示该位置没走过, 1表示为墙不能走, 2表示通路可以走, 3表示是死路走不通
     * 5.走迷宫的方法时 按照 下->右->上->左的顺序依次尝试
     *
     *
     *
     * @param 迷宫地图数组
     * @param 起始位置行列数
     * @return true 找到路径走出迷宫 false未找到路径
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/11 10:29
     */
    public static boolean findWay(int[][] maze, int i, int j) {
        //先判断是否到达出口 出口是数组的右下角
        int o1 = maze.length-2;
        int o2 = maze[0].length-2;
        if(maze[o1][o2] == 2){
            return true;
        }else if(maze[i][j] == 0){//当前位置未走过
            //假定当前位置可以走通,设为2
            maze[i][j] = 2;
            //按照方法依次尝试 下->右->上->左
            if(findWay(maze,i+1, j)){//向下
                return true;
            }else if(findWay(maze,i, j+1)){//向右
                return true;
            }else if(findWay(maze,i, j-1)){//向上
                return true;
            }else if(findWay(maze,i-1, j)){//向左
                return true;
            }else{
                //该点的上下左右都走不通,假设不成立设为3
                maze[i][j] = 3;
                return false;
            }
        }else{
            //当前路可能是 1 2 3
            return false;
        }
    }

    //创建指定大小的迷宫
    //m代表行数 n代表列数
    public static int[][] createMaze(int m, int n){
        //创建迷宫
        int[][] maze = new int[m][n];

        //设置上下为墙
        for(int i=0; i<n; i++){
            maze[0][i] = 1;
            maze[m-1][i] = 1;
        }
        //设置左右为墙
        for(int i=1; i<m-1; i++){
            maze[i][0] = 1;
            maze[i][n-1] = 1;
        }
        //设置挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[2][2] = 1;
        maze[2][3] = 1;
        maze[3][3] = 1;
        maze[5][2] = 1;
        maze[5][3] = 1;
        maze[5][4] = 1;

        return maze;
    }

    //展示迷宫的方法
    public static void showMaze(int[][] maze){
        for(int[] mazeRow: maze){
            for(int mazeItem: mazeRow){
                System.out.printf("%d\t",mazeItem);
            }
            //每打印完一行换行
            System.out.println();
        }
    }
}
