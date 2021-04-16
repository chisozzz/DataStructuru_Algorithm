package InsertSearch;

public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int res = insertSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index=" + res);
    }

    public static int insertSearch(int[] arr, int left, int right, int key) {
//        System.out.println("插值查找法~~");
        //插值查找法,判断key的值大小是必须的，因为key值参与了mid的取值运算
        //如果key过大或过小，mid的值就是让数组存在越界的问题
        if (left > right || arr[0] > key || arr[arr.length - 1] < key) {
            return -1;
        }
        //(key - arr[left]) / (arr[right] - arr[left])就是自适应的斜率取值，两点的差的比
        int mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (key > midValue) {
            return insertSearch(arr, mid + 1, right, key);
        } else if (key < midValue) {
            return insertSearch(arr, left, mid - 1, key);
        } else {
            return mid;
        }
    }
}
