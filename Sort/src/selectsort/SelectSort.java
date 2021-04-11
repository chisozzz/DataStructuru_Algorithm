package selectsort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long startTime = System.currentTimeMillis();
        selectSort(arr);
        long endTime = System.currentTimeMillis();
        //花费的时间很长，不适合大数据排序，不过比冒泡排序好一点
        System.out.printf("花费的时间：%d ms", (endTime - startTime));
    }

    public static void selectSort(int[] arr) {
        //选择排序法，每一次在未排序的数组中寻找最小值和其索引，依次从左到右的寻找，直到数组大小-1的位置排好
        int minIndex = 0;
        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第" + (i + 1) + "轮：" + Arrays.toString(arr));
        }

        /*//第1轮
        int minIndex=0;
        int min = arr[0];
        for (int i = 0 + 1; i < arr.length  ; i++) {
            if (min>arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex !=0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第1轮："+ Arrays.toString(arr));

        //第2轮
         minIndex=1;
         min = arr[1];
        for (int i = 1 + 1; i < arr.length  ; i++) {
            if (min>arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex !=1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第2轮："+ Arrays.toString(arr));

        //第3轮
        minIndex=2;
        min = arr[2];
        for (int i = 2 + 1; i < arr.length  ; i++) {
            if (min>arr[i]){
                minIndex = i;
                min = arr[i];
            }
        }
        if (minIndex !=2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第3轮："+ Arrays.toString(arr));*/
    }
}
