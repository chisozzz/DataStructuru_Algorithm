package practice;

public class Practice {
    //定义一个节点,单链表
    private static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }
    }

    //空链表,只有一个头结点
    public static Node emptyList() {
        return new Node().next=null;
    }

    //单链表的删除，插入，查找
    //删除,返回对应的值，错误返回-1
    public static int del(int i,Node head){
        //head链表为空,无法删除
        if (head==null) return -1;
        //创建哨兵
        Node solider =new Node();
        //将head链表增加哨兵
        solider.next=head;
        //p头指针
        Node p=solider;
        //n记录链表长度,从1开始
        int n=1;
        while (p!=null && n < i){
            p=p.next;
            n++;
        }
        //没找到需要删除的，或者i<=0
        if (p ==null || n > i)  return -1;
        //执行删除操作
        int result=p.next.val;
        p.next=p.next.next;
        return result;
    }
    //删除链表中的节点
    public static void delByVal(Node node){
    }


    //删除头结点后面的节点

    //通过序号n获取n位置的值
    public static int get(int n, Node head) {
        Node p = head.next;//从头结点指向下一个首元结点
        int i = 1;
        while (p != null && i < n) {
            p = p.next;
            ++i;
        }
        if (p == null || i > n) return -1;//n值不合法n超过了链表的长度或n<=0;
        return p.val;
    }

//    public static void del(int n, Node head) {
//        Node p = head;
//        p.next = p.next.next;
//        head.next = p;
//    }
}
