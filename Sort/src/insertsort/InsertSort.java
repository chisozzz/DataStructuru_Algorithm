package insertsort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long startTime = System.currentTimeMillis();
        insertSort(arr);
        long endTime = System.currentTimeMillis();
        //花费的时间很长，不适合大数据排序，不过比冒泡排序和选择排序好一点
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));
//        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[]arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //insertVal是每次插入的值，insertIndex是每次插入的索引，从1开始直到最后，
            //保持[0,i)之间是有序的，[i,arr.length]之间是无序的
            insertVal = arr[i];
            insertIndex = i - 1;
            //insertIndex不能越界，同时保持insertVal左边比他小
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1]= arr[insertIndex];
                insertIndex--;//默认减减，会让插入的位置在insertIndex的右移一位
            }
            //循环后找到了insertIndex + 1插入的位置
            arr[insertIndex +1] = insertVal;
//            System.out.println("第"+i+"轮排序："+ Arrays.toString(arr));
        }

        /*int insertVal= arr[1];
        int insertIndex = 1 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] =arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第1轮排序："+ Arrays.toString(arr));

        insertVal= arr[2];
         insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] =arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第2轮排序："+ Arrays.toString(arr));

        insertVal= arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] =arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第3轮排序："+ Arrays.toString(arr));

        insertVal= arr[4];
        insertIndex = 4 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] =arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex +1] = insertVal;
        System.out.println("第4轮排序："+ Arrays.toString(arr));*/
    }
}
