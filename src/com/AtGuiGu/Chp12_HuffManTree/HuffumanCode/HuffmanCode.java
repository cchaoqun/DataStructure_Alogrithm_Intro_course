package com.AtGuiGu.Chp12_HuffManTree.HuffumanCode;

import java.io.*;
import java.util.*;

/*
 * @Description: 霍夫曼编码
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/28 11:53
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String contents = "i like like like java do you like a java";
        byte[] contentsBytes = contents.getBytes();
        System.out.println("原始数组: \n"+Arrays.toString(contentsBytes));
//        ArrayList<Node> nodes = getNodeList(contentsBytes);
//        System.out.println(nodes);
//        Node huffmanRoot = createHuffmanTree(nodes);
//        //霍夫曼树的前序遍历
//        System.out.println("霍夫曼树前序遍历:");
//        huffmanRoot.preOrder();
//
//        //获取霍夫曼编码
//        Map<Byte,String> huffmanCodes = getCode(huffmanRoot);
//        System.out.println("生成的霍夫曼编码:");
//        System.out.println(huffmanCodes);

        //原始数组压缩后的霍夫曼数组
        byte[] huffmanCodeByte = huffmanZip(contentsBytes);
        System.out.println("huffmanCodeByte:\n"+Arrays.toString(huffmanCodeByte));

        //解码后的原始数据
        byte[] originByte = decode(huffmanCode,huffmanCodeByte);
        System.out.println("解码后的原始数组:\n"+Arrays.toString(originByte));
        System.out.println("原来的字符串： "+new String(originByte));

        //压缩文件
        zipFile("D://Chaoqun Cheng.jpg","D://ccq.zip");
        System.out.println("压缩文件完成~");

        //解压缩文件
        unzipFile("D://ccq.zip","D://Chaoqun Cheng1.jpg");
        System.out.println("解压缩完成~~");

    }

    //--------------------------压缩文件--------------------------
    /*
     * @Description: 将一个文件进行压缩
     *
     * @param srcFile 传入的希望压缩的文件全路径
     * @param desFile 压缩文件压缩后存入的目录
     */
    public static void zipFile(String srcFile, String desFile){
        //创建文件输入流
        FileInputStream fis = null;
        //创建文件输出流
        FileOutputStream fos = null;
        //创建对象输出流
        ObjectOutputStream oos = null;
        try{
            //写入对象文件
            fis = new FileInputStream(srcFile);
            //创建和写入对象文件相同大小的字节数组
            byte[] b = new byte[fis.available()];
            //将文件写入字节数组读取文件
            fis.read(b);
            //转换成赫夫曼编码后得数组 压缩文件
            byte[] huffmanByte = huffmanZip(b);
            //创建文件输出流，存放压缩文件
            fos = new FileOutputStream(desFile);
            //创建一个和文件输出流关联的对象输出流
            oos = new ObjectOutputStream(fos);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanByte);
            //以对象流的方式写入 赫夫曼编码，为了以后回复文件时使用，因为解压缩需要用赫夫曼编码解码
            oos.writeObject(huffmanCode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                fis.close();
                oos.close();
                fos.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    //--------------解压缩文件---------------
    public static void unzipFile(String zipFile, String desFile){
        //定义输入流
        InputStream is = null;
        //定义对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;

        try{
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建文件输入流关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取 赫夫曼编码处理后的数组
            byte[] huffmanByte = (byte[])ois.readObject();
            //读取 回复慢编码表
            Map<Byte,String> huffmanCode = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] sourceByte = decode(huffmanCode,huffmanByte);
            //将解码后的数组输出
            os = new FileOutputStream(desFile);
            os.write(sourceByte);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                ois.close();
                is.close();
                os.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }






    //---------------------解码-----------------------
    /*
     * @Description: 将一个byte 转化成对应的二进制字符串
     *
     * @param flag 标志是否需要补高位，true:需要补高位， false:不需要补高位， 最后一个byte无需补高位
     * @param b 传入的byte
     * @return 返回byte b对应的二进制字符串
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/28 16:52
     */
    public static String byteToBitString(Boolean flag,byte b){
        //将 byte->int 便于后面使用Integer.toBinaryString
        int temp = b;
        if(flag){
            temp |= 256;
        }
        String strByte = Integer.toBinaryString(temp);
        if(flag){
            return strByte.substring(strByte.length()-8);
        }else{
            return strByte;
        }
    }

    /*
     * @Description: 将赫夫曼编码表处理后的byte[]转换成原始数组
     *
     * @param huffmanCode 赫夫曼编码表
     * @param huffmanByte 赫夫曼编码表处理后的byte[]
     * @return
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/28 16:57
     */
    public static byte[] decode(Map<Byte,String> huffmanCode, byte[] huffmanByte){

        //创建StringBuilder接收byte对应的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //将byte[]转换成对应的二进制字符串
        for(int i=0; i<huffmanByte.length; i++){
            byte b = huffmanByte[i];
            boolean flag = (i == huffmanByte.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //得到对应的二进制字符串
        //将赫夫曼编码表反转成Map<String,Byte>
        Map<String,Byte> reverseHuffmanCode = new HashMap<String,Byte>();
        for(Map.Entry<Byte,String> entry:huffmanCode.entrySet()){
            reverseHuffmanCode.put(entry.getValue(),entry.getKey());
        }
        //老师的方法
        List<Byte> list = new ArrayList<>();
        for(int j=0; j<stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String subStr = stringBuilder.substring(j,j+count);
                b = reverseHuffmanCode.get(subStr);
                if(b==null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            j = j+count;
        }
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

        //自己的方法
        //对照翻转的赫夫曼编码表,对得到的二进制字符串进行解码
        //每次匹配字符串的起始位置
//        int start = 0;
//        //原始byte数组索引
//        int index = 0;
//        ArrayList<Byte> originList = new ArrayList<Byte>();
//        //存储每次比较后的结果
//        Byte match;
//        for(int j=1; j<=stringBuilder.length(); j++){
//            //这里需要=因为含头不含尾
//            //从头开始依次匹配
//            String subStr = stringBuilder.substring(start,j);
//            match = reverseHuffmanCode.get(subStr);
//            if(match!=null){
//                //匹配,获取这个字符到集合
//                originList.add(match);
//                //将下次起始位置移动到该字符的后一位
//                start = j;
//            }
//        }
//        //循环结束得到存储所有原始Byte数据的集合,将这些数据放到数组
//        byte[] originByte = new byte[originList.size()];
//        for(Byte b:originList){
//            originByte[index++] = b;
//        }
//        return originByte;
    }



    //---------------封装从原始byte[]直接到压缩后的byte[]---------------
    public static byte[] huffmanZip(byte[] bytes){
        //获取byte[]对应的ArrayList
        ArrayList nodes = getNodeList(bytes);
        //创建霍夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //获取霍夫曼编码
        Map<Byte,String> huffmanCodes = getCode(huffmanTreeRoot);
        //生成压缩byte[]
        byte[] huffmanCodeByte = zip(bytes,huffmanCodes);
        return huffmanCodeByte;

    }

    //将原始的byte[]数组 通过霍夫曼编码表 -> 字符串,再将字符串每8位一个byte生成一个byte[]
    /*
     * @Description:
     *
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码表
     * @return byte[] 返回霍夫曼编码处理后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){
        //拼接霍夫曼编码处理得到的拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历原始byte[],将每一个字符通过霍夫曼编码转变
        for(byte b:bytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        //得到每个字符转变成霍夫曼编好后拼接到一起的字符串
        //每8位一个byte将这个字符串转变成byte[]
        //生成的霍夫曼byte[]长度
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }
        //创建霍夫曼byte[]
        byte[] huffmanCodeByte = new byte[len];
        //记录霍夫曼数组下标
        int index = 0;
        for(int i=0; i<stringBuilder.length(); i=i+8){
            String strByte;
            //如果字符串不是8的倍数
            if(i+8>stringBuilder.length()){
                //从i到stringBuilder结尾
                strByte = stringBuilder.substring(i);
            }else{
                //含头不含尾
                strByte = stringBuilder.substring(i,i+8);
            }
            //需要强制转换因为生成的是Integer
            huffmanCodeByte[index++] = (byte) Integer.parseInt(strByte,2);
        }
        return huffmanCodeByte;
    }



    //--------------------获取霍夫曼编码表--------------------
    //创建一个Map存储霍夫曼编码表
    //字符(key)->字符对应的编码
    static Map<Byte,String> huffmanCode = new HashMap<Byte,String>();

    //创建一个StringBuilder用于存储每一个非叶子节点的路径
    //左子节点=0, 右子节点=1;
    static StringBuilder stringBuilder = new StringBuilder();

    //重载获取霍夫曼编码的方法
    public static Map<Byte,String> getCode(Node node){
        if(node==null){
            return null;
        }
        getCode(node,"",stringBuilder);
        return huffmanCode;
    }

    /*
     * @Description:将传入的node的叶子结点的霍夫曼编码得到并存入huffmanCode集合中
     *
     * @param node 传入结点
     * @param code 路劲 左子结点=0 右子节点=1
     * @param stringBuilder 用于拼接路劲
     */
    //获取霍夫曼编码的方法
    public static void getCode(Node node, String code, StringBuilder stringbuilder){
        //创建一个StringBuilder
        StringBuilder stringBuilder2 = new StringBuilder(stringbuilder);
        //拼接路劲
        stringBuilder2.append(code);
        //判断传入的结点是否为空, 为空则不处理
        if(node != null){
            //判断结点的data域是否为null
            if(node.data == null){
                //为非叶子节点
                //向左递归
                getCode(node.left,"0",stringBuilder2);
                //向右递归
                getCode(node.right,"1",stringBuilder2);
            }else{
                //为叶子结点,以及获取了该叶子结点对应数据的霍夫曼编码
                //存放进map中
                huffmanCode.put(node.data,stringBuilder2.toString());
            }
        }
    }


    //前序遍历
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("霍夫曼树空,无法遍历~");
        }
    }


    //创建霍夫曼树
    public static Node createHuffmanTree(ArrayList<Node> nodes){

        while(nodes.size()>1){
            //将ArrayList从小到大排序
            Collections.sort(nodes);
            //获取weight最小的Node
            Node leftNode = nodes.get(0);
            //获取weight第二小的Node
            Node rightNode = nodes.get(1);
            //通过获取的两个Node创建父节点
            //data = null,因为字符信息只存放在叶子结点
            Node parentNode = new Node(null,leftNode.weight+rightNode.weight);
            //连接
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //删除处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加新建的结点
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    //利用map获得字符串对应的byte数组中每个字符出现的次数并添加到一个ArrayList中用来创建霍夫曼树
    public static ArrayList<Node> getNodeList(byte[] bytes){
        //创建用来存放结点的ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //创建map来通过key-value键值对 存放每个字符出现的次数以及字符的信息
        Map<Byte,Integer> map = new HashMap<>();

        for(byte content:bytes){
            //counts为字符出现的次数
            Integer counts = map.get(content);
            if(counts==null){
                //未出现在map中,本次为第一次
                map.put(content,1);
            }else{
                //不是第一次出现 count+1
                map.put(content,counts+1);
            }
        }
        //现在map中存放的是字符-字符出现次数的键值对
        //讲这些键值对赋给Node并存放到ArrayList中
        //遍历map
        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            //将该键值对信息存到Node中并将Node存放到ArrayList中
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        //得到ArrayList
        Collections.sort(nodes);
        return nodes;
    }
}

class Node implements Comparable<Node> {
    //存放字符
    Byte data;
    //存放字符出现的次数
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight){
        this.data = data;
        this.weight = weight;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    @Override
    public int compareTo(Node o){
        //从小到大排序
        return this.weight - o.weight;
    }
}
