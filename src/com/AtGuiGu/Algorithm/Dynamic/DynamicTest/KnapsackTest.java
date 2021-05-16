package com.AtGuiGu.Algorithm.Dynamic.DynamicTest;
/*
 * @Description: 动态规划背包问题练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 13:57
 */
public class KnapsackTest {
    public static void main(String[] args) {
        int[] weight = {1,4,3};
        int[] value = {1500, 3000, 2000};
        int n = weight.length;
        int m = 4;

        int[][] v = new int[n+1][m+1];
        int[][] info = new int[n+1][m+1];

        for(int i=0; i<v.length; i++){
            v[i][0] = 0;
        }
        for(int i=0; i<v[0].length; i++){
            v[0][i] = 0;
        }

        for(int i=1; i<v.length; i++){
            for(int j=1; j<v[0].length; j++){
                if(weight[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    if(v[i-1][j]>=value[i-1]+v[i-1][j-weight[i-1]]){
                        v[i][j] = v[i-1][j];
                    }else{
                        v[i][j] = value[i-1]+v[i-1][j-weight[i-1]];
                        info[i][j] = 1;
                    }
                }
            }
        }
        System.out.println("最大背包价值表");
        for(int i=0; i<v.length; i++){
            for(int j=0; j<v[0].length; j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        int row = info.length-1;
        int column = info[0].length-1;
        while(row>0 && column>0){
            if(info[row][column] == 1){
                System.out.printf("第%d件物品放入背包\n",row);
                column = column - weight[row-1];
            }
            row--;
        }
    }
}
