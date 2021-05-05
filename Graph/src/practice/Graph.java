package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//邻接矩阵表示图
public class Graph {
    public ArrayList<String> vertexes;//图的顶点集合
    public int[][] edges;//二维数组表示图的边和权
    public int numsOfEdges;//边的个数

    public Graph(int n) {//初始化图，n表示图顶点个数
        vertexes = new ArrayList<>(n);
        edges = new int[n][n];
        numsOfEdges = 0;
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexes.add(vertex);//加入顶点集合

    }

    //插入边，设置边的权，0表示没有直接连接，非0表示权,1表示有直接连接
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;//顶点v1和v2的边设置权weight
        edges[v2][v1] = weight;//顶点v2和v1的边设置权weight
        numsOfEdges++;
    }

    //获取顶点个数
    public int getNumsOfVertexes() {
        return vertexes.size();
    }

    //获取边的个数
    public int getNumsOfEdges() {
        return numsOfEdges;
    }

    //获取某个边的权
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //通过索引获取顶点的值
    public String getVertex(int index) {
        return vertexes.get(index);
    }

    //展示整个矩阵
    public void show() {
        for (int[] vertex : edges) {
            System.out.println(Arrays.toString(vertex));
        }
    }

    public int getFirstNeighbor(int index) {//查找index顶点的第一个邻接顶点，返回索引
        for (int j = 0; j < getNumsOfVertexes(); j++) {
            if (edges[index][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int index, int second) {//查找index顶点的第second的邻接节点，返回索引
        for (int j = second + 1; j < getNumsOfVertexes(); j++) {
            if (edges[index][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    //图的深度优先遍历
    private void dfs(boolean[] isVisited, int n) {
        //先将当前结点输出并改变已遍历状态
        System.out.print(getVertex(n) + "->");
        isVisited[n] = true;
        int firstNeighbor = getFirstNeighbor(n);//获取第一个邻接结点
        while (firstNeighbor != -1) {
            //第一个邻接结点存在
            if (!isVisited[firstNeighbor]) {//没有访问过
                dfs(isVisited, firstNeighbor);//继续dfs遍历
            }
            firstNeighbor = getNextNeighbor(n, firstNeighbor);//访问过了找下一个邻接结点，继续遍历
        }
    }

    public void dfs() {
        boolean[] isVisited = new boolean[getNumsOfVertexes()];//用于遍历的状态
        for (int i = 0; i < getNumsOfVertexes(); i++) {//将每个结点都遍历
            if (!isVisited[i]) {//结点未遍历了
                dfs(isVisited, i);
            }
        }
    }

    //图的广度优先遍历
    public void bfs() {
        boolean[] isVisited = new boolean[getNumsOfVertexes()];
        for (int i = 0; i < getNumsOfVertexes(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //图的广度优先遍历
    private void bfs(boolean[] isVisited, int n) {
        //先将当前结点输出并改变已遍历状态
        System.out.print(getVertex(n) + "->");
        isVisited[n] = true;
        //创建一个队列保存已访问过结点的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //将已访问过的节点入队
        queue.addLast(n);
        while (!queue.isEmpty()) {//队列不为空
            Integer u = queue.removeFirst();//出队列，获取对头的节点
            int w = getFirstNeighbor(u);//查找邻接结点
            while (w != -1) {//结点存在
                if (!isVisited[w]) {//未访问过
                    //先将当前结点输出并改变已遍历状态
                    System.out.print(getVertex(w) + "->");
                    isVisited[w] = true;
                    //将已访问过的节点入队
                    queue.addLast(w);
                }
                //已访问过,获取下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public static void main(String[] args) {
        //测试图的创建
        Graph graph = new Graph(5);
        String[] vertex = {"A", "B", "C", "D", "E"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        System.out.println("图的邻接矩阵：");
        graph.show();
        System.out.println("深度优先遍历：");
        graph.dfs();
        System.out.println("广度优先搜索：");
        graph.bfs();
    }
}
