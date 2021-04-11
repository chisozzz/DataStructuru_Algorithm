package selectsort;

import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        int[] arr ={4,2,1,5,6,3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[]arr){
        //选择排序就是将数组中的每个数找到他的位置，交换
        int minVal;
        int index;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            minVal =arr[i];
            for (int j = i; j < arr.length -1; j++) {
                if (minVal>arr[j+1]){
                    minVal = arr[j+1];
                    index = j+1;
                }
            }
            if (index != i){
                arr[index] = arr[i];
                arr[i] = minVal;
            }
        }
    }
}
