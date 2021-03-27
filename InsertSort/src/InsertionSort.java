public class InsertionSort {
    private InsertionSort(){}

    //插入排序法,双指针，i为慢，j为快，如果j指向的数比他左边小，则两者交换，从小到大排序,保持i左边的数组永远是
    //有序的数组，但是不一定是最后的位置，只是在[0,i)是有序的，反观选择排序法，从[0,i)排好的位置，都是最终的位置
    //优化后的插入排序
    public static <E extends Comparable<E>> void sort(E []nums){
//        for (int i = 1; i < nums.length; i++) {
//            E temp=nums[i];int index=i;
//            for (int j = i; j-1 >= 0; j--) {
//                if (nums[j-1].compareTo(temp)>0){
//                   nums[j]= nums[j-1];
//                   index=j-1;
//                }
//            }
//            nums[index]=temp;
//        }

        //另一种写法，时间复杂度O(n^2)
        for (int i = 0; i < nums.length; i++) {
            E temp=nums[i];
            int j;
            //j即使循环的条件也是插入的位置，条件就是j-1是0之前的且temp的值比左边小，j就往左移动，数值也要移动
            for (j=i;j-1>=0 &&temp.compareTo(nums[j-1])<0;j--)
                nums[j]=nums[j-1];
            //当j找到左边最小的位置，将temp付给j位置就可以了
            nums[j]=temp;
        }

    }

    public static void main(String[] args) {
        Integer[]nums={2,4,1,6,5,3};
        sort(nums);
        for (int num : nums) {
            System.out.print(num +" ");
        }
        System.out.println();
    }
}
