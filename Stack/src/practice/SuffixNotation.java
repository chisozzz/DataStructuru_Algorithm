package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式实现计数器
 */
public class SuffixNotation {
    public static void main(String[] args) {
        //字符串为后缀表达式，使用空格进行分隔字符
        //String suffixExpression ="1 2 + 3 *";
        String expression ="(10 +2)*3";//中缀表达式
        //将中缀表达式字符串转为List集合
        List<String> infixExpressionList = toInfixList(expression);
        System.out.println(infixExpressionList);
        //中缀表达式转后缀表达式
        List<String> suffixExpressionList = infixToSuffix(infixExpressionList);
//        List<String> list = toSuffixList(suffixExpression);
        System.out.println(suffixExpressionList);
        int res = calculate(suffixExpressionList);
        System.out.println("expression = "+res);
    }
    //方法：中缀表达式转后缀表达式
    private static List<String> infixToSuffix(List<String> infixExpressionList) {
        //一个栈放置运算符，一个List放置转化的后缀表达式
        Stack<String> s1= new Stack<>();
        List<String> s2 = new ArrayList<>();
        //遍历List集合，如果是操作数直接入栈,如果是运算符，则判断栈是否为空或者栈顶是'('是的话
        //直接入栈，如果优先级比栈顶元素高，直接入栈，否则弹出栈顶，放到List中，继续比较新栈顶,直到不是小于的情况，加入s1
        //如果是'('则直接入栈，如果是')'则弹出栈顶放到List中直到栈顶是'('为止，扫描到最后，将s1中所有元素加入List中
        for (String s : infixExpressionList) {
            if (s.matches("\\d+")){
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            }else if (s.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else if (s.equals("+") || s.equals("-")|| s.equals("*")|| s.equals("/")){
                if (s1.isEmpty() || s1.peek().equals("(")){
                    s1.push(s);
                }else if (priority(s) > priority(s1.peek())){
                    s1.push(s);
                }else {
                    while (s1.isEmpty() && priority(s) <= priority(s1.peek())){
                        s2.add(s1.pop());
                    }
                    s1.push(s);
                }
            }
        }
        while (s1.size()>0){
            s2.add(s1.pop());
        }
        return s2;
    }

    private static int priority(String s) {
        int res =0;
        if (s.equals("+") || s.equals("-")){
            res =1;
        }else if (s.equals("*") || s.equals("/")){
            res = 2;
        }
        return res;
    }

    //将中缀表达式字符串转为List集合
    private static List<String> toInfixList(String s) {
        //对expression遍历，每个字符加入集合中，注意多位数的
        char ch=' ';
        List<String> list = new ArrayList<>();
            int index=0;
        while (index < s.length()) {
            ch = s.charAt(index);
            if (ch < 48 || ch > 57){
                //非数字
                list.add(ch +"");
            }else {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while (index+1 < s.length() && s.charAt(index+1)>=48 && s.charAt(index+1) <= 57) {
                    sb.append(s.charAt(index+1));
                    index++;
                }
                list.add(sb.toString());
            }
            index++;
        }
        return list;
    }


    private static int calculate(List<String> list) {
        //有了处理好的后缀表达式后，对list遍历，遇到数字直接入栈
        //操作符则直接弹出两个数字，进行计算，知道扫描结束，栈中就是结果
        Stack<String> stack = new Stack<>();
        int num1 =0;
        int num2 =0;
        int res =0;
        for (String s : list) {
            if (s.matches("\\d+")){
                //字符是数字，直接入栈
                stack.push(s);
            }else if (s.equals("+")){
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                res = num1 + num2;
                stack.push(res + "");
            }else if (s.equals("-")){
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                res = num2 - num1;
                stack.push(res + "");
            }else if (s.equals("*")){
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                res = num2 * num1;
                stack.push(res + "");
            }else if (s.equals("/")){
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                res = num2 / num1;
                stack.push(res + "");
            }else {
                System.out.println("字符不合法");
            }
        }
        res = Integer.parseInt(stack.pop());
        return res;
    }

    //将后缀字符串形式转换成List集合
    private static List<String> toSuffixList(String suffixExpression) {
        //创建一个list集合保存String
        List<String> list =new ArrayList<>();
        //字符串分隔split，形成数组
        String[] split = suffixExpression.split(" ");
        for (String s : split) {
            list.add(s);
        }
        return list;
    }
}
