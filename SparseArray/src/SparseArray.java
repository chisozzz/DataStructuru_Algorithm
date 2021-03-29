import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个棋盘，使用二维数组
        int[][] chessArr1 = new int[11][11];
        //给2个位置赋值,默认是0，黑子是1，蓝子是2
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] =2;

        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        int sum = 0;
        //遍历chessArr数组，寻找与0不同数的个数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0)
                    sum += 1;
            }
        }
        //创建稀疏数组，行数是sum+1，列数是3，sum是不为0的个数，row，low，value
        int[][] sparseArr = new int[sum+1][ 3];
        //初始化稀疏数组的第一行
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //将原始二维数组的非0数的row，low，value输入稀疏数组
        int count=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //将稀疏数组打印
        System.out.println();
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组转换成原始二维数组
        //读取稀疏数组第一行的数据，创建原始二维数组
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        //读取第二行开始的非0数据的位置信息，保存到原始二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        //打印还原的数组
        System.out.println();
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组保存到文件中
        //将文件读取到内存，恢复稀疏数组
        /*FileWriter fw=new FileWriter(new File("map.txt"));
        for (int i = 0; i < sparseArr.length; i++) {
            fw.write(sparseArr[i][0]);
            fw.write(sparseArr[i][1]);
            fw.write(sparseArr[i][2]);
        }
        fw.close();
        FileReader fr=new FileReader(new File("map.txt"));
        char[] str=new char[1024];
        while ((fr.read(str))!=-1){
            System.out.println(str);
        }*/
    }
}
