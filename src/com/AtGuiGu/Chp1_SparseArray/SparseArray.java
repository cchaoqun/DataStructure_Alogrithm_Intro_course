package com.AtGuiGu.Chp1_SparseArray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

/**
 * @Description:  
 *1) 使用稀疏数组，来保留类似前面的二维数组(棋盘、地图等等)
 *2) 把稀疏数组存盘，并且可以从新恢复原来的二维数组数
 *
 *
 * 
 * @Author: CCQ
 * @Date: 2020/12/7
 */

public class SparseArray {
    public static void main(String[] args) throws Exception{
        //------------原始数组转变成稀疏数组------------------
        System.out.println();
        //1.创建二维数组
        int[][] chessArr1 = new int[11][11];
        //2.给对应位置赋值代表不同棋子颜色 1代表白色 2代表黑色
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        chessArr1[2][2] = 1;
        //3.打印数组查看效果
        System.out.println("输出原始二维数组");
        for(int[] row: chessArr1){
            for(int element: row){
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }
        //4.寻找sum值
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum的值为："+sum);

        //5.创建稀疏数组,并为第一行赋值
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //6.遍历原始数组，并将非零数值赋值给稀疏数组
        //记录遍历的到的是第几个非零数，并赋值给稀疏数组的第count行
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //7.打印得到的稀疏数组
        System.out.println("得到稀疏数组：");
        //7.1打印数组方式1
//        for(int[] row: sparseArr){
//            for (int element:row){
//                System.out.printf("%d\t",element);
//            }
//            System.out.println();
//        }
        //7.2打印数组方式2
        for(int i=0; i< sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //8.将稀疏数组保存到properties中
        Properties properties  = new Properties();
        FileOutputStream fos = new FileOutputStream("D:\\Data_Structure_Algorithm_AtGuiGu\\sparseArray.properties");
        //8.1获取稀疏数组中的非零元素，并按照数组的格式存储在properties中
        for (int i = 0; i < sparseArr.length; i++) {
            String key =String.valueOf(i);
            //[11,11,3]
            String value = "["+sparseArr[i][0]+","+sparseArr[i][1]+","+sparseArr[i][2]+"]";
            properties.setProperty(key,value);
        }
        properties.save(fos,"SparseArray");

        //------------稀疏数组转变成原始数组------------------
        //从properties中导出稀疏数组
        Properties properties2  = new Properties();
        FileInputStream fis = new FileInputStream("D:\\Data_Structure_Algorithm_AtGuiGu\\sparseArray.properties");
        properties2.load(fis);
        //获取key的数量
        Set<Object> keyset =  properties2.keySet();
        int len = keyset.size();
        //[11,11,3]
        //获取存储在properties中的原始数组的行列数 key=0 的value
        String firstrow = (String)properties2.get("0");
        String[] firstrow1 = firstrow.substring(1,firstrow.length()-1).split(",");

        //1.获取稀疏数组第一列元素，创建原始数组
        int r0, c0;
        //字符创转整数类型两种方法  Integer.parseInt  Integer.valueOf().intValue()
        r0 = Integer.parseInt(firstrow1[0]);
        c0 = Integer.valueOf(firstrow1[1]).intValue();
        int[][] chessArr2 = new int[r0][c0];
        //2.获取稀疏数组后续行元素信息，并赋值给新的的数组
        String sparseRow;
        String[] sparseRow1;
        int r,c,v;
        for (int i = 1; i < len; i++) {
            //整数转换成字符串
            //1.  +
//            String key = i+"";
            //2. String.valueOf()
//            String key = String.valueOf(i);
            //3. Integer.toString()
            String key = Integer.toString(i);
            sparseRow = (String)properties2.get(key);
            sparseRow1 = sparseRow.substring(1,sparseRow.length()-1).split(",");
            r = Integer.parseInt(sparseRow1[0]);
            c = Integer.parseInt(sparseRow1[1]);
            v = Integer.parseInt(sparseRow1[2]);
            chessArr2[r][c] = v;
        }
        //1.获取稀疏数组第一列元素，创建原始数组
//        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.获取稀疏数组后续行元素信息，并赋值给新的的数组
//        for (int i = 1; i < sparseArr.length; i++) {
//            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
//        }

        //3.打印输出的原始数组
        System.out.println("从稀疏数组转变成原始数组:");
//        for (int i = 0; i < chessArr2.length; i++) {
//            for (int j = 0; j < chessArr2[0].length; j++) {
//                System.out.printf("%d\t",chessArr2[i][j]);
//            }
//            System.out.println();
//        }
        for(int[] row: chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        
        /** 
         * @Description:  
         *要求：
         * 1) 在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
         * 2) 恢复原来的数组时，读取 map.data 进行恢复
         *
         *
         * 
         * @Author: CCQ
         * @Date: 2020/12/7
         */
    }
}
