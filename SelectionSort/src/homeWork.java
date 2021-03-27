public class homeWork {
    private homeWork(){

    }
    public static void sort(int[]nums){
        for (int i=nums.length-1;i>-1;i--){
            int maxIndex=i;
            for (int j = i; j >-1 ; j--) {
                if (nums[j]>nums[maxIndex])
                    maxIndex=j;
            }
            int t=nums[i];
            nums[i]=nums[maxIndex];
            nums[maxIndex]=t;
        }
    }

    public static void main(String[] args) {
        int[]nums={5,2,4,1,3,6};
        sort(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
    }
}
