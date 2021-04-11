package mergesort;


public class MergeSort {
    public static void main(String[] args) {
        //int[]arr ={8,4,5,7,1,3,6,2};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        int[] temp = new int[arr.length];
        long startTime = System.currentTimeMillis();
        //规模越大，和快排，希尔，处于同一时间复杂度
        mergeSort(arr,0,arr.length-1,temp);
        long endTime = System.currentTimeMillis();
        System.out.printf("花费的时间：%d ms\n", (endTime - startTime));

    }
    //归并排序，采用的是分治的思想，将问题分解成小的步骤，慢慢解决

    public static void mergeSort(int[] arr ,int left,int right , int[]temp){
        //将数组分开
        if (left < right){//分解的数组可以再分
            int mid = (left + right)/2;//中间索引
            mergeSort(arr,left,mid,temp);//向左边递归分解
            mergeSort(arr,mid+1,right,temp);//向右边递归分解
            merge(arr,left,mid,right,temp);//最后合并
//            System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * 这是归并算法的核心，解决的部分
     * @param arr 原始数组
     * @param left 左边有序数组的索引
     * @param mid  右边有序数组的索引
     * @param right 右边有序数组的边界
     * @param temp 临时数组用于存储排序后的数组
     */
    public static void merge(int[]arr,int left,int mid, int right , int[] temp){
        int l = left;//左边有序数组的下标
        int r = mid +1;//右边有序数组的下标
        int tempIndex = 0;
        while (l <= mid && r <= right){
            //左右两边任意一边扫描完就结束
            if (arr[l]<= arr[r]){
                temp[tempIndex]=arr[l];
                tempIndex++;
                l++;
            }else {
                temp[tempIndex] = arr[r];
                tempIndex++;
                r++;
            }
        }
        while (l <= mid){
            temp[tempIndex]=arr[l];
            tempIndex++;
            l++;
        }
        while ( r <= right){
            temp[tempIndex] = arr[r];
            tempIndex++;
            r++;
        }
        l = left;
        for (int i = 0; i < tempIndex; i++,l++) {
            arr[l] = temp[i];
        }
    }
}
