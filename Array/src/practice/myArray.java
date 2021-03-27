package practice;

/**
 * myArray本质是数组，自动库容和可扩容行是数组重新创建实现的，频繁的扩容会导致时间的增加
 * 建议根据数据规模的大小构造的时候指定大小
 * @param <T>
 */

public class myArray<T> {
    //泛型的数组结构
    private T[] data;
    //存储数据个数
    private int size;

    //有参构造函数
    public myArray(int capacity){
        data= (T[]) new Object[capacity];
        size=0;
    }

    //无参构造默认16容器大小
    public myArray() {this(16);
    }

    //向index位置添加元素
    public void add(int index, T elem){
        checkIndex(index);
        //自动扩容机制
        if (size== data.length){
            //将容器的大小扩大两倍
            resize(2* data.length);
        }
        //将index位置以后的元素向后移动一位
        for (int i = size -1; i >= index ; i--) {
            data[i+1]=data[i];
        }
        data[index]=elem;
        //实际大小加1
        size++;
    }
    //向头部添加元素
    public void addFirst(T elem){
        add(0,elem);
    }
    //向尾部添加元素
    public void addLast(T elem){
        add(size-1,elem);
    }
    //查找元素，返回元素下标，没有则为-1
    public int find(T elem){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(elem)){
                return i;
            }
        }
        return -1;
    }
    //获取给定index位置的元素
    public T get(int index){
        checkIndex(index);
        return data[index];
    }

    //获取容器的大小
    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size ==0;
    }
    //修改index位置的元素
    public void set(int index , T elem){
        checkIndex(index);
        data[index] =elem;
    }
    //删除index位置的元素,返回删除的元素
    public T remove(int index){
        checkIndex(index);
        T rec=data[index];
        for (int i = index + 1; i <size ; i++) {
            data[i-1]=data[i];
        }
        data[size-1]=null;
        size--;
        //自动缩容,存储的数据是容器的1/4
        if (size== data.length / 4 && data.length /2 !=0){
            resize(data.length/2);
        }
        return rec;
    }
    //删除指定元素
    public void removeElement(T elem){
        int index = find(elem);
        if (index !=-1 )remove(index);
    }
    //删除第一个元素
    public void removeFirst(){
        remove(0);
    }
    //删除最后一个元素
    public void removeLast(){
        remove(size-1);
    }

    //是否包含元素，返回布尔值
    public boolean contains(T elem){
        int index = find(elem);
        if (index ==-1)return false;
        return true;
    }
    //获取当前元素个数
    public int count(){
        return size;
    }

    //扩容设置
    private void resize(int capacity){
        T[] newData= (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i]=data[i];
        }
        data = newData;
    }
    //索引越界检查
    private void checkIndex(int index){
        if (index < 0 || index >= size){
            System.out.println("索引越界异常");
            //索引越界了，虚拟机直接退出
            System.exit(0);
        }
    }

    //重写toString方法，样式[1, 2, 3]
    @Override
    public String toString() {
       StringBuilder sb =new StringBuilder();
       sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i == size -1)sb.append(data[i]);
            if (i !=size -1)sb.append(data[i] +", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
