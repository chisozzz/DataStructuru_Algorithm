package practice;

//旋转数组
public class rotate {
    public static void main(String[] args) {
        int [] nums={1,2,3,4,5,6,7};
        rotateNums(nums,3);
        for (int num : nums) {
            System.out.println(num);
        }
    }
    public static void rotateNums(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int temp=nums[nums.length-1];
            for (int j = nums.length-2; j >-1 ; j--) {
                nums[j+1]=nums[j];
            }
            nums[0]=temp;
        }
    }
}
