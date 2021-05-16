package com.AtGuiGu.Algorithm.Dynamic;
/*
 * @Description: 动态规划
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/30 19:08
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的种类
        int[] weights = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};
        //物品的数量
        int n = val.length;
        //背包的重量
        int m = 4;

        //二维数组
        //v[i][j] 表示将i个物品装进重量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];

        //记录背包商品存放信息
        int[][] container = new int[n+1][m+1];

        //将二维数组的第一行和第二行赋值为0
        for(int i = 0; i<v.length; i++){
            v[i][0] = 0;
        }
        for(int i = 0; i<v[0].length; i++){
            v[0][i] = 0;
        }

        //根据公式动态规划处理
        for(int i=1; i<v.length; i++){//不处理第一行
            for(int j=1; j<v[0].length; j++){//不处理第一列
                //物品重量的数组要从i-1开始,因为二维数组里,第一个物品从第二行开始
                if(weights[i-1] > j){
                    //准备新增的物品重量大于当前背包容量,直接使用上一个单元格的装入策略
                    v[i][j] = v[i-1][j];
                }else{
                    //准备新增的物品重量小于当前背包容量
                    //v[i-1][j]:上一个单元格装入的值
                    //val[i-1]: 当前物品的价值
                    //v[i-1][j-weights[i-1]]: 尝试装入当前物品后,向剩余重量空间装入 i-1商品的最大价值
                    //物品价值的数组要从i-1开始,因为二维数组里,第一个物品从第二行开始
                    //v[i][j] = Math.max(v[i-1][j], val[i-1]+v[i-1][j-weights[i-1]]);
                    //为了记录商品的存放信息,需要使用if-else选择结构
                    if(v[i-1][j] >= val[i-1]+v[i-1][j-weights[i-1]]){
                        v[i][j] = v[i-1][j];
                    }else{
                        v[i][j] = val[i-1] + v[i-1][j-weights[i-1]];
                        //当前物品添加到了背包中,标记
                        container[i][j] = 1;
                    }
                }
            }
        }

        //遍历二维数组
        for(int i = 0; i<v.length; i++){
            for(int j=0; j<v[0].length; j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        //输出最大值情况下背包存放物品的信息
        System.out.println("====背包存放物品信息====");
        int row = container.length - 1;//行的最大下标
        int column = container[0].length - 1;//列的最大下标
        while(row>0 && column>0){//从container的最后开始找
            if(container[row][column] == 1){
                System.out.printf("第%d个商品放入背包\n",row);
                column = column - weights[row-1];
            }
            row--;
        }

    }
}
