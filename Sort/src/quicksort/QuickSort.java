package quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr ={-4,23,0,-23,5,33};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long startTime = System.currentTimeMillis();
        //规模越大，快排比希尔排序越快，反之在一定规模，希尔比快排快
        quickSort(arr, 0, arr.length - 1);

        long endTime = System.currentTimeMillis();
        //花费的时间很长，不适合大数据排序，不过比冒泡排序和选择排序好一点
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));
//        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        //left是左边界，right是右边界
        //l是左排序的索引，r是右排序的索引
        int l = left;
        int r = right;
        int temp;
        //中轴值
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            //左索引在pivot左边，右索引在pivot右边，没有结束快排
            while (arr[l] < pivot) {
                //在左排序中找打比pivot大的值，l指向该值的索引
                l++;
            }
            while (arr[r] > pivot) {
                //在右排序中找打比pivot小的值，r指向该值的索引
                r--;
            }
            if (l >= r) {
                //l和r相遇过，本次pivot快排结束
                //l是下一次快排的左边界
                //r是下一次快排的右边界
                break;
            }
            //本次快排未结束，则交换l，r索引的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot) {
                //左索引值是边界，证明左边完成了排序，r前移继续右边的排序
                r--;
            }
            if (arr[r] == pivot) {
                //右索引值是边界，证明右边完成排序，l后移继续左边的排序
                l++;
            }

        }
        if (l == r) {
            //两个指向了中轴，让l和r指向下次快排的左边界，右边界
            l++;
            r--;
        }
        if (left < r) {
            //左递归，r是本次递归的右边界
            quickSort(arr, left, r);
        }
        if (right > l) {
            //右递归，l是本次递归的右边界
            quickSort(arr, l, right);
        }
    }
}
