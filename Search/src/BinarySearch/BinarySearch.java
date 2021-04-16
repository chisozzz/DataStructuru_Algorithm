package BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,1,1,1, 4, 6, 7, 8, 9, 22, 88, 123, 126};
//        int index = binarySearch(arr, 0, arr.length - 1, 5);
        List index = binarySearch2(arr,0,arr.length-1,1);
        System.out.println("index=" + index);
    }

    /**
     * 二分查找法的递归写法，结束条件是left>right，就表名遍历了整个数组，没有找到key值
     * 二分查找法只能对有序的数组进行查找，不能对无序数组查找，
     *
     * @param arr   查找的数组范围
     * @param left  左索引
     * @param right 右索引
     * @param key
     * @return 找到了返回该值的下标，没有找到则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int key) {
        //递归结束条件，最坏情况下left与right指向同一元素，不相等，开始下一个递归，left向右移，right向左移动
        //使得left>right
        if (left > right) {
            return -1;
        }
        //二分查找的关键，mid的划分是左和右的中间
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (key > midValue) {//key可能在mid的右边，向右递归,左边界是mid+1
            return binarySearch(arr, mid + 1, right, key);
        } else if (key < midValue) {//key可能在mid的左边，向左递归,右边界是mid-1
            return binarySearch(arr, left, mid - 1, key);
        } else {//key==midValue，key值找到了
            return mid;
        }
    }
    //在arr中查找值key，返回包含下标的List集合，没有key值则返回空，
    public static List<Integer> binarySearch2(int[] arr,int left,int right, int key){
        if (left > right){
            return null;
        }
        int mid = (left+right)/2;
        int midValue=arr[mid];
        if (key > midValue){
            return binarySearch2(arr,mid+1,right,key);
        }else if (key < midValue){
            return binarySearch2(arr,left,mid-1,key);
        }else {
            //当第一次找到该值下标mid，需要向左和向右扫描，是否有重复值
            List<Integer> list = new ArrayList<>();
            int l=mid;//l是左索引
            while (true){//向左扫描
                if (l < 0 || arr[l] != key) {
                    break;
                }
                l--;
            }
            int r=mid+1;//r是右索引
            while (true){//向右扫描
                if (r>arr.length-1 || arr[r] !=key){
                    break;
                }
                r++;
            }
            //左右索引指向重复值，lr之间扫描,添加
            for (int i = l+1; i <r ; i++) {
                list.add(i);
            }
            return list;
        }
    }
}
