import java.util.Scanner;

public class CycleArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CycleQueueFull queue = new CycleQueueFull(3);//初始化一个长度为三的队列,给rear+1预留了一个空间，最大存储空间就是3
        char key;  //声明key用于switch选择
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：向队列添加数据");
            System.out.println("g(get)：获取队列头的数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");
            System.out.println("请选择需要的操作：");
            key = scanner.next().charAt(0);
            switch (key) {
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
                        System.out.printf("取出的数据是%d\n", i1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.head();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//数组模拟循环队列，使用%让front和rear循环变动
class CycleQueue {
    public int maxSize;//数组的最大存储空间,预留一个空间给rear+1
    public int front;//队列的头部第一个数据
    public int rear;//队列的尾部后一位
    public int[] arr;//队列的实际结构

    //队列的构造器
    public CycleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize]; //初始化队列
    }

    //队列是否满了
    public boolean isFull() {
        //给rear后预留了一个空间，所以rear+1达到最大空间，取模等于front
        return (rear + 1) % maxSize == front;
    }

    //队列是否为空
    public boolean isEmpty() {
        //队列初始化front和rear都是0
        return rear == front;
    }

    //数据入队
    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加数据。");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;//循环队列需要取模
    }

    //获取数据，出队
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据.");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //查看头部数据
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据。");
        }
        return arr[front];
    }

    //显示队列所有数据
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据。");
        }
        //因为是循环队列，rear可能在front的前面，需要一个有效个数(rear+maxSize-front)%maxSize
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);//front的实际位置因为循环，需要通过front%maxSize
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
//循环队列不预留空间
class CycleQueueFull{
    private int maxSize;//数组的最大空间
    private int front;
    private int rear;
    private int[] arr;
    private int timeValue;//队列的实际空间
    public CycleQueueFull(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize]; //初始化队列
    }

    //队列是否满了
    public boolean isFull() {
        return timeValue==maxSize;
    }

    //队列是否为空
    public boolean isEmpty() {
        //队列初始化front和rear都是0
        return timeValue==0;
    }

    //数据入队
    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加数据。");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;//循环队列需要取模
        timeValue++;
    }

    //获取数据，出队
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据.");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        timeValue--;
        return temp;
    }

    //查看头部数据
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据。");
        }
        return arr[front];
    }

    //显示队列所有数据
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，请添加数据。");
        }
        //因为是循环队列，rear可能在front的前面，需要一个有效个数timeValue
        for (int i = front; i < front + timeValue; i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);//front的实际位置因为循环，需要通过front%maxSize
        }
    }
}