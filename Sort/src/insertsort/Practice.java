package insertsort;

import java.util.Arrays;

public class Practice {
    //插入排序的练习
    public static void main(String[] args) {
        int[] arr ={4,2,1,5,6,3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[]arr){
        //保持索引左边的数组有序
        int insertVal =0;
        int insertIndex =0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i -1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex +1 ]= arr[insertIndex];
                insertIndex--;
            }
            //找到了需要插入insertVal的位置后插入
            arr[insertIndex +1] = insertVal;
        }
    }
}
