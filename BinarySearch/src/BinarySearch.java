import java.util.Arrays;

public class BinarySearch {
    private BinarySearch() {
    }

    //一般形式，返回下标
    public static int sort(int key, int[] a) {
        //二分查找，数组必须是有序的，lo，hi是两个指针，
        int lo = 0;
        int hi = a.length - 1;
        //时间复杂度是O(n)级别
        while (hi <= lo) {
            //key要么不存在，要么一定在[lo,hi]中，保持[lo,hi]循环不变量
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    //递归形式
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    //重载方式，参数不同
    private static int rank(int key, int[] a, int lo, int hi) {
        //递归的结束条件
        if (lo > hi) return -1;
        //临时指针，每次是根据lo和hi生成，
        int mid = lo + (hi - lo) / 2;
        if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else if (key < a[mid]) return rank(key, a, lo, mid - 1);
        else return mid;
    }

    public static void main(String[] args) {
        int[] a = {9, 5, 1, 6, 2, 24, 67, 0, 4};
        //二分查找需要排序才能使用
        Arrays.sort(a);
        int result = sort(3, a);
        System.out.println(result);
    }
}
