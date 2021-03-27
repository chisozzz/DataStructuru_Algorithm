package basic;

import java.util.Scanner;

public class HomeWork_1_1 {
    public static void main(String[] args) {
        //System.out.println((0+15)/2);
//        equal();
//        Scanner sc=new Scanner(System.in);
////        sc.useDelimiter(" ");
//        while (sc.hasNextInt())
//            System.out.println("键盘输入的是："+sc.nextInt());
        test4(88);
    }

    public static void test4(int n) {
        //将一个数转化为二进制的字符串
        String s = "";
        for (int i = n; i > 0; i /= 2)
            s = (i % 2) + s;
        System.out.println(s);
    }

    public static void test3() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < 1000; j++)
                sum++;
        }
        System.out.println(sum);
    }

    public static void test2() {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        System.out.printf("%.5f\n", t);
    }

    public static void test1() {
        int f = 0;
        int g = 1;
        //从第3位开始，后面的数是前面两数之和
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    //1.1.3习题
    public static void equal() {
        int[] a = new int[3];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        //填入非整数值停止输入
        while (sc.hasNextInt()) {
            a[i] = sc.nextInt();
            i++;
        }
        sc.close();
        if (a[0] == a[1] && a[0] == a[2])
            System.out.println("equal");
        else
            System.out.println("not equal");
    }
}
