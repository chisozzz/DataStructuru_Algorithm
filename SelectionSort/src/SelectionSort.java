public class SelectionSort {
    private SelectionSort() {
    }
    //泛型方法,使用排序，对象需要可比较性，不可比较，不能排序
    public static <E extends Comparable<E>> void sort(E[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j].compareTo(nums[minIndex]) < 0)
                    minIndex = j;
            }
            swap(nums, i, minIndex);
        }
    }

    private static <E> void swap(E[] nums, int i, int minIndex) {
        E t = nums[i];
        nums[i] = nums[minIndex];
        nums[minIndex] = t;
    }

    //测试
    public static void main(String[] args) {
        Integer[] nums = {6, 2, 3, 5, 1, 4};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
