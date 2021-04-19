package practice;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        arrBinaryTree.infixOrder();
//        arrBinaryTree.postOrder();
        int res = arrBinaryTree.preOrderSearch(8);
        System.out.printf("%d 的下标是 %d\n",8,res);
    }
}

//创建一个顺序存储二叉树，对数组的下标进行顺序存储，使用的遍历方式是二叉树，
//可以将该数组抽象成二叉树，因为算法的性质就是二叉树
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历的重载形式
    public void preOrder() {
        preOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //先输出当前结点
        System.out.println(arr[index]);
        //判断左节点是否存在，左递归前序遍历
        if ((2 * index) + 1 < arr.length) {
            preOrder((2 * index) + 1);
        }
        //判断右节点，右递归前序遍历
        if ((2 * index) + 2 < arr.length) {
            preOrder((2 * index) + 2);
        }
    }

    //前序遍历查找,找到了返回下标，没有返回-1
    public int preOrderSearch(int n) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return -1;
        }
        return preOrderSearch(n, 0);
    }

    //前序遍历查找,找到了返回下标，没有返回-1
    private int preOrderSearch(int key, int n) {
        int res = -1;
        //遍历开始的节点
        if (arr[n] == key) {
            return n;
        }
        if ((2 * n + 1) < arr.length) {
            res = preOrderSearch(key, 2 * n + 1);
        }
        if (res != -1) {
            return res;
        }
        if ((2 * n + 2) < arr.length) {
            res = preOrderSearch(key, 2 * n + 2);
        }
        return res;
    }

    //中序遍历
    public void infixOrder() {
        infixOrder(0);
    }

    //中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //先判断左节点，进行左递归中序遍历
        if ((2 * index) + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前结点
        System.out.println(arr[index]);
        //再判断右节点，进行右递归中序遍历
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //后序遍历
    public void postOrder() {
        postOrder(0);
    }

    //后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //先判断左节点，进行左递归后序遍历
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        //再判断右节点，进行右递归后序遍历
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前结点信息
        System.out.println(arr[index]);
    }
}