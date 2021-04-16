package SeqSearch;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 1, 6, 7};
        int index = seqSearch(arr, 5);
        System.out.println("index=" + index);
    }

    /**
     * 线性查找法，在arr数组中查找key，找到了该值，返回key的下标，没有找到返回-1
     *线性查找法，可以对有序和无序均可查找，时间复杂度O(n)
     * @param arr 查找数组
     * @param key 值
     * @return
     */
    public static int seqSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {//遍历数组进行查找
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
