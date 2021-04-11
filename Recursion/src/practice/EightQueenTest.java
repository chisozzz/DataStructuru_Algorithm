package practice;

import java.util.Arrays;

public class EightQueenTest {
    private int Max = 8;
    private int[] board = new int[Max];
    private int count;

    public static void main(String[] args) {
        EightQueenTest queenTest = new EightQueenTest();
        queenTest.chess(0);
        System.out.printf("一共有 %d 的解法", queenTest.count);
    }
    public void chess(int n ){
        //n表示这是第几个皇后下棋,从0开始
        if (n == 8){//已经是第9个皇后了，前8个下完了
            //递归结束的操作
            print();
            return;
        }
        //开始进行下棋
        for (int i = 0; i < Max; i++) {
            //有多少列，尝试走多少次
            board[n] = i;//尝试下第i列
            if (judge(n)){
                //可以走这一步,尝试走下一个皇后
                chess(n+1);
            }
        }
    }
    public boolean judge(int n ){
        for (int i = 0; i < n; i++) {//第n个皇后前面的皇后，有无冲突
            if (board[i] == board[n] || Math.abs(i - n) == Math.abs(board[i] - board[n])){
                return false;
            }
        }
        return true;
    }

    public void print(){
        count++;
        System.out.println(Arrays.toString(board));
//        for (int i = 0; i < board.length; i++) {
//            System.out.print(board[i] + "");
//        }
//        System.out.println();
    }
}
