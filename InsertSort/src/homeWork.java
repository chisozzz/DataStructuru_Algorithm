public class homeWork {
    private homeWork(){}
    public static <E extends Comparable<E>> void sort(E [] nums){
        for(int i=nums.length-1;i>-1;i--){
            E temp=nums[i];
            int j;
            //从小到大排序，需要右派的时候当左边大于右边的时候，右移，j++，也就是找最大值
            for (j=i;j+1< nums.length && temp.compareTo(nums[j+1])>0;j++)
                nums[j]=nums[j+1];
            nums[j]=temp;
        }

    }

    public static void main(String[] args) {
        Integer[]nums={2,3,5,1,6,4};
        sort(nums);
        for (Integer num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
//        int x=2;
//        System.out.printf("输出的是%d\n",x);
    }
}
