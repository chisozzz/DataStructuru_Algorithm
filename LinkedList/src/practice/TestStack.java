package practice;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        //测试栈的使用,入栈push()方法，栈顶指针
        Stack<String> stack=new Stack<>();
        stack.push("jack");
        stack.push("tom");
        stack.push("icon");
        //stack的特性，先入后出
        while (stack.size()>0){
            //pop 出栈 返回出栈的对象
            System.out.println(stack.pop());
        }
    }
}
