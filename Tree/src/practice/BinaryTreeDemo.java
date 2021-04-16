package practice;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree(node1);

        System.out.println("前序遍历：");//1,2,3,5,4
        binaryTree.preOrder();
        System.out.println("中序遍历：");//2,1,5,3,4
        binaryTree.infixOrder();
        System.out.println("后序遍历：");//2,5,4,3,1
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private Node root;//默认为空

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    //删除节点
    public void del(int no){
        if (root != null) {
            if (root.getId()==no){
                root = null;
            }else {
                root.del(no);
            }
        }else {
            System.out.println("二叉树为空，无法删除");
        }
    }
    //前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}

/**
 * 这是二叉树的节点结构
 * id,name是对象数据
 * left 是左子节点
 * right 右子结点
 */
class Node {
    private int id;
    private String name;
    private Node left;//默认null
    private Node right;//默认null

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
    //删除节点
    /**
     * 规定：如果节点是叶子节点，则直接删除
     * 如果节点不是叶子节点，则将以该节点的子树删除
     */
    public void del(int no){
        //判断当前节点的左节点不为空，且是否id为no
        if (this.left != null && this.left.id==no){
            this.left = null;
        }
        if (this.right !=null && this.right.id == no){
            this.right =null;
        }
        if (this.left !=null){
            this.left.del(no);
        }
        if (this.right != null){
            this.right.del(no);
        }
    }
    //前序遍历
    public void preOrder() {
        //先输出当前节点
        System.out.println(this);
        //在判断当前节点左节点是否为空，不为空，左递归前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //在判断当前节点右节点是否为空，不为空，右递归前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //先判断当前节点的左子节点是否为空，不为空，左递归中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出当前节点
        System.out.println(this);
        //先判断当前节点的右子节点是否为空，不为空，右递归中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }

    }
    //后序遍历

    public void postOrder() {
        //先判断当前节点的左子节点是否为空，不为空，左递归中序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //先判断当前节点的右子节点是否为空，不为空，右递归中序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出当前节点
        System.out.println(this);
    }
    //前序查找节点
    public Node preOrderSearch(int no){
        //先判断当前节点是否为no
        if (this.id == no){
            return this;
        }
        //递归判断当前节点的左子节点是否为no，返回tempNode
        Node tempNode=null;
        if (this.left != null){
            tempNode = preOrderSearch(no);
        }
        //判断tempNode是否为空，不为空，证明找到了，返回tempNode
        if (tempNode != null){
            return tempNode;
        }
        //左递归没有找到，则右递归查找，返回tempNode
        if (this.right != null){
            tempNode=preOrderSearch(no);
        }
        return tempNode;
    }
    //中序查找节点
    public Node infixOrderSearch(int no){
        Node tempNode = null;
        if (this.left !=null){
            tempNode = infixOrderSearch(no);
        }
        if (tempNode != null){
            return tempNode;
        }
        if (this.id == no){
            return this;
        }
        if (this.right != null){
            tempNode = infixOrderSearch(no);
        }
        return tempNode;
    }
    //后序查找节点
    public Node postOrderSearch(int no){
        Node tempNode = null;
        if (this.left != null){
            tempNode = postOrderSearch(no);
        }
        if (tempNode != null){
            return tempNode;
        }
        if (this.right !=null){
            tempNode = postOrderSearch(no);
        }
        if (tempNode != null){
            return tempNode;
        }
        if (this.id == no){
            return this;
        }
        return tempNode;
    }
}
