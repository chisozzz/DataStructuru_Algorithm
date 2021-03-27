package practice;
//两数之和
// 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
//你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//

public class sum {
    public static void main(String[] args) {
        int []nums={2,7,11,15};
        int[] sum = twoSum(nums, 9);
        for (int i : sum) {
            System.out.println(i);
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        int[]sum =new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j]+nums[i]==target){
                    sum[0]=i;
                    sum[1]=j;
                    break;
                }

            }
        }
        return sum;
    }
}
