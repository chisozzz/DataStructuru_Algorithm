package practice;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
//        int[]arr={4,6,8,5,9};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long startTime = System.currentTimeMillis();
        heapSort(arr);
        long endTime = System.currentTimeMillis();
        //堆排序的最差，最好，平均的时间复杂度都是O(nlogn)具有不稳定性,性能比较稳定
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));
//        System.out.println(Arrays.toString(arr));
    }
    public static void heapSort(int[]arr){
        int temp=0;
        //arr.length/2-1表示的是数组中第一个非叶子结点的索引，从该结点索引到0就是所有非叶子结点
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);//从下到上，左到右调整数组为大顶堆
        }
        //调整好了后，将根节点和末尾的数交换，length-1
        //i即表示实际长度，也表示数组的末尾，0则是根节点索引
        for (int i = arr.length-1; i >0 ; i--) {
            temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            //填入0是因为在根节点交换前，数组是满足大顶堆的，交换后，只有0索引不满足，
            //只需要从0再调整一个数组就好了
            adjustHeap(arr,0,i);
        }
    }

    /**
     *将数组调整成顶堆的数组，也就是父节点的值大于子节点，arr[i]>arr[2*i+1] && arr[i]>arr[2*i+2]
     * @param arr 待调整的原始数组
     * @param i 非叶子结点的索引
     * @param length 需要排序成顶堆的实际长度
     */
    private static void adjustHeap(int[]arr,int i,int length){
        //将非叶子节点存储在临时变量中
        int temp = arr[i];
        //数组中的二叉树其实是算法，从左子节点开始将以i为根节点的子树排序成大顶堆的形式
        for (int j = 2*i+1; j < length ; j=2*j+1) {//j为左节点，j存在，每次j都是下个左节点
            if (j+1<length && arr[j]<arr[j+1]){//右节点存在且右节点大于左节点，让j指向右节点
                j++;
            }
            if (arr[j]>temp){//右节点的值大于i的值
                arr[i]=arr[j];//替换
                i=j;//让i指向左右节点较大的节点
            }else {
                //这里直接退出是因为，我们每次调整数组的非叶子节点是从左到右，从下到上，
                // 也就是说i的下面子树已经调整好了,不用再向下调整我们直接退出
                break;
            }
        }
        //将原i值还给现在的i
        arr[i]=temp;
    }
}
