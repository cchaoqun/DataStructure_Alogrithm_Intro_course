package com.AtGuiGu.Chp7_Recursion;
/*
 * @Description: 递归解决8皇后问题
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/11 12:21
 */
public class Quenns8 {
    //放置皇后的个数
    int max = 8;
    //创建一维数组存放每个皇后存放的位置
    //数组元素下标i代表第i+1个皇后, 以及该皇后放置的行数(0-7)
    //数组元素值val代表存放的列数(0-7)
    int[] posArr = new int[max];
    //统计一个有多少种解法
    static int solutionCount = 0;
    //统计一个回溯了多少次
    static int judgeCount = 0;

    public static void main(String[] args) {
        Quenns8 quenns8 = new Quenns8();
        quenns8.check(0);
        System.out.printf("一共有%s种解法",solutionCount);
        System.out.printf("一共回溯了%s次",judgeCount);

    }

    /*
     * @Description: 放置第n个皇后
     *
     * @param n 第n个皇后
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/11 12:48
     */
    private void check(int n){
        //判断完成的条件
        if(n==8){
            //n从0开始,n=8代表当前放置的为第9个皇后,前面8个已经完成,打印解法并结束当前方法
            showSolution();
            return;
        }
        //从当前行的0列开始尝试放置皇后n
        for (int i = 0; i < max; i++) {
            //放置皇后在i列
            posArr[n] = i;
            //判断是否与之前皇后冲突
            if(judge(n)){
                //不冲突,开始放置下一个皇后 n+1
                check(n+1);
            }
            //冲突,则尝试放入当前行的下一列
        }
    }



    /*
     * @Description: 判断当前皇后放置后,是否与之前的皇后冲突 (1.不能放在同一行同一列, 2.不能再对角线)
     * 该步判断在放置皇后的操作之后
     * @param n 放置的第n个皇后
     * @return boolean true 当前位置与之前放置的皇后不冲突
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/11 12:22
     */
    private boolean judge(int n){
        //判断一次
        judgeCount++;
        //判断当前皇后位置是否与之前的皇后冲突
        for (int i = 0; i < n; i++) {
            //判断该皇后位置与之前皇后是否冲突
            //冲突的判断条件
            // 1.不能放在同一行同一列, posArr[n] != posArr[i]
            // 2.不能再对角线) Math.abs(n-i) != Math.abs(posArr[n]-posArr[i]
            if(posArr[n] == posArr[i] || Math.abs(n-i) == Math.abs(posArr[n]-posArr[i])){
                //条件不成立,冲突
                return false;
            }
        }
        //判断完当前皇后以前所有的皇后冲突情况 未发现冲突情况, 当前皇后OK
        return true;
    }

    private void showSolution(){
        //得到一种解法
        solutionCount++;
        for (int i = 0; i < max; i++) {
            System.out.printf("%s ", posArr[i]);
        }
        //打印完一个解法后换行
        System.out.println();
    }
}
