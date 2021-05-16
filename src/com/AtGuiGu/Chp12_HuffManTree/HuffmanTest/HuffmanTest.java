package com.AtGuiGu.Chp12_HuffManTree.HuffmanTest;

import java.io.*;
import java.util.*;

/*
 * @Description: 赫夫曼树的相关练习
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/29 10:02
 */
public class HuffmanTest {
    public static void main(String[] args) {
        String contents = "i like like like java do you like a java";
        byte[] contentsBytes = contents.getBytes();
        System.out.println("原始数组: \n"+ Arrays.toString(contentsBytes));

        //原始数组压缩后的霍夫曼数组
        byte[] huffmanCodeByte = huffmanZip(contentsBytes);
        System.out.println("huffmanCodeByte:\n"+Arrays.toString(huffmanCodeByte));

        //解码后的原始数据
        byte[] originByte = decode(huffmanCodeByte,huffmanCode);
        System.out.println("解码后的原始数组:\n"+Arrays.toString(originByte));
        System.out.println("原来的字符串： "+new String(originByte));

        //压缩文件
        zipFile("D://Chaoqun Cheng.jpg","D://ccq2.zip");
        System.out.println("压缩文件完成~");

        //解压缩文件
        unZipFile("D://ccq2.zip","D://Chaoqun Cheng2.jpg");
        System.out.println("解压缩完成~~");
    }


    //解压缩文件
    public static void unZipFile(String zipFile, String desFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] b = (byte[])ois.readObject();
            Map<Byte,String> huffmanCode= (Map<Byte,String>)ois.readObject();
            byte[] srcByte = decode(b,huffmanCode);
            os = new FileOutputStream(desFile);
            os.write(srcByte);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                is.close();
                ois.close();
                os.close();
            }catch(Exception e){
                System.out.println(e.getMessage());

            }
        }
    }

    //压缩文件
    public static void zipFile(String srcFile, String desFile){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fos = new FileOutputStream(desFile);
            oos = new ObjectOutputStream(fos);
            byte[] huffmanByte = huffmanZip(b);
            oos.writeObject(huffmanByte);
            oos.writeObject(huffmanCode);
        }catch(Exception e){
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


    //解码
    public static byte[] decode(byte[] huffmanByte, Map<Byte,String> huffmanCode){
        StringBuilder strBu = new StringBuilder();
        for(int i=0; i< huffmanByte.length; i++) {
            Byte b = huffmanByte[i];
            boolean flag = (i == huffmanByte.length-1);
            strBu.append(bitToBinaryString(!flag, b));
        }

        Map<String,Byte> reverseHuffmanCode = new HashMap<String,Byte>();
        for(Map.Entry<Byte,String> entry:huffmanCode.entrySet()){
            reverseHuffmanCode.put(entry.getValue(),entry.getKey());
        }

        ArrayList<Byte> list = new ArrayList<Byte>();

        for(int j=0; j<strBu.length(); ){
            int count = 1;
            Byte b = null;
            boolean flag = true;
            while(flag){
                String match = strBu.substring(j,j+count);
                b = reverseHuffmanCode.get(match);
                if(b==null){
                    count++;
                }else{
                    flag=false;
                }
            }
            list.add(b);
            j = j + count;
        }
        byte[] srcByte = new byte[list.size()];
        for(int k=0; k<srcByte.length; k++){
            srcByte[k] = list.get(k);
        }
        return srcByte;
    }


    //字节转化成二进制的方法
    public static String bitToBinaryString(boolean flag, byte b){
        int temp = b;
        if(flag){
            temp |= 256;
        }
        String subStr = Integer.toBinaryString(temp);
        if(flag){
            return subStr.substring(subStr.length()-8);
        }else{
            return subStr;
        }
    }

    //封装从原始byte[]到赫夫曼编码后的byte[]
    public static byte[] huffmanZip(byte[] arr){
        ArrayList<Node> nodes = getNodesList(arr);
        Node root = createHuffmanTree(nodes);
        Map<Byte,String> huffmanCodes = getCode(root);
        byte[] huffmanByte = zip(arr,huffmanCodes);
        return huffmanByte;
    }


    //压缩数据的方法
    public static byte[] zip(byte[] arr, Map<Byte,String> huffmanCode){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:arr){
            stringBuilder.append(huffmanCode.get(b));
        }
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanByte = new byte[len];
        int index = 0;
        String subStr;
        for(int i=0; i<stringBuilder.length(); i=i+8){
            if(i+8 > stringBuilder.length()){
                subStr = stringBuilder.substring(i);
            }else{
                subStr = stringBuilder.substring(i,i+8);
            }
            huffmanByte[index++] = (byte)Integer.parseInt(subStr,2);
        }
        return huffmanByte;
    }

    //重载获取赫夫曼编码
    public static Map<Byte,String> getCode(Node node){
        if(node==null){
            return null;
        }
        getCode(node,"",stringBuilder);
        return huffmanCode;
    }

    //获取赫夫曼编码
    public static void getCode(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node!=null){
            if(node.data==null){
                getCode(node.left,"0",stringBuilder2);
                getCode(node.right,"1",stringBuilder2);
            }else{
                huffmanCode.put(node.data,stringBuilder2.toString());
            }
        }
    }

    //赫夫曼编码表
    static Map<Byte,String> huffmanCode = new HashMap<Byte,String>();
    //拼接字符串
    static StringBuilder stringBuilder = new StringBuilder();

    //创建赫夫曼树
    public static Node createHuffmanTree(ArrayList<Node> nodes){

        while(nodes.size()>1){
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null,leftNode.weight+rightNode.weight);

            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parentNode);

            Collections.sort(nodes);
        }
        return nodes.get(0);
    }

    //获取存放数据的list集合
    public static ArrayList<Node> getNodesList(byte[] arr){
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte,Integer> map = new HashMap<Byte,Integer>();
        for(byte b:arr){
            Integer count = map.get(b);
            if(count==null){
                map.put(b,1);
            }else{
                map.put(b,count+1);
            }
        }
        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        Collections.sort(nodes);
        return nodes;
    }



}

//节点类
class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight){
        this.data = data;
        this.weight = weight;
    }

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
                "data='" + data + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }
}
