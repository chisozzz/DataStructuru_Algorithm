package practice;

public class RecursionPractice {
    public static void main(String[] args) {
        test1(4);
        System.out.printf("%d! = %d",5,test2(5));
    }

    //第二次练习，打印递归
    public static void test1(int n){
        if (n > 2){//结束条件
            test1(n -1);//逼近结束
        }
        System.out.println("n = " + n);//实际操作

    }
    //第二次练习，阶乘递归
    public static int test2( int n){
        if (n == 1){//结束条件
            return 1;//结束操作
        }else {
            return test2(n -1) * n;//递归操作，逼近结束
        }
    }
    //再一次练习，迷宫解法
    public static boolean mazeStep(int[][] maze,int i ,int j){
        if (maze[6][5] == 2){//终点的状态为通路
            return true;
        }else {
            if (maze[i][j] ==0){
                maze[i][j] = 2;
                if (mazeStep(maze,i,j+1)){
                    return true;
                }else if (mazeStep(maze,i+1,j)){
                    return true;
                }else if (mazeStep(maze,i,j-1)){
                    return true;
                }else if (mazeStep(maze,i-1,j)){
                    return true;
                }else {
                    maze[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
