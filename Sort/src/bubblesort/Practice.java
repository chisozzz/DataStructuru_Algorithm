package bubblesort;

import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        int[] arr = {3,5,1,2,6,4};
        sort(arr);
        String s = Arrays.toString(arr);
        System.out.println(s);
    }
    public static void sort(int[] arr){
        int temp =0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (flag){
                flag = false;
            }else {
                break;
            }
        }
    }
}
