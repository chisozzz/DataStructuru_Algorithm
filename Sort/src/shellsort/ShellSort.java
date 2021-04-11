package shellsort;


import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
//        int[]arr ={8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long startTime = System.currentTimeMillis();
        shellSort(arr);
        long endTime = System.currentTimeMillis();
        //花费的时间很长，不适合大数据排序，不过比冒泡排序和选择排序好一点
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));
//        System.out.println(Arrays.toString(arr));
    }
    public static void shellSort(int[]arr){
        //希尔排序是对插入排序的优化，通过缩小增量的方式，将无序数据的数据有序程度增加
        //将数组分组，每次分组的组数就是缩小增量gap
        int temp=0;
        int index=0;
//        int count =0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            //分组后，gap不仅是组数，也是小组元素之间的步长
            for (int k = 0; k < gap; k++) {
                //每个组的头一个就是k
                for (int i = k + gap; i < arr.length; i+=gap) {
                    //对每个小组进行插入排序从第二个数开始，这个小组是[0,0+5]之类的
                    temp = arr[i];//需要插入的值,gap就是步长
                    index = i - gap;//索引位置,gap是步长
                    while (index >= 0 && temp < arr[index]){
                        arr[index + gap] = arr[index];
                        index-=gap;
                    }
                    arr[index + gap] = temp;
                }
            }
//            System.out.println("第"+(++count)+"轮排序：" + Arrays.toString(arr));
        }
    }
}
