public class ArrayGenertor {
    //数组生成器
    private ArrayGenertor() {
    }

    public static Integer[] product(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
