package practice;
//数组的经典处理
public class array {
    //找出数组中的最大值
    public static int findMax(int[]a){
        int max=a[0];
        int len = a.length;
        for (int i = 0; i <len ; i++) {
            if (max<a[i])max=a[i];
        }
        return max;
    }
    //计算数组中的平均值
    public static double avg(int []a){
        int sum=0;
        int len=a.length;
        for (int i=0;i<len;i++  ){
            sum+=a[i];
        }
        return sum/len *1.0;
    }
    //复制数组
    public static int[] copy(int[]a){
        int len=a.length;
        int[] b=new int[len];
        for (int i=0;i<len;i++)
            b[i]=a[i];
        return b;
    }
    //颠倒数组元素的顺序
    public static void reverse(int []a){
        int len=a.length;
        for (int i=0;i<len/2;i++){
            int temp=a[i];
            a[i]=a[len-1-i];
            a[len-1-i]=temp;
        }
    }

    public static void main(String[] args) {
        int []a={1,2,3,4};
        int max = findMax(a);
        double avg = avg(a);
        int[] b = copy(a);
        reverse(a);
        for (int i : a) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
