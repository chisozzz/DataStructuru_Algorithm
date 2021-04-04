package practice;

public class DoubleLinkedList {
    //初始化一个头结点
    private static Node2 head =new Node2();

    public static Node2 getHead() {
        return head;
    }

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //创建节点
        Node2 node1 = new Node2(1, "宋江", "及时雨");
        Node2 node2 = new Node2(2, "卢俊义", "玉麒麟");
        Node2 node3 = new Node2(3, "吴用", "智多星");
        Node2 node4 = new Node2(4, "林冲", "豹子头");
        //添加节点
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node2);


        //查看双链表
        doubleLinkedList.list();

        //修改
        Node2 newNode = new Node2(4, "小林", "小豹子");
        doubleLinkedList.update(newNode);
        System.out.println("修改后的双链表");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的双链表");
        doubleLinkedList.list();

    }

    //通过节点的no编号排序的添加节点
    public void addByOrder(Node2 heroNode) {
        Node2 p = head;
        while (true){
            if (p.next == null){
                p.next =heroNode;
                heroNode.pre =p;
                return;
            }
            if (p.next.no > heroNode.no){
                heroNode.next = p.next;
                if (p.next != null){
                    p.next.pre =heroNode;
                }
                p.next=heroNode;
                heroNode.pre =p;
                return;
            }else if (p.next.no == heroNode.no){
                System.out.printf("no = %d的节点已存在\n", heroNode.no);
                return;
            }
            p = p.next;
        }
    }
    //查看
    public void list(){
        if (head.next == null){
            System.out.println("空链表");
            return;
        }
        Node2 p =head.next;
        while (p !=null){
            System.out.println(p);
            p = p.next;
        }
    }
    //添加,最后节点添加
    //后插法直接添加节点
    public void add(Node2 heroNode) {
        //辅助指针p指向头结点head遍历链表，找到最后的节点插入heroNode
        Node2 p = head;
        //p指向的下一个对象不为null，一直循环，直到指向最后的节点
        while (p.next != null) {
            p = p.next;
        }
        //p下一个指向heroNode
        p.next = heroNode;
        //新结点的pre指向p，双向
        heroNode.pre = p;
    }
    //修改
    public void update(Node2 newNode){
        Node2 p = head.next;
        boolean flag =false;
        while (p !=null){
            if (newNode.no == p.no){
                flag = true;
                break;
            }
            p = p.next;
        }
        if (flag){
            p.name = newNode.name;
            p.nickname = newNode.nickname;
        }else {
            System.out.printf("no = %d 的节点不存在，不能更改\n", newNode.no);
        }
    }
    //删除
    public void del(int n){
        if (head.next == null){
            System.out.println("空链表，请添加数据");
            return;
        }
        Node2 p = head.next;
        while (p != null){
            if (p.no == n){
                p.pre.next=p.next;
                //如果p指向最后一个节点，p.next不存在,不用pre指向前面
                if (p.next != null){
                    p.next.pre = p.pre;
                }
                return;
            }
            p = p.next;
        }
        System.out.printf("no = %d 的节点不存在，不能删除\n",n);
    }
}

//双向链表的节点结构
class Node2{
    //data域
    public int no;
    public String name;
    public String nickname;
    //指针域
    public Node2 next;
    public Node2 pre;
    //构造器
    //无参的头结点
    public Node2() {
    }

    //带参构造,新结点默认next为null
    public Node2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重写toString方法，便于查看
    @Override
    public String toString() {
        return "Node2[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ']';
    }
}
