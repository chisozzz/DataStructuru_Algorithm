package practice;

//单链表的增删改查功能实现
public class SinglyLinkedList {
    //构造一个带头空链表
    public Node head = new Node();

    //通过节点的no编号排序的添加节点
    public void addByOrder(Node heroNode) {
        Node p=head;
        boolean flag=false;
        while (true){//死循环，后面判断停止的条件，比较容易分析
            if (p.next==null)
                break;
            if (p.next.no>heroNode.no)
                break;
            else if (p.next.no== heroNode.no){
                flag=true;
                break;
            }
            p=p.next;
        }
        if (flag){
            //节点已存在，提示
            System.out.printf("no = %d的节点已存在\n", heroNode.no);
        }else {
            heroNode.next=p.next;
            p.next=heroNode;
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
    public void update(Node newHeroNode){
        Node p=head.next;
        boolean flag=false;
        while (true){
            if (p==null){
                break;
            }
            if (p.no== newHeroNode.no){
                flag=true;
                break;
            }
            p=p.next;
        }
        if (flag){
            p.name= newHeroNode.name;
            p.nickname= newHeroNode.nickname;
        }else {
            System.out.printf("no = %d 的节点不存在，不能更改\n", newHeroNode.no);
        }
    }
    //删除指定的no删除节点
    public void del(int no){
        if (head.next == null){
            System.out.println("空链表，请添加数据");
            return;
        }
        Node p= head;
        boolean flag=false;
        while (true){
            if (p.next==null){
                break;
            }
            if (p.next.no==no){
                flag=true;
                break;
            }
            p=p.next;
        }
        if (flag){
            p.next=p.next.next;
        }else {
            System.out.printf("no = %d 的节点不存在\n",no);
        }
    }

    //查看单链表节点
    public void list() {
        //辅助变量p指向头结点下一个，方便遍历,一定要移动p
        Node p = head.next;
        if (p==null){
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
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

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
        singlyLinkedList.addByOrder(node1);
        singlyLinkedList.addByOrder(node4);
        singlyLinkedList.addByOrder(node2);
        singlyLinkedList.addByOrder(node3);
        //查看链表
        singlyLinkedList.list();

        //创建修改的节点
        Node newNode = new Node(2, "小卢", "麒麟");
        singlyLinkedList.update(newNode);
        //查看修改后的链表
        System.out.println("修改后的链表");
        singlyLinkedList.list();

        //删除链表
        singlyLinkedList.del(1);
        singlyLinkedList.del(2);
        singlyLinkedList.del(3);
//        singlyLinkedList.del(4);
        //查看修改后的链表
        System.out.println("删除后的链表");
        singlyLinkedList.list();
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