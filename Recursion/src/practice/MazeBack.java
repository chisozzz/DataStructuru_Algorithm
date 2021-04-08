package practice;

//迷宫回溯，递归实现的方式
public class MazeBack {
    public static void main(String[] args) {
        int[][] maze1 = createMaze(8, 7);
        int[][] maze2 = createMaze(8, 7);
        int[][] maze3 = createMaze(8, 7);
        int[][] maze4 = createMaze(8, 7);
//        设置挡板
        maze1[3][1] = 1;
        maze1[3][2] = 1;
        maze2[3][1] = 1;
        maze2[3][2] = 1;
        maze3[3][1] = 1;
        maze3[3][2] = 1;
        maze4[3][1] = 1;
        maze4[3][2] = 1;
//        System.out.println("迷宫的地图：");
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(maze[i][j] + " ");
//            }
//            System.out.println();
//        }
        //迷宫的最短路径问题，现在只能根据不同寻路方式来判断，那个最短
        mazeSolution1(maze1, 1, 1);
        mazeSolution2(maze2, 1, 1);
        mazeSolution3(maze3, 1, 1);
        mazeSolution4(maze4, 1, 1);
        int step1 = countStep(maze1);
        int step2 = countStep(maze2);
        int step3 = countStep(maze3);
        int step4 = countStep(maze4);
        System.out.println("迷宫的寻路地图不同策略的步长：");
        System.out.println(step1+" "+ step2+" "+step3+" "+ step4+" ");

    }

    //对不同寻路方式的结果进行步数比较
    public static int countStep(int[][] maze) {
        int count = 0;
        for (int[] ints : maze) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    count++;
                }
            }
        }
        return count;
    }

    //创造一个i行j列的迷宫，迷宫最外面一圈是墙，1是墙，0是通道
    public static int[][] createMaze(int i, int j) {
        int[][] maze = new int[i][j];
        //给最外围上墙
        for (int k = 0; k < j; k++) {
            maze[0][k] = 1;
            maze[i - 1][k] = 1;
        }
        for (int k = 0; k < i; k++) {
            maze[k][0] = 1;
            maze[k][j - 1] = 1;
        }
        return maze;
    }
    //迷宫回溯算法：参数 地图 和 起点 i，j
    //规律是从起点开始探路，下右上左，找路，是0就继续找路，是1 墙，2 通路，3 死路，换方向找路，4个方向都找完了找不到
    //这个点是死路，设为3，每到一个点设为通路，默认为通路，开始找，每到一点就找路循环，直到找到了终点

    //找路策略：下右上左
    public static boolean mazeSolution1(int[][] maze, int i, int j) {
        //结束条件,走到了终点，也就是终点为2,存在解
        if (maze[6][5] == 2) {
            return true;
        } else {
            if (maze[i][j] == 0) {
                //没有走到，就得找路了,起点开始找
                maze[i][j] = 2;
                if (mazeSolution1(maze, i + 1, j)) {
                    //向下可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution1(maze, i, j + 1)) {
                    //向右可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution1(maze, i - 1, j)) {
                    //向上可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution1(maze, i, j - 1)) {
                    //向左可以走,走到下个点循环找路
                    return true;
                } else {
                    //此路为死路，这个点为3，找路结果为false
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                //是1,2,3则是走过的，这步寻路是错的
                return false;
            }
        }
    }

    //找路策略：上右下左
    public static boolean mazeSolution2(int[][] maze, int i, int j) {
        //结束条件,走到了终点，也就是终点为2,存在解
        if (maze[6][5] == 2) {
            return true;
        } else {
            if (maze[i][j] == 0) {
                //没有走到，就得找路了,起点开始找
                maze[i][j] = 2;
                if (mazeSolution2(maze, i - 1, j)) {
                    return true;
                } else if (mazeSolution2(maze, i, j + 1)) {
                    //向右可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution2(maze, i + 1, j)) {
                    return true;
                } else if (mazeSolution2(maze, i, j - 1)) {
                    //向左可以走,走到下个点循环找路
                    return true;
                } else {
                    //此路为死路，这个点为3，找路结果为false
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                //是1,2,3则是走过的，这步寻路是错的
                return false;
            }
        }
    }

    //找路策略：右下左上
    public static boolean mazeSolution3(int[][] maze, int i, int j) {
        //结束条件,走到了终点，也就是终点为2,存在解
        if (maze[6][5] == 2) {
            return true;
        } else {
            if (maze[i][j] == 0) {
                //没有走到，就得找路了,起点开始找
                maze[i][j] = 2;
                if (mazeSolution3(maze, i, j + 1)) {
                    //向下可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution3(maze, i + 1, j)) {
                    //向右可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution3(maze, i, j - 1)) {
                    //向上可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution3(maze, i - 1, j)) {
                    //向左可以走,走到下个点循环找路
                    return true;
                } else {
                    //此路为死路，这个点为3，找路结果为false
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                //是1,2,3则是走过的，这步寻路是错的
                return false;
            }
        }
    }

    //找路策略：左上右下
    public static boolean mazeSolution4(int[][] maze, int i, int j) {
        //结束条件,走到了终点，也就是终点为2,存在解
        if (maze[6][5] == 2) {
            return true;
        } else {
            if (maze[i][j] == 0) {
                //没有走到，就得找路了,起点开始找
                maze[i][j] = 2;
                if (mazeSolution4(maze, i, j - 1)) {
                    //向下可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution4(maze, i - 1, j)) {
                    //向右可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution4(maze, i, j + 1)) {
                    //向上可以走,走到下个点循环找路
                    return true;
                } else if (mazeSolution4(maze, i + 1, j)) {
                    //向左可以走,走到下个点循环找路
                    return true;
                } else {
                    //此路为死路，这个点为3，找路结果为false
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                //是1,2,3则是走过的，这步寻路是错的
                return false;
            }
        }
    }
}
