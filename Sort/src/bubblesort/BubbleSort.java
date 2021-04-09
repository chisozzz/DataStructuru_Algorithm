package bubblesort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        /*int[]arr ={4,3,7,5,2};//小数据测试
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/
        //大数据测试算法时间，O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        long startTime = System.currentTimeMillis();
        //真nm的快
//        Arrays.sort(arr);
        //真的慢
        bubbleSort(arr);
        long endTime = System.currentTimeMillis();
        //花费的时间很长，不适合大数据排序
        System.out.printf("花费的时间：%d ms" , (endTime - startTime));
        /*int temp =0;
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i]= arr[i+1];
                arr[i+1] = temp;
            }
        }
        //第一次排序后的数组
        System.out.println("第1次排序：");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < size - 1 - 1; i++) {
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        //第二次排序后的数组
        System.out.println("第2次排序：");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < size - 1 - 2; i++) {
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] =arr[i+1];
                arr[i+1] = temp;
            }
        }
        //第3次排序后的数组
        System.out.println("第3次排序：");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < size - 1 - 3; i++) {
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] =arr[i+1];
                arr[i+1] = temp;
            }
        }
        //第4次排序后的数组
        System.out.println("第4次排序：");
        System.out.println(Arrays.toString(arr));*/

    }
    public static void bubbleSort(int [] arr){
        //优化后
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            //大循环是比较最多比较多少趟
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //小循环是从0开始比较到位置，保持arr.length -1-i右边是排好的
                if (arr[j]>arr[j+1]){//左右两边逆序则调换位置，使得右边一直是最大的
                    flag = true;//表示有过调换操作
                    temp = arr[j];
                    arr[j] =arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (flag){//有过调换操作，则将flag初始化
                flag = false;
            }else {//本趟排序没有操作，数组已经是有序的，则直接跳出循环
                break;
            }
        }
    }
//    public static void bubbleSort(int [] arr){
//        //没优化
//        int temp = 0;
//        for (int i = 0; i < arr.length - 1; i++) {
//            //大循环是比较最多比较多少趟
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                //小循环是从0开始比较到位置，保持arr.length -1-i右边是排好的
//                if (arr[j]>arr[j+1]){//左右两边逆序则调换位置，使得右边一直是最大的
//                    temp = arr[j];
//                    arr[j] =arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//        }
//    }
}
