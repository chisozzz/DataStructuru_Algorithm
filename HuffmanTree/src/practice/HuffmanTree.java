package practice;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanTree {
    public static void main(String[] args) {
        int[]arr={13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    /**
     * 将原始数组传化成霍夫曼树
     * @param arr 原始数组
     * @return  返回霍夫曼树的根节点
     */
    public static Node createHuffmanTree(int[]arr){
        if (arr ==null && arr.length ==0){
            System.out.println("数组为空，无法生成霍夫曼树");
            return null;
        }
        List<Node> nodes = getList(arr);
        //开始取最小权值的两个子树，合并成一个子树parent，直到nodes集合中只有一个结点就是根节点
        
        while (nodes.size()>1){
            //先将nodes从小到大排序，每次取出两个后，加入拼好的，顺序会改变，需要重新排序
            Collections.sort(nodes);
            //排序后，索引0,1是最小的两个数
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建父节点，权值是两数的和
            Node parent= new Node(leftNode.value+rightNode.value);
            //将两个子树拼接到父节点上
            parent.left=leftNode;
            parent.right=rightNode;
            //对list进行操作，出两个小的子树，加入合并后的树，直到只剩下一个结点就是霍夫曼树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //将数组的加入集合中
    private static List<Node> getList(int[]arr){
        List<Node> list=new ArrayList<>();
        for (int i : arr) {
            list.add(new Node(i));
        }
        return list;
    }
    //前序遍历霍夫曼树
    public static void preOrder(Node root){
        if (root != null) {
            System.out.println(root);
            if (root.left!=null){
                preOrder(root.left);
            }
            if (root.right != null) {
                preOrder(root.right);
            }
        }else {
            System.out.println("霍夫曼树为空，无法遍历");
        }
    }
}
//创建霍夫曼树的结点

class Node implements Comparable<Node>{
    public int value;//结点的权值
    public Node left;//左指针
    public Node right;//右指针

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大比较,升序排序
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}