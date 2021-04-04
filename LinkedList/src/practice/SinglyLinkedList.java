package practice;

import java.util.Stack;

//单链表的增删改查功能实现
public class SinglyLinkedList {
    //构造一个带头空链表
    public Node head = new Node();

    //合并两个有序链表
    public static Node combine(Node l1, Node l2) {
        //两个都不为空，进行比较大小，小的插入新的链表
        Node combine = new Node();
        //三个链表，每个链表需要一个指针
        Node p=combine;
        Node q =l1.next;
        Node m =l2.next;
        while ( q != null && m != null){
            if (q.no < m.no){
                p.next= q;
                q = q.next;
            }else {
               p.next = m;
               m = m.next;
            }
            p = p.next;
        }
        //对两个有序链表进行校验，有任何一个链表为空，combine连上另一个链表
        if ( q == null){
            p.next = m;
        }
        if (m == null){
            p.next = q;
        }
        return combine;
    }

    //从尾到头打印单链表
    //使用stack的先入后出的特性，打印
    public static void reversePrint(Node head) {
        //对链表进行校验
        if (head.next == null) {
            System.out.println("链表为空，打印失败");
            ;
        }
        //遍历链表，将链表的各个结点压入栈中
        //创建stack
        Stack<Node> stack = new Stack<>();
        Node p = head.next;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        //遍历栈，依次出栈，打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //单链表的反转
    public static void reverse(Node head) {
        //对单链表的空链表和长度为1的情况，返回本身
        if (head.next == null || head.next.next == null) {
            return;
        }
        //创建一个新链表，存储反转后链表
        Node reverse = new Node();
        Node p = head.next;
        //将head链表的p结点取出插入reverse链表最前面,p指向p的下一个结点
        while (p != null) {
            //temp临时变量存入p.next,防止head断链
            Node temp = p.next;
            p.next = reverse.next;
            reverse.next = p;
            p = temp;
        }
        head.next = reverse.next;
    }

    //查找单链表的倒数第K个结点
    public static Node findLastIndexNode(Node head, int n) {
        //对传入的参数进行校验
        //单链表为空
        if (head.next == null) {
            return null;
        }
        //获取head的有效个数
        int size = getLength(head);
        //n的值校验
        if (n <= 0 || n > size) {
            return null;
        }
        //创建一个辅助变量，遍历到倒数的n节点
        Node p = head.next;
        for (int i = 0; i < size - n; i++) {
            //p移向下一个
            p = p.next;
        }
        return p;
    }

    //单链表中有效节点的个数
    public static int getLength(Node head) {
        //对传入的链表进行校验
        if (head.next == null) {
            return 0;
        }
        //创建一个辅助变量p
        Node p = head.next;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    //通过节点的no编号排序的添加节点
    public void addByOrder(Node heroNode) {
        Node p = head;
        boolean flag = false;
        while (true) {//死循环，后面判断停止的条件，比较容易分析
            if (p.next == null)
                break;
            if (p.next.no > heroNode.no)
                break;
            else if (p.next.no == heroNode.no) {
                flag = true;
                break;
            }
            p = p.next;
        }
        if (flag) {
            //节点已存在，提示
            System.out.printf("no = %d的节点已存在\n", heroNode.no);
        } else {
            heroNode.next = p.next;
            p.next = heroNode;
        }
    }

    //后插法直接添加节点
    public void add(Node heroNode) {
        //辅助指针p指向头结点head遍历链表，找到最后的节点插入heroNode
        Node p = head;
        //p指向的下一个对象不为null，一直循环，直到指向最后的节点
        while (p.next != null) {
            p = p.next;
        }
        p.next = heroNode;
    }

    //修改节点的数据，no不能更改
    public void update(Node newHeroNode) {
        Node p = head.next;
        boolean flag = false;
        while (true) {
            if (p == null) {
                break;
            }
            if (p.no == newHeroNode.no) {
                flag = true;
                break;
            }
            p = p.next;
        }
        if (flag) {
            p.name = newHeroNode.name;
            p.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("no = %d 的节点不存在，不能更改\n", newHeroNode.no);
        }
    }

    //删除指定的no删除节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("空链表，请添加数据");
            return;
        }
        Node p = head;
        boolean flag = false;
        while (true) {
            if (p.next == null) {
                break;
            }
            if (p.next.no == no) {
                flag = true;
                break;
            }
            p = p.next;
        }
        if (flag) {
            p.next = p.next.next;
        } else {
            System.out.printf("no = %d 的节点不存在\n", no);
        }
    }

    //查看单链表节点
    public void list() {
        //辅助变量p指向头结点下一个，方便遍历,一定要移动p
        Node p = head.next;
        if (p == null) {
            System.out.println("空链表~~");
            return;
        }
        while (p != null) {
            System.out.println(p);
            p = p.next;
        }
    }

    //测试
    public static void main(String[] args) {
        //创建一个链表
        SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();
        SinglyLinkedList singlyLinkedList2 = new SinglyLinkedList();

        //创建节点
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(3, "吴用", "智多星");
        Node node4 = new Node(4, "林冲", "豹子头");
        //添加节点
//        singlyLinkedList.add(node1);
//        singlyLinkedList.add(node2);
//        singlyLinkedList.add(node3);

        //通过no添加节点
        singlyLinkedList1.addByOrder(node1);
        singlyLinkedList1.addByOrder(node4);
        singlyLinkedList1.addByOrder(node2);
        singlyLinkedList1.addByOrder(node3);
        //查看链表
        singlyLinkedList1.list();
//        singlyLinkedList2.list();

        //合并两个链表
//        System.out.println("合并后的链表：");
//        Node sum = combine(singlyLinkedList1.head, singlyLinkedList2.head);
//        Node p =sum.next;
//        while (p != null){
//            System.out.println(p);
//            p = p.next;
//        }

        //反转链表
        System.out.println("逆序打印链表：");
        singlyLinkedList1.reverse(singlyLinkedList1.head);
//        //查看倒数第1个结点
//        System.out.println("倒数第4个结点：");
//        System.out.println(singlyLinkedList.findLastIndexNode(singlyLinkedList.head, 4));

        /*//创建修改的节点
        Node newNode = new Node(2, "小卢", "麒麟");
        singlyLinkedList.update(newNode);

        //查看修改后的链表
        System.out.println("修改后的链表");
        singlyLinkedList.list();

        //删除链表
        singlyLinkedList.del(1);
        singlyLinkedList.del(2);
//        singlyLinkedList.del(3);
//        singlyLinkedList.del(4);
        //查看修改后的链表
        System.out.println("删除后的链表");
        singlyLinkedList.list();

        //节点的有效个数
        System.out.println("节点的有效个数");
        System.out.println(getLength(singlyLinkedList.head));*/
    }
}

//定义单链表的节点结构
class Node {
    //data域
    public int no;
    public String name;
    public String nickname;
    //next域
    public Node next;

    //构造器
    //无参的头结点
    public Node() {
    }

    //带参构造,新结点默认next为null
    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重写toString方法，便于查看
    @Override
    public String toString() {
        return "Node[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ']';
    }
}