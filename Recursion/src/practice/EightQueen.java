package practice;

public class EightQueen {
    //八皇后问题，规则：在一个8*8的棋盘中摆放8皇后，要求8个皇后不能互相攻击
    //不能互相攻击，换句话就是8个皇后任意两个之间不能处于同一行，同一列，同一斜线上
    //问题转化成数据对象，棋盘和皇后的位置变成一个一维数组，arr[0,4,3,4,2,3,8,6]长度代表皇后的个数，索引代表第i+1个
    //皇后，第i+1行，arr[i]代表第i+1行的第几列数，所以一个一维数组就可以表示皇后的位置和个数
    private int Max = 8;//8个皇后
    private int[] chessBoard = new int[Max];//棋盘
    private int count =0;//多少个解

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.chess(0);
        System.out.printf("一共有 %d 次解",queen.count);
    }
    //开始下棋，从第0行0列开始递归循环
    private void chess(int n ){
        //结束条件
        if (n == Max){
            print();
            return;
        }
        for (int i = 0; i < Max; i++) {

            chessBoard[n]=i;
            if (judge(n)){
                chess(n+1);
            }
        }

    }

    //判断是否能攻击
    private boolean judge(int n){
        //n是第n个皇后
        for (int i = 0; i < n ; i++) {
            //同一列，同一斜线，就是斜率为1 -1,绝对值为1
            if (chessBoard[n] == chessBoard[i] || Math.abs(n - i) == Math.abs(chessBoard[n] - chessBoard[i])) {
                return false;
            }
        }
        //全部满足才为真
        return true;
    }

    //将解法打印
    private void print(){
        count++;
        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print(chessBoard[i]+" ");
        }
        System.out.println();
    }
}
