package practice;

import java.util.Scanner;
import java.util.Stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(3);
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
//单链表模拟栈
class LinkedListStack{
    private Node head = new Node();//空链表，first为头结点
    private int maxSize; //栈的最大长度
    private Node top; //栈顶指针 ,默认为null
    private int length; //栈的实际长度
    //构造器
    public LinkedListStack(int maxSize){
        this.maxSize = maxSize;
        top = head;//top指向头结点，栈的有效节点从下一个开始
    }
    //栈满
    public boolean isFull(){
        return length == maxSize;
    }
    //栈空
    public boolean isEmpty(){
        return length == 0;
    }
    //查看栈的实际长度
    public int getLength(){
        return length;
    }
    //入栈
    public void push(int num){
        if (isFull()){
            System.out.println("栈满，入栈失败");
            return;
        }
        Node node = new Node(num);
        top.next = node;
        top = top.next;
        length++;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，出栈失败"    );
        }
        Node p = head;
        while (true){
            if (p.next == top){
                break;
            }
            p = p.next;
        }
        int value = top.value;
        p.next = null;
        top = p;
        length--;
        return value;
    }
    //查看栈
    public void list(){
        if (isEmpty()){
            System.out.println("空栈~~");
            return;
        }
        //将head链表逆序输出
        Stack<Node> stack = new Stack<>();
        //将单链表遍历，依次入栈
        Node p = head.next;
        while (p != null){
            stack.push(p);
            p = p.next;
        }
        int i = length;
        //遍历栈，出栈打印
        while (stack.size()>0){
            System.out.printf("stack[%d] = %d \n",i--,stack.pop().value);
        }
    }
}

//单链表结构
class Node{
    public int value;//和数组保持相似，整数
    public Node next;
    public Node() {
    }
    public Node(int value) {
        this.value = value;
    }
}