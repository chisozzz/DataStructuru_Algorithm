package practice;


public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[]arr={7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        binarySortTree.add(new Node(2));
        System.out.println("生成的二叉排序树为：");
        binarySortTree.infixOrder();
        System.out.println("删除后的二叉排序树为：");
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(2);
        binarySortTree.infixOrder();
    }
}
class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }
    //删除node为根节点的子树下最大的数并返回它的值
    public int delLeftMax(Node node){
        if (node.right!=null){
            return delLeftMax(node.right);
        }else {
            int temp=node.value;
            delNode(node.value);
            return temp;
        }
    }
    //删除node为根节点的子树下最小的数并返回它的值
    public int delRightMin(Node node){
        if (node.left!=null){
            return delRightMin(node.left);
        }else {
            int temp = node.value;
            delNode(node.value);
            return temp;
        }
    }
    //删除节点,通过需要删除的值进行查找结点进行删除
    /*
    * 思路分析：
    * 先找到需要删除的结点target，如果节点为空，直接返回
    * 再找到需要删除结点的父结点parent，没有找到，返回为空
    * 对于删除的结点有三种：叶子结点，带左右子节点的结点，带一颗子树的节点
    * 叶子结点：
    * 父结点为空，则该结点为根节点
    * 父结点不为空，则判断该结点是父结点的左子节点还是右子结点，左节点：parent.left=null
    * 带一颗子树的结点：
    *   父结点为空，则该结点为根节点,对根节点进行父结点存在时的操作
    *   父结点不为空，则判断该节点是父结点的左子节点还右子结点
    * 父结点的左子结点：
    *   判断该结点左子树存在：parent.left=target.left;
    *   判断该结点右子树存在：parent.left=target.right;
    * 父结点的右子结点：
    *   该结点左子树存在：parent.right=target.left;
    *   该结点右子树存在：parent.right=target.right;
    * 带左右子结点的结点：
    * 找到target右子树的最小结点，删除该结点，并将该结点的值赋给target
    * 或者找到target左子树的最大结点，删除该结点，并将该结点的值赋给target
    * 二者选一种方式实现
    * */
    public void delNode(int value){
        if (root == null) {

        }else {
            Node target = root.searchTarget(value);
            if (target == null){

            }else {
                Node parent = root.searchParent(value);
                if (target.left==null && target.right == null){//查找的结点是叶子结点
                    if (parent == null){
                        root=null;
                    }else {
                        if (parent.left.value == target.value){
                            parent.left=null;
                        }else {
                            parent.right=null;
                        }
                    }
                }else if (target.left !=null && target.right!=null){//查找的结点有左右子树
                    target.value= delRightMin(target.right);
//                    target.value= delLeftMax(target.left);
                }else {//查找的结点只有一颗子树
                    if (parent == null){//target是根节点，
                        if (target.left!=null){
                            root=target.left;
                        }else {
                            root=target.right;
                        }
                    }else {//不是根节点
                        if (parent.left.value == target.value){
                            if (target.left!=null){
                                parent.left=target.left;
                            }else {
                                parent.left = target.right;
                            }
                        }else {
                            if (target.left!=null){
                                parent.right=target.left;
                            }else {
                                parent.right=target.right;
                            }
                        }
                    }
                }
            }
        }
    }
    public Node searchParent(int value){
        if (root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }
    public Node searchTarget(int value){
        if (root==null){
            return null;
        }else {
           return root.searchTarget(value);
        }
    }
    //添加节点
    public void add(Node node){
        if (root!=null){
            root.add(node);
        }else {
            root=node;
        }
    }
    //中序遍历
    public void infixOrder(){
        if (root == null) {
            System.out.println("二叉排序树为空，无法遍历");
            return;
        }
        root.infixOrder();
    }
}

class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找需要删除的父结点，没有父结点则放回null，一个该结点是根节点，一个是没有该结点
    public Node searchParent(int value){
        if (this.value == value){
            return null;
        }else if (value>this.value){
            if (this.right==null){
                return null;
            }else {
                 if (value==this.right.value){
                    return this;
                }else{
                    return this.right.searchParent(value);
                }
            }
        }else {
            if (this.left==null){
                return null;
            }else {
                if (value==this.left.value){
                    return this;
                }else{
                    return this.left.searchParent(value);
                }
            }
        }
    }
    //查找需要删除的结点,没有则返回null
    public Node searchTarget(int value){
        if (value== this.value){
            return this;
        }
        if (value < this.value) {
            if (this.left == null) {
                return null;
            }else {
                return this.left.searchTarget(value);
            }
        }else {
            if (this.right ==null){
                return null;
            }else {
                return this.right.searchTarget(value);
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        //左节点不为空，则继续向左查看
        if (this.left!=null){
            this.left.infixOrder();
        }
        //已经是最左了，输出当前结点信息，Node重写toString方法
        System.out.println(this);
        //右结点不为空，右递归查看
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //添加
    public void add(Node node){
        if (node==null){//添加的结点为空，直接返回
            System.out.println("添加的结点为空，添加失败");
            return;
        }
        if (node.value < value) {//添加的结点的值小于当前结点
            if (left==null){//判断当前结点是否有左节点，
                left=node;//没有，直接添加节点
            }else {
                left.add(node);//有左结点，则继续向左递归添加
            }
        } else{//添加的结点的值大于当前结点
            if (right==null){//右结点为空，直接添加node
                right=node;
            }else {//右结点存在，则开始向右递归比较和添加
                right.add(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Node[" + "value=" + value + ']';
    }
}
