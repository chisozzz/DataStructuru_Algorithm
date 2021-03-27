package answer;

//选择排序法
public class arraySearch {
    public static void main(String[] args) {
        int[]nums={6,2,3,5,1,4};
        for (int i=0;i<nums.length;i++){
            //minIndex是最小值的下标，值就是nums[minIndex]最小
            int minIndex=i;
            for (int j = i; j < nums.length; j++) {
                //当nums[j]的值比nums[minIndex]小，说明nums[minIndex]不是最小，j下标才是最小
                if (nums[j]<nums[minIndex])
                    minIndex=j;
            }
            int temp=nums[i];
            nums[i]=nums[minIndex];
            nums[minIndex]=temp;
        }
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
    }
}
