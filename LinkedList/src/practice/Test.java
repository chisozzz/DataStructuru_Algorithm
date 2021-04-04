package practice;

import java.util.Stack;
//第二遍练习
public class Test {
    public static void main(String[] args) {
        //创建一个链表
        SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();
        SinglyLinkedList singlyLinkedList2 = new SinglyLinkedList();

        //创建节点
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(3, "吴用", "智多星");
        Node node4 = new Node(4, "林冲", "豹子头");

        //通过no添加节点
        singlyLinkedList1.addByOrder(node1);
        singlyLinkedList2.addByOrder(node4);
        singlyLinkedList1.addByOrder(node2);
        singlyLinkedList2.addByOrder(node3);
        //查看链表
        singlyLinkedList1.list();
        singlyLinkedList2.list();

        //合并两个链表
        System.out.println("合并后的链表：");
        Node sum = combine2(singlyLinkedList1.head, singlyLinkedList2.head);
        Node p =sum.next;
        while (p != null){
            System.out.println(p);
            p = p.next;
        }
        System.out.println("逆序打印,结构不变");
        reversePrint(sum);

        System.out.println("反转链表");
        reverse(sum);
        p =sum.next;
        while (p != null){
            System.out.println(p);
            p = p.next;
        }
    }
    //合并两个有序链表
    public static Node combine2(Node l1 , Node l2){
        //每个链表都是带头结点，辅助变量可以没有，合并为新链表
        Node combine = new Node();
        Node p =combine ;
        while( l1.next != null && l2.next != null){
            if (l1.next.no < l2.next.no){
                p.next = l1.next;
                l1 = l1.next;
            }else {
                p.next =l2.next;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1.next == null){
            p.next = l2.next;
        }
        if (l2.next == null){
            p.next = l1.next;
        }
        //返回一个带头结点
        return combine;
    }
    //逆序打印单链表,使用栈的特性
    public static void reversePrint(Node head){
        //遍历head链表，将除了头结点外的节点入栈
        Node p =head.next;
        Stack<Node> stack =new Stack<>();
        while (p != null){
            stack.push(p);
            p = p.next;
        }
        //遍历栈，依次出栈，先进后出特性
        while (stack.size() > 0){
            //pop()出栈方法，栈顶依次出栈
            System.out.println(stack.pop());
        }
    }
    //单链表的反转
    public static void reverse(Node head){
        if (head.next ==null || head.next.next ==null){
            return;
        }
        //创建一个新链表，每次从第一个结点插入head第一个结点
        Node reverse = new Node();
        Node p =head.next;
        while (p != null){
            Node temp =p.next;
            p.next=reverse.next;
            reverse.next = p;
            p=temp;
        }
        head.next = reverse.next;
    }
    //查找单链表倒数第K个结点
    public static Node findLastIndexNode(Node head,int k){
        if (head.next ==null){
            return null;
        }
        //获取head的长度，校验k的值
        Node p = head.next;
        int size=0;
        while (p != null) {
            size++;
            p = p.next;
        }
        if (k<=0 || k>size){
            return null;
        }
        p=head.next;
        for (int i = 0; i < size - k; i++) {
            p=p.next;
        }
        return p;
    }

}
