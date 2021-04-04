package practice;

import sun.awt.windows.ThemeReader;

public class Josephu {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(25);
        circleLinkedList.showBoy();
        circleLinkedList.count(3,3,25);
    }
}

//循环单链表,解决约瑟夫问题
class CircleLinkedList{
    private Boy first = null;



    /**
     * 约瑟夫问题的解决方法，数小孩
     * @param start 开始数的位置
     * @param count 数的个数
     * @param nums  总共人数
     */
    public void count(int start ,int count , int nums){
        //对参数进行校验
        if (first == null || start < 1 || start > nums){
            System.out.println("输入的参数有误");
            return;
        }
        //创建一个指针helper,指向最后一个节点
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //将first和helper指向start和start前一个
        for (int i = 0; i < start -1 ; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        //开始数小孩，进行出队,直到最后一个小孩
        while (true){
            if (helper == first){
                break;
            }
            for (int i = 0; i < count - 1; i++) {
                helper = helper.getNext();
                first = first.getNext();
            }
            System.out.printf("编号 %d 的小孩出圈 \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后出圈的小孩的编号: %d  \n", first.getNo());
    }
    //添加小孩,nums是小孩个数,形成环状
    public void addBoy(int nums){
        //对nums校验
        if (nums < 1){
            System.out.println("nums格式错误");
            return;
        }
        //辅助指针cur，帮组添加节点
        Boy cur=null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i ==1){//第一个结点，形成循环
                first = boy;
                first.setNext(first);
                cur = first;
            }else {
                cur.setNext(boy);
                boy.setNext(first);
                cur =cur.getNext();
            }
        }
    }
    //查看小孩
    public void showBoy(){
        if (first == null){
            System.out.println("循环链表为空");
            return;
        }
        //辅助指针，遍历链表
        Boy cur=first;
        while (true){
            System.out.printf("编号为 %d 的小孩 \n", cur.getNo());
            if (cur.getNext() == first){
                break;
            }
            cur = cur.getNext();
        }
    }
}
//小孩节点
class Boy{
    private int no;//编号
    private Boy next;//指针，默认为null

    public Boy() {
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}