package FibonacciSearch;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[]arr={1,8,10,89,1000,1234};
//        int[] f = f();
//        System.out.println(Arrays.toString(f));
        int index = fibSearch(arr, 8);
        System.out.println("index:"+index);
    }
    public static int fibSearch(int[]arr,int key){
        //非递归的实现方法
        int low=0; //左索引
        int high=arr.length-1;//右索引
        int k=0;//斐波那契数列的k的下标
        int mid=0;//中点的值，f[k] -1 =f[k-1]-1+f[k-2]-1 +1;
        int[] f = f();
        while (high>f[k]-1){//让arr数组的长度符合斐波那契数列，取k的值为新arr的长度
            k++;
        }
        //创建满足斐波那契数列的数组，元素和arr一直，保持有序，
        int[] temp = Arrays.copyOf(arr, f[k]);
        //为使得数组有序，则将后补的0值改为arr【high】的值
        for (int i = high+1; i < temp.length; i++) {
            temp[i]=arr[high];
        }
        //非递归
        while (low <= high){
            //将temp数组的长度f[k]-1分为f[k-1]-1和f[k-2]-1左右两部分中点就是黄金分割点
            mid = low+f[k-1]-1;
            if (key<arr[mid]){
                //key可能在f[k-1]-1左部分，所以k需要-1
                high=mid-1;
                k--;
            }else if(key > arr[mid]){//key可能在f[k-2]-1的右部分，所以k需要-2
                low=mid+1;
                k-=2;
            }else {
                //如果中点和high相比小，则取小的部分
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

    //因为斐波那契查找需要用到斐波那契数组，生成一个数组
    public static int[] f(){
        //非递归方式生成斐波那契数列
        int[]f=new int[maxSize];
        f[0]=1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

}
