package practice;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=================");
            System.out.println("add:  添加员工");
            System.out.println("list: 查看所有员工");
            System.out.println("find: 查找员工");
            System.out.println("del:  删除员工");
            System.out.println("exit: 退出");
            System.out.println("请输入指令：");
            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("请输入员工id");
                    int id = sc.nextInt();
                    System.out.println("请输入员工姓名");
                    String ename = sc.next();
                    Emp emp = new Emp(id, ename);
                    hashTab.addById(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "del":
                    System.out.println("请输入员工id");
                    id=sc.nextInt();
                    hashTab.del(id);
                    break;
                case "find":
                    System.out.println("请输入员工id");
                    id = sc.nextInt();
                    emp = hashTab.findById(id);
                    if (emp == null){
                        System.out.println("表中无此员工");
                    }else {
                        System.out.println("id="+emp.id+" name="+emp.ename);
                    }
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        }
    }
}

//创建员工节点
class Emp {
    public int id;
    public String ename;
    public Emp next;//默认为null

    public Emp(int id, String ename) {
        this.id = id;
        this.ename = ename;
    }
}

//创建员工链表
class EmpLinkedList {
    //创建链表的头结点,默认为空
    private Emp head;

    //添加员工到链表，链表尾直接添加,不保证id顺序
    public void add(Emp emp) {
        if (head == null) {
            //如果链表为空
            head = emp;
            return;
        }
        //不为空，直接尾部添加
        Emp p = head;
        while (true) {
            if (p.next == null) {//遍历完毕
                break;
            }
            p = p.next;//没有遍历完毕，后移
        }
        p.next = emp;
    }
    //添加员工，根据员工的id，顺序添加到链表
    public void addByOrder(Emp emp){
        //链表为空
        if (head == null){
            head = emp ;
            return;
        }
        //链表只有一个节点
        if (head.next == null){
            if (head.id <= emp.id){
                head.next= emp;
            }else {
                Emp temp = head;
                head=emp;
                head.next=temp;
            }
            return;
        }
        //链表有两个以上节点时
        Emp p = head;
        while (p.next !=null){
            if (p.next.id > emp.id) {
                break;
            }
            p=p.next;
        }
        emp.next=p.next;
        p.next = emp;
    }
    //删除员工，根据id
    public void del(int id) {

        Emp emp = findById(id);
        if (emp == null){
            System.out.println("表中无该员工");
        }else {
            if (emp == head){
                head=null;
            }else {
                Emp p = head;
                while (p.next != emp){
                    p=p.next;
                }
                p.next=p.next.next;
            }
        }
    }
    //通过id查找员工信息
    public Emp findById(int id) {
        if (head == null) {
//            System.out.println("链表为空");
            return null;
        }
        Emp p = head;
        while (true) {
            if (p.id == id) {
                return p;
            }
            if (p.next == null) {
                break;
            }
            p = p.next;
        }
        return null;
    }

    //查看链表的信息
    public void list() {
        if (head == null) {
            //空链表
            System.out.println("链表为空");
            return;
        }
        Emp p = head;
        System.out.print("链表为");
        while (true) {
            System.out.print(" => " + "id=" + p.id + " name=" + p.ename);
            if (p.next == null) {
                break;
            }
            p = p.next;
        }
        System.out.println();
    }
}

//创建哈希表，使用数组存储链表的结构
class HashTab {
    private int size;
    private EmpLinkedList[] empLinkedLists;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //将数组赋值
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //根据散列函数，向哈希表添加员工
    public void add(Emp emp) {
        int no = hashFun(emp.id);
        empLinkedLists[no].add(emp);
    }
    public void addById(Emp emp){
        int no = hashFun(emp.id);
        empLinkedLists[no].addByOrder(emp);
    }
    public void del(int id){
        int no = hashFun(id);
        empLinkedLists[no].del(id);
    }
    //遍历哈希表，展示所有员工
    public void list() {
        for (int i = 0; i < size; i++) {
            System.out.print("第 "+(i+1)+" 条");
            empLinkedLists[i].list();
        }
    }

    //通过id查找哈希表的员工信息
    public Emp findById(int id) {
        int no = hashFun(id);
        Emp findEmp = empLinkedLists[no].findById(id);
        return findEmp;
    }

    //编写散列函数，有多种，这里简单的取模
    private int hashFun(int id) {
        return id % size;
    }
}
