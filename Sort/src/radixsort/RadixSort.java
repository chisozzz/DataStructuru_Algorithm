package radixsort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
//        int[]arr ={53,3,542,748,14,214};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long startTime = System.currentTimeMillis();
        //时间复杂度是O(n * N)N为桶的个数，是时间复杂度最小的，但是非常耗费空间
        radixSort(arr);
//        Arrays.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));
//        System.out.println(Arrays.toString(arr));
    }
    public static void radixSort(int[]arr){
        //基数排序是桶排序的扩展，
        //二维数组表示十个桶，行表示桶，列表示桶内元素
        int[][] bucket = new int[10][arr.length];
        //一维数组表示桶内数据的有效个数
        int[] bucketCount = new int[10];
        //查找原始数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max<arr[i]){
                max =arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int k = 0, n = 1; k < maxLength; k++,n*=10) {
        //第1轮，对所有数的个位数，进行分桶

        for (int i = 0,ele; i < arr.length; i++) {
            ele = arr[i] / n % 10;//个位数的值
            bucket[ele][bucketCount[ele]] = arr[i];
            bucketCount[ele]++;
        }
        //分桶后，从桶中取值放入arr
        for (int i = 0,index =0; i < bucketCount.length; i++) {
            if (bucketCount[i] !=0){
                for (int j = 0; j < bucketCount[i]; j++) {
                    arr[index++]=bucket[i][j];
                }
                bucketCount[i] =0;
            }
        }
        }
    }
}
