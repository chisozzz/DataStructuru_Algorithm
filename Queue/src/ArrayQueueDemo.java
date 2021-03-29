import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayQueue queue=new ArrayQueue(3);//初始化一个长度为三的队列
        char key=' ';
        boolean loop=true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：向队列添加数据");
            System.out.println("g(get)：获取队列头的数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");
            System.out.println("请选择需要的操作：");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        queue.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int i = scanner.nextInt();
                    queue.add(i);
                    break;
                case 'g':
                    try {
                        int i1 = queue.get();
                        System.out.printf("取出的数据是%d\n",i1);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.head();
                        System.out.printf("队列头的数据是%d\n",head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop=false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列,数组只能使用一次
class ArrayQueue{
    public int maxSize;//数组的最大存储空间
    public int front;//队列的头部前一个索引
    public int rear;//队列的尾部数据索引
    public int[] arr;//队列的实际结构

    //队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize]; //初始化队列
        front=-1;//队列的头部
        rear=-1;//队列的尾部
    }

    //队列是否满了
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //数据入队
    public void add(int n){
        if (isFull()){
            System.out.println("队列已满，无法添加数据。");
            return;
        }
        rear++;
        arr[rear]=n;
    }
    //获取数据，出队
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("空队列，请添加数据.");
        }
        front++;
        return arr[front];
    }

    //查看头部数据
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("空队列，请添加数据。");
        }
        return arr[front+1];
    }
    //显示队列所有数据
    public void show(){
        if (isEmpty()){
            throw new RuntimeException("空队列，请添加数据。");
        }
        for (int i = front+1; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

}
