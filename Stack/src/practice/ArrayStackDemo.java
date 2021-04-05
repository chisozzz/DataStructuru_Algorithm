package practice;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        String key;
        while (loop) {
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("show:查看栈");
            System.out.println("exit:退出程序");
            System.out.println("请输入你的操作:");
            key = sc.next();
            switch (key) {
                case "push":
                    if (stack.isFull()) {
                        System.out.println("栈满，入栈失败");
                        break;
                    }
                    System.out.println("请输入一个数字：");
                    int num = sc.nextInt();
                    stack.push(num);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据：" + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    break;
            }
        }
        System.out.println("退出成功");
    }
}

//数组模拟栈结构
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;//栈顶指向数组最后的下一个
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //数据入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满，入栈失败");
            return;
        }
        stack[++top] = num;
    }

    //数据出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈，出栈失败");
        }
        return stack[top--];
    }

    //查看栈
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }
}