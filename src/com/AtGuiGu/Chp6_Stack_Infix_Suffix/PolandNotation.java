package com.AtGuiGu.Chp6_Stack_Infix_Suffix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @Description: 中缀表达式 => 逆波兰表达式(后缀) 利用栈实现多位数 + - * / () 的计算
 * (1)Calculate----------利用ArrayList和Stack计算后缀表达式的思路------------
 * 1.将后缀表达式的数据和运算符存入到一个ArrayList
 * 2.将这个ArrayList传递给一个计算表达式的方法
 * 3.方法中先创建一个栈
 * 4.遍历传递过来的ArrayList
 * 5.判断如果是数字,则直接入栈
 * 6.如果是字符,从栈中出栈两个数字进行运算(后出栈的再运算符之前)
 * 7.将计算的结果入栈
 * 8.最后栈内的一个数字就是计算的结果
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/10 13:56
 */
public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式: 4 * 5 - 8 + 60 + 8 / 2 => 后缀表达式: 4 5 * 8 - 60 + 8 2 / +

        //1.中缀表达式
        String infix = "1+((2+3)×4)-5";

        //2.中缀表达式字符串 ====> 中缀表达式ArrayList
        List<String> infixList = infixToList(infix);
        System.out.println("中缀表达式:"+infixList);

        //3.中缀表达式ArrayList ====> 后缀表达式ArrayList
        List<String> suffixList = infixToSuffix(infixList);
        System.out.println("后缀表达式:"+suffixList);

        //4.通过后缀表达式ArrayList计算后缀表达式的值
        int result = calculate(suffixList);
        //5.打印计算结果
        System.out.printf("\"%s\" 的计算结果为: %d", suffixList.toString(), result);
    }

    //----2.中缀表达式字符串 ====> 中缀表达式ArrayList-----
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
                while (i < inFix.length() && inFix.charAt(i) >= 48 && inFix.charAt(i) <= 57) {
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

    //-------3.中缀表达式ArrayList ====> 后缀表达式ArrayList-------
    //传入的list存放了中缀表达式
    public static List<String> infixToSuffix(List<String> list){
        //1.初始化一个栈一个ArrayList；
        //1.1ArrayList存放数字后续的运算符,因为此容器一直承担存放的功能不需要取出
        //且如果用栈后面需要逆序打印才能得到后缀表达式
        List<String> suffixList = new ArrayList<>();
        //1.2栈存放运算符
        Stack<String> operStack = new Stack<>();

        //2.从左至右扫描存放中缀表达式的infixList；
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
                //右括号“)”，则依次弹出operStack栈顶的运算符，并存入suffixList，
                while(!operStack.peek().equals("(") ){
                    // 直到遇到左括号为止，此时将这一对括号丢弃
                    suffixList.add(operStack.pop());
                }
                //退出循环表示当前operStack栈顶为左括号"(",弹出这个左括号
                operStack.pop();
            }else{
                while(operStack.size() != 0 && priority(item) <= priority(operStack.peek())){
                    //当前元素优先级小于等于栈顶元素优先级,栈顶元素出栈并存入suffixList
                    suffixList.add(operStack.pop());
                }
                //退出循环条件不满,当前元素入栈
                operStack.push(item);
            }
        }
        //5.退出循环,suffixList遍历完成,将operStack中的运算符依次pop并存入suffixList
        while(!operStack.isEmpty()){
            suffixList.add(operStack.pop());
        }
        //6.退出循环,suffixList为最后的结果
        return suffixList;
    }

    //---------4.通过后缀表达式ArrayList计算后缀表达式的值-----------
    public static int calculate(List<String> list){
        //创建一个存放数字的栈
        Stack<Integer> stack = new Stack<>();

        //1.从左到右扫描list
        for (String item : list){
            //如果是数字,则直接入栈,这里用正则表达式cover多位数
            if(item.matches("\\d+")){
                //入栈
                stack.push(Integer.parseInt(item));
            }else {
                //不是数字就是运算符,则从栈中出栈两个数字进行运算
                //第一个出栈数
                int num1 = stack.pop();
                //第二个出栈数
                int num2 = stack.pop();
                //运算结果
                int res = 0;
                if (item.equals("+")){
                    res = num2 + num1;
                }else if(item.equals("-")){
                    res = num2 - num1;
                }else if(item.equals("×")){
                    res = num2 * num1;
                }else if(item.equals("/")){
                    res = num2 / num1;
                }else{
                    //如果输入了其他符号则抛出异常
                    throw new RuntimeException("输入符号有误....");
                }
                //将计算结果入栈
                stack.push(res);
            }
        }
        //结束循环后,栈内最后的一个元素就是计算结果
        return stack.pop();
    }

    //-------------比较运算符优先级的方法-------------
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

    //-------------将一个逆波兰表达式的数据和符号依次存放进一个ArrayList-------------
    public static List<String> getListString(String suffixExpression) {
        //按照空格分开得到存放数据和字符的数组
        String[] split = suffixExpression.split(" ");
        //创建一个ArrayList存放字符串的数据
        List<String> list = new ArrayList<>();
        for (String item : split) {
            list.add(item);
        }
        return list;
    }


}
