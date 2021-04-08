package practice;

public class RecursionDemo {
    public static void main(String[] args) {
        //测试打印递归
        printRecursion(4);//预测打印从2开始到4
        //测试阶乘递归
        int res = mulRecursion(5);//计算5！的值
        System.out.println("res = " + res);
    }
    //打印递归问题,递归就是重复规律性的事情，逻辑不能出错，需要结束条件和趋向结束条件的变化
    public static void printRecursion(int n){
        if (n > 2){//调用方法本身就是递归，而参数的变化方向朝结束方向，不是死归
            printRecursion(n -1);
        }
        System.out.println("n = " + n);
    }
    //阶乘问题
    public static int mulRecursion(int n){
        if (n == 1){//这是结束条件，而n-1是趋向于1，不是死归，每次减一后乘，阶乘递归
            return 1;
        }else {
            return mulRecursion(n - 1) * n;
        }
    }
}
