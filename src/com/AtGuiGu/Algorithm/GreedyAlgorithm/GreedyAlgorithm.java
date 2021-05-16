package com.AtGuiGu.Algorithm.GreedyAlgorithm;

import java.util.HashMap;
import java.util.HashSet;

/*
 * @Description: 贪心算法
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/31 17:15
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //HashMap key->电台 value->电台覆盖的区域
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String,HashSet<String>>();

        //创建电台对应的覆盖区域的HashSet
        HashSet<String> hashset1 = new HashSet<>();
        HashSet<String> hashset2 = new HashSet<>();
        HashSet<String> hashset3 = new HashSet<>();
        HashSet<String> hashset4 = new HashSet<>();
        HashSet<String> hashset5 = new HashSet<>();

        //向对应电台添加覆盖区域
        //k1
        //"北京", "上海", "天津"
        hashset1.add("北京");
        hashset1.add("上海");
        hashset1.add("天津");
        //k2
        //"广州", "北京", "深圳"
        hashset2.add("广州");
        hashset2.add("北京");
        hashset2.add("深圳");
        hashset2.add("大连");
        //k3
        //"成都", "上海", "杭州"
        hashset3.add("成都");
        hashset3.add("上海");
        hashset3.add("杭州");
        //k4
        //"上海", "天津"
        hashset4.add("上海");
        hashset4.add("天津");
//        hashset4.add("大连");
        //k5
        //"杭州", "大连"
        hashset5.add("杭州");
        hashset5.add("大连");

        //向HashMap中添加 对应电台和电台覆盖区域
        broadcasts.put("k1",hashset1);
        broadcasts.put("k2",hashset2);
        broadcasts.put("k3",hashset3);
        broadcasts.put("k4",hashset4);
        broadcasts.put("k5",hashset5);

        //创建集合保存所有电台可能覆盖的区域
        HashSet<String> allAreas = new HashSet<>();
        //遍历所有的电台覆盖的区域的hashset集合,依次添加到所有区域的hashset集合中,hashset自动去重,
        //最后得到的hashset就是去重的所有区域的集合
        for(String key:broadcasts.keySet()){
            allAreas.addAll(broadcasts.get(key));
        }
        System.out.println("allAreas = "+allAreas);

        //创建保存每一轮选择的电台的集合
        HashSet<String> select = new HashSet<>();

        //创建一个 保存每一轮比较 电台覆盖区域与未覆盖区域交集最大的 字符串保存该电台
        String maxKey = null;

        //创建一个集合保存每一轮遍历时取出的电台覆盖区域的集合
        HashSet<String> tempSet = new HashSet<>();

        //定义一个变量保存当前轮次,maxKey与allAreas交集的大小
        int maxSize = 0;

        //定义变量,记录遍历了几轮
        int count = 0;
        //当allAreas集合不为空就继续循环
        while(!allAreas.isEmpty()){
            count++;
            //新一轮需要将maxKey清空
            maxKey = null;

            //遍历每个电台
            for(String key:broadcasts.keySet()){
                //遍历下一个电台时,需要先将tempSet重置来保存新取出的覆盖区域集合
                tempSet.clear();
                //定义临时集合保存取出的电台覆盖区域集合
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //将取出的电台覆盖区域与所有区域取交集
                //retainAll: 将tempSet与allAreas取交集,并将交集重新赋给tempSet
                tempSet.retainAll(allAreas);

                //判断当前的tempSet与当前maxKey相比,与allAreas取交集的大小,如果更大,则将maxKey替换为tempSet,否则不变
                //获取当前最大电台与allAreas交集的大小
                if(maxKey!=null){
                    HashSet<String> maxSet = broadcasts.get(maxKey);
                    maxSet.retainAll(allAreas);
                    maxSize = maxSet.size();
                }
                //判断tempSet与当前最大电台与allAreas交集的大小
                if(tempSet.size()>0 && (maxKey==null || tempSet.size()>maxSize)){
                    maxKey = key;
                }
            }
            //遍历完得到与allAreas最大交集的电台
            if(maxKey!=null){
                //将最大电台添加到选择的集合
                select.add(maxKey);
                //将本轮最大电台覆盖的区域从allAreas中删除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
            //打印当前轮次所做工作信息
            System.out.println("=======");
            System.out.printf("第%d轮: 选择%s电台->%s\n剩余未覆盖区域: %s\n",count,maxKey,broadcasts.get(maxKey),allAreas);
        }
        //退出循环,当前allAreas为空,所有区域已经被取出的电台覆盖
        System.out.println("=======");
        System.out.println(select);
    }
}
