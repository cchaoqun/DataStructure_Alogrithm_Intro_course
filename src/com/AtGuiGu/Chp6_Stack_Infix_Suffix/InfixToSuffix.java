package com.AtGuiGu.Chp6_Stack_Infix_Suffix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Description: 中缀表达式ArrayList ===> 后缀表达式ArrayList
 * 1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
 * 2.从左至右扫描中缀表达式；
 * 3.遇到操作数时，将其压s2；
 * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
 *  4.1如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *  4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1；
 *  4.3否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
 * 5.遇到括号时：
 *  5.1如果是左括号“(”，则直接压入s1
 *  5.2如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6.重复步骤2至5，直到表达式的最右边
 * 7.将s1中剩余的运算符依次弹出并压入s2
 * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/10 15:00
 */
public class InfixToSuffix {
    public static void main(String[] args) {
        //将字符串中的数据和运算符存放到ArrayList中进行遍历
        String inFix = "10+ (( 2 + 3 ) × 4 ) - 5";
        //得到存放中缀表达式的list
        List<String> list = infixToList(inFix);
        System.out.println("得到的ArrayList是: "+list);

        //1.初始化一个栈一个ArrayList；
        //1.1ArrayList存放数字后续的运算符,因为此容器一直承担存放的功能不需要取出
        //且如果用栈后面需要逆序打印才能得到后缀表达式
        List<String> suffixList = new ArrayList<>();
        //1.2栈存放运算符
        Stack<String> operStack = new Stack<>();

        //2.从左至右扫描存放中缀表达式的list；
        for (String item : list){
            //3.判断是否是数字 利用正则表达式 \d+ 表达多位数字
            if(item.matches("\\d+")){
                //是数字直接存入list
                suffixList.add(item);
            }else if(item.equals("(")){
                //先考虑括号的情况
                //左括号"("直接存入operStack
                operStack.push(item);
            }else if(item.equals(")")){
                //右括号“)”，则依次弹出operStack栈顶的运算符，并存入list，
                while(!operStack.peek().equals("(") ){
                    // 直到遇到左括号为止，此时将这一对括号丢弃
                    suffixList.add(operStack.pop());
                }
                //退出循环表示当前operStack栈顶为左括号"(",弹出这个左括号
                operStack.pop();
            }else{
                while(operStack.size() != 0 && priority(item) <= priority(operStack.peek())){
                    //字符栈不为空; 当前元素优先级小于等于栈顶元素优先级; 栈顶元素出栈并存入list
                    suffixList.add(operStack.pop());
                }
                //退出循环,当前元素入栈
                operStack.push(item);
            }
        }
        //5.退出循环,list遍历完成,将operStack中的运算符依次pop并存入list
        while(!operStack.isEmpty()){
            suffixList.add(operStack.pop());
        }
        //6.退出循环,list为最后的结果
        System.out.println(inFix+" 的后缀表达式为: "+suffixList);


    }

    //将中缀表达式转成对应的List 适用于一般情况的字符串转list
    //1 + ( ( 2 + 3 ) × 4 ) - 5
    public static List<String> infixToList(String inFix){
        //创建存放数据的List
        List<String> list = new ArrayList<>();
        //遍历字符串的指针
        int i = 0;
        //存放取出的字符
        char c = ' ';
        //拼接多位数
        String keepNum = "";
        do {
            //处理空格
            if(((c=inFix.charAt(i))+"").equals(" ")) {
                i++;
            }else if ((c = inFix.charAt(i)) < 48 || (c = inFix.charAt(i)) > 57) {
                //1.判断是否是字符 "0"=[48] "9"=[57]
                //加入list
                list.add(c + "");
                //i后移一位
                i++;
            } else {
                //是一个数字,考虑多位数
                //重置拼接字符串
                keepNum = "";
                while (i < inFix.length() &&( c=inFix.charAt(i)) >= 48 && (c=inFix.charAt(i)) <= 57) {
                    keepNum += c;
                    //i后移一位
                    i++;
                }
                //将多位数加入到list
                list.add(keepNum);
            }
        } while (i < inFix.length());
        //返回存储了数据的list
        return list;
    }

    //字符串 -> ArrayList 只针对刚好每个字符之间都是空格隔开的情况
    public static List<String> getStringList(String inFix){
        //split得到一个数组
        String[] split = inFix.split(" ");
        //创建存放数据的ArrayList
        ArrayList<String> list = new ArrayList<>();
        //遍历数组将数据存放进ArrayList
        for (String item : split){
            list.add(item);
        }
        //将存放数据的数组返回
        return list;
    }

    //比较运算符的优先级
    public static int priority(Object oper){
        if(oper.equals("+") || oper.equals("-")){
            return 1;
        }else if(oper.equals("×") || oper.equals("/")){
            return 2;
        }else{
            System.out.println("输入的运算符有误...");
            return 0;
        }
    }
}



