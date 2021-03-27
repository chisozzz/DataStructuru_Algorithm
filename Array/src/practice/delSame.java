package practice;
//删除排序数组中的重复项
//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
//不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
//注意是排好序的数组，也就是说相同的数在一起，我们只需要将第一个不同的数放置到前面数的后面就可以了
public class delSame {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(del(nums));
    }

    public static int del(int[] nums) {
        if (nums.length==0)return 0;
        int i=0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        for (int j = 0; j < i+1; j++) {
            System.out.print(nums[j]);
        }
        return i+1;
    }
}
