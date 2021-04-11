package quicksort;

import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        int[] arr ={3,1,4,6,5,8,7};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[]arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right)/2];
        while (l < r) {
            while (arr[l] < pivot){
                l++;
            }
            while (arr[r] > pivot){
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot){
                l++;
            }
        }
        if (l == r){
            l++;
            r--;
        }
        if (left < r){
            sort(arr,left,r);
        }
        if (right > l){
            sort(arr,l,right);
        }
    }
}
