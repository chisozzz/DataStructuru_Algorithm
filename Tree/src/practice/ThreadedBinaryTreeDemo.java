package practice;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedNode root = new ThreadedNode(1, 1);
        ThreadedNode node2 = new ThreadedNode(2, 3);
        ThreadedNode node3 = new ThreadedNode(3, 6);
        ThreadedNode node4 = new ThreadedNode(4, 8);
        ThreadedNode node5 = new ThreadedNode(5, 10);
        ThreadedNode node6 = new ThreadedNode(6, 14);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.infixThreadedNodes();
        threadedBinaryTree.ThreadedList();
    }
}
class ThreadedBinaryTree{
    private ThreadedNode root;
    private ThreadedNode pre;//前驱结点默认开始为null

    public ThreadedBinaryTree(ThreadedNode root) {
        this.root = root;
    }

    //中序线索化二叉树的编写
    public void infixThreadedNodes() {
        if (root ==null){
            System.out.println("二叉树为空，无法线索化");
            return;
        }
        infixThreadedNodes(root);
    }

    //中序线索化二叉树的编写
    private void infixThreadedNodes(ThreadedNode node){
        //递归的结束条件，node为空，因为pre前驱指针的作用，pre和当前结点node
        //两个结点就可以处理前驱和后继的关系指向，我们只需要处理node这个结点就行
        if (node == null) {
            return;
        }
        //先线索化左子树
        infixThreadedNodes(node.getLeft());
        //处理当前结点
        //当该node左边为null，可以指向前驱结点，将左指针指向pre，设置类型为1
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //当pre不为null，说明前驱存在，同时pre的右指针为null，node作为后继才能让pre指向他
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //！！！当完成以上的前驱和后继处理，将前驱pre下移到node上，完成pre前驱的移动，node的移动由递归node.getLeft产生
        pre=node;
        //再线索化右子树
        infixThreadedNodes(node.getRight());
    }

    //前序线索化二叉树的编写
    public void preThreadedNode() {
        preThreadedNode(root);
    }

    //前序线索化二叉树的编写
    private void preThreadedNode(ThreadedNode node){
        //当前结点为null，则结束循环
        if (node == null){
            return;
        }

        //先处理当前结点的前驱和后继
        if (node.getLeft()==null){//如果当前结点的左指针为null，则让左指针指向pre作为node的前驱，设置类型为1
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre !=null && pre.getRight()==null){//如果pre不为null，前驱存在且pre的右指针为null，
            pre.setRight(node);//则让pre右指针指向当前结点，作为后继，设置类型为1
            pre.setRightType(1);
        }
        //让pre后移，指向node，而node通过递归后移
        pre=node;
        //左结点递归线索化
        preThreadedNode(node.getLeft());
        //右节点递归线索化
        preThreadedNode(node.getRight());
    }

    //后序线索化二叉树
    public void postThreadedNode() {
        postThreadedNode(root);
    }

    //后序线索化二叉树
    private void postThreadedNode(ThreadedNode node){
        if (node == null){
            return;
        }
        postThreadedNode(node.getLeft());
        postThreadedNode(node.getRight());
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
    }
    //遍历线索化二叉树
    public void ThreadedList(){
        ThreadedNode node = root;//从root结点开始遍历，按照线索化方式线性遍历
        while (node!=null){//该节点不为null，一直循环
            while (node.getLeftType()==0){//寻找第一个leftType为1的结点的，也就是线索化的第一个结点
                node=node.getLeft();
            }
            //输出该结点
            System.out.println(node);
            //如果该结点有后继结点，一直后移输出，线性的
            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            //倒数第二个结点需要再后移指向倒数第一个结点
            node=node.getRight();
        }
    }
}

class ThreadedNode{
    private int id;//结点的标志
    private int value;//结点的内容
    private ThreadedNode left;//默认为null
    private ThreadedNode right;//默认为null
    //线索化二叉树后，对于left和right指向的类型就有子树和前驱的区别
    private int leftType;//leftType的0是指向左子树，1是指向前驱结点
    private int rightType;//rightType的0是指向右子树，1是指向后继结点
    public ThreadedNode(int id, int value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ThreadedNode{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}