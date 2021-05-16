package com.AtGuiGu.Chp6_Stack_Infix_Suffix;
/*
 * @Description: 用栈模拟计算器一位数的 + - * / 运算
 * 1.循环遍历需要计算的字符串表达式
 * 2.如果当前位字符,
 *  2.1如果运算符栈空,或者当前优先级大于栈顶优先 则直接入字符栈
 *  2.2如果当前优先级小于等于栈顶优先级,
 *      2.2.1则数字栈出栈两个元素,
 *      2.2.2字符栈出栈一个 后
 *      2.2.3出栈数字在运算符前,计算得到的结果入栈,
 *      2.2.4当前元素入栈
 * 3.如果当前位数字 考虑多位数
 *  3.1 如果当前位为字符串最后一位,当前数字直接入数字栈
 *  3.2 如果下一位为字符则拼接字符串入栈后清空,
 * 4.i++
 * 5.遍历完成后 数字栈出栈两个数字,运算符栈出栈一个运算符,后出栈数字在前,运算结果入栈
 * 6.直到运算符栈为空,则数字栈的唯一数字为最后的计算结果
 *
 *
 * @param null
 * @return
 * @author Chaoqun
 * @creed: Talk is cheap,show me the code
 * @date 2020/12/10 9:38
 */
public class Calculator {
    public static void main(String[] args) {
        //定义两个栈存放数字和操作符
        ArrayStack2 numStack = new ArrayStack2( 10);
        ArrayStack2 operStack = new ArrayStack2( 10);
        //定义需要计算的字符串表达式
        String expression = "1*2-3/4+5*6-7*8+9/10";
        //定义指针遍历输入的字符串
        int index = 0;
        //存放每次扫描得到的字符
        char ch = ' ';
        //pop出进行运算的两个数字和运算符
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        //存储运算结果的变量
        int res = 0;
        //拼接字符串,用于拼接可能遇到的多位数字
        String keepNum = "";
        //循环遍历需要计算的表达式
        while (true) {
            if(index == expression.length()){
                break;
            }
            //获得字符串的第一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是否是运算符
            if (operStack.isOper(ch)) {
                //如果是运算符,判断符号栈是否空
                if (operStack.isEmpty()) {
                    //直接入栈
                    operStack.push(ch);
                } else if (operStack.priority(ch) > operStack.priority(operStack.peek())) {
                    //待入栈操作符优先级大于栈内操作符 入栈
                    operStack.push(ch);
                } else{
                    //否则待入栈操作符优先级小于栈内操作符
                    //数组栈出栈两个数字,符号栈出栈一个符号
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    //计算得到结果如数字栈
                    res = operStack.cal(num1, num2, oper);

                    numStack.push(res);
                    //当前操作符入栈
                    operStack.push(ch);
                }
            }else{
                //不是运算符则是数字,查看下一位是否是运算符,如果还是数字则继续查看下一位直到是运算符
                //拼接
                keepNum += ch;
                //判断是否是最后一位
                if(index == expression.length()-1){
                    //达到表达式的最后一位
                    //直接入栈
                    //String to int
                    numStack.push(Integer.parseInt(keepNum));
                }else if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //下一位是字符 入栈
                    //String to int
                    numStack.push(Integer.parseInt(keepNum));
                    //清空keepNum
                    keepNum = "";
                }
            }
            index++;
        }
        //退出循环后,扫描完成
        while(true){
            //数组栈和符号栈分别pop出两个数字一个字符进行运算
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //计算后的数字入栈
            numStack.push(res);
            //如果入栈后数字栈只剩一个数字,就是计算的结果
            if(numStack.top == 0){
                break;
            }
        }
        //退出循环后,数字栈内的元素即为计算结果
        System.out.printf("\"%s\"的计算结果为: %d",expression,numStack.pop());
    }
}
//定义一个 ArrayStack2表示栈
class ArrayStack2 {
    //数组大小
    public int maxSize;
    //模拟栈的数组栈
    public int[] stack;
    //模拟栈顶的变量
    public int top = -1;


    //构造方法
    public ArrayStack2(int size) {
        this.maxSize = size;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        //栈顶与数组容量大小相同
        return (top == maxSize - 1);
    }

    //判断栈空
    public boolean isEmpty() {
        //top = -1
        return top == -1;
    }

    //入栈 push
    public void push(int data) {
        //判断是否满
        if (isFull()) {
            System.out.println("栈满,无法入栈...");
            return;
        }
        //栈未满
        top++;
        stack[top] = data;
    }

    //出栈 pop
    public int pop() {
        //判断是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空,无数据可出栈...");
        }
        //整数栈
        //临时变量存储出栈数据
        int temp = stack[top];
        top--;
        return temp;
    }

    //栈的遍历,需要从栈顶开始从上往下显示数据
    public void stackShow() {
        //判断是否空
        if (isEmpty()) {
            System.out.println("栈空,无数据可出栈...");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

    //返回当前栈顶的数据
    public int peek(){
        return stack[top];
    }

    //自己定义操作符的优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            //这里默认只有 + - * / 运算,
            return -1;
        }
    }

    //判断字符是不是操作符
    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //计算方法
    /*
     * @Description:
     *
     * @param num1 pop出的第一个数 应该在运算符的后面
     * @param num2 pop出的第二个数 应该在运算符的前面
     * @param oper 运算符
     * @return int 运算结果
     * @author Chaoqun
     * @creed: Talk is cheap,show me the code
     * @date 2020/12/10 9:46
     */
    public int cal(int num1, int num2, int oper) {
        //保存运算结果
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}
