package shellsort;

import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        int[]arr ={2,5,1,6,3,7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public  static void sort(int[]arr){
        int insertVal;
        int insertIndex;
        for (int gap = arr.length/2; gap > 0 ; gap/=2) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < arr.length ; j+=gap) {
                    insertVal = arr[j];
                    insertIndex = j - gap;
                    while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                        arr[insertIndex + gap] = arr[insertIndex];
                        insertIndex-=gap;
                    }
                    arr[insertIndex + gap] = insertVal;
                }
            }
        }
    }
}
