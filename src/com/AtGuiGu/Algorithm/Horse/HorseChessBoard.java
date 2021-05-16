package com.AtGuiGu.Algorithm.Horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/*
 * @Description: 马踏棋盘算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2021/1/4 15:00
 */
public class HorseChessBoard {
    //棋盘的列数
    private static int X;
    //棋盘的行数
    private static int Y;
    //标记棋盘的位置是否被访问过
    private static boolean[] isVisited;
    //true表示成功
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法开始");
        X = 8;
        Y = 8;
        //马初始位置的行,从1开始
        int row = 1;
        //马初始未知的列,从1开始
        int column = 1;
        int[][] chessboard = new int[X][Y];
        isVisited = new boolean[X*Y];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("马踏棋盘耗时: "+(end-start)+"ms");

        for(int[] rows : chessboard){
            for(int step : rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }

    }

    /*
     * @Description:完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row 马儿当前位置的行 从0开始
     * @param column 马儿当前位置的列, 从0开始
     * @param step 马儿走的第几步,初始位置就是第一步
     */
    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        //标记当前位置已经访问过
        isVisited[row * X + column] = true;
        //获取当前位置下一步可以走的位置集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对保存下一步位置的集合进行排序
        sort(ps);
        //遍历ps
        while(!ps.isEmpty()){
            //获取集合的第一个下一步位置
            Point p1 = ps.remove(0);
            //判断该点是否被访问过
            if(!isVisited[p1.y*X+p1.x]){
                //没有被访问过则在该点递归
                traversalChessBoard(chessboard,p1.y,p1.x,step+1);
            }
        }
        //遍历完,判断是否完成任务
        //step和棋盘的数量相比,没有达到数量,则表示没有完成任务,将整个棋盘置位0
        //说明, step < X*Y 有两种情况
        //1. 棋盘到目前的位置,还没有走完
        //2. 棋盘已经走完,处在回溯的过程
        if(step<X*Y && !finished){
            chessboard[row][column] = 0;
            isVisited[row*X+column] = false;
        }else{
            finished = true;
        }
    }


    //根据当前位置 curPoint 判断马下一步还能去那些位置 (Point), 并放入一个集合中(ArrayList) 最多8个位置
    public static ArrayList<Point> next(Point curPoint){
        //创建ArrayList存放马下一步可能的位置
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point对象
        Point p1 = new Point();

        //判断下一步是否可以走5这个位置
        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走6这个位置
        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走7这个位置
        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走0这个位置
        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走1这个位置
        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走2这个位置
        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走3这个位置
        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //判断下一步是否可以走4这个位置
        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }

        return ps;
    }

    //对当前位置下一步的所有Point对象的下一步的位置的数组,进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2) {
                //获取两个Point对应的下一步的位置的集合的大小
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}
