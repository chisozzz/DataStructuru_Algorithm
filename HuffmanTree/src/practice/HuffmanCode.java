package practice;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str="i like like like java do you like a java";
//        List<byteNode> charMap = getCharMap(str);
//        byteNode huffmanTree = createHuffmanTree(charMap);
//        preOrder(huffmanTree);
//        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTree);
//        System.out.println(huffmanCodes);
        byte[] zip = zip(str);
        System.out.println(Arrays.toString(zip));
    }

    public static byte[] zip(String str) {
        List<byteNode> charMap = getList(str);
        byteNode huffmanTree = createHuffmanTree(charMap);
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTree);
        return zip(str, huffmanCodes);
    }

    public static byte[] zip(String str, Map<Byte, String> huffmanCodes){
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(huffmanCodes.get(aByte));
        }
//        System.out.println(sb.toString());
        int len= (sb.length()+7)/8;
        byte[] by = new byte[len];
        int count=0;
        for (int i = 0; i < sb.length(); i+=8) {
            String strByte;
            if (i+8>sb.length()){
                strByte = sb.substring(i);
            }else {
                strByte=sb.substring(i,i+8);
            }
            by[count] = (byte) Integer.parseInt(strByte, 2);
            count++;
        }
        return by;
    }
    public static Map<Byte,String> getHuffmanCodes(byteNode root){
        if (root == null) {
//            System.out.println("霍夫曼树为空");
            return null;
        }
        Map<Byte,String> huffmanCodes= new HashMap<>();
        StringBuilder sb = new StringBuilder();
        getCodes(root,"",sb,huffmanCodes);
//        getCodes(root.left,"0",sb,huffmanCodes);
//        getCodes(root.right,"1",sb,huffmanCodes);
        return huffmanCodes;
    }
    private static void getCodes(byteNode node, String code, StringBuilder sb, Map<Byte, String> huffmanCodes){
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node!=null){
            if (node.ch ==null){
                getCodes(node.left,"0",sb2,huffmanCodes);
                getCodes(node.right,"1",sb2,huffmanCodes);
            }else {
                huffmanCodes.put(node.ch,sb2.toString());
            }
        }
    }

    /**
     * 将字符串遍历统计出现字符的次数，并将字符和其对应的次数封装入byteNode中，并存入一个list集合中
     * @param str 原始的字符串
     * @return
     */
    private static List<byteNode> getList(String str){
        //获取字符串的字符数组
        byte[] bytes = str.getBytes();
        //创建一个map集合统计字符串中每个字符的出现次数，让key=字符，value=出现次数
        Map<Byte,Integer> map=new HashMap<>();
        for (byte aByte : bytes) {
//            map.merge(aByte,1,Integer::sum);
            Integer integer = map.get(aByte);
            if (integer == null) {
                map.put(aByte,1);
            }else {
                map.put(aByte,integer+1);
            }
        }
        List<byteNode>nodes =new ArrayList<>();
        //将map中的键值对装入byteNode对象中，然后放入list集合用于生成霍夫曼树
        for (Byte aByte : map.keySet()) {
            //将byteNode的ch放入字符，value放入出现次数，相当于权值
            nodes.add(new byteNode(aByte,map.get(aByte)));
        }
        return nodes;
    }

    /**
     * 通过nodes集合通过value的权值比较大小生成霍夫曼树
     * @param nodes
     * @return
     */
    private static byteNode createHuffmanTree(List<byteNode> nodes){
        //循环拼接子树，直到集合中只有一个结点就是霍夫曼树的父结点
        while (nodes.size()>1){
            //先从小到大排序
            Collections.sort(nodes);
            //取出权值最小的两个子树
            byteNode leftNode = nodes.get(0);
            byteNode rightNode = nodes.get(1);
            //生成两个子树的父结点并拼接子树
            byteNode parent = new byteNode(null, leftNode.value + leftNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //删除已经合并的子树，放入合并后的树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //返回根节点
        return nodes.get(0);
    }
    public static void preOrder(byteNode root){
        if (root == null) {
            System.out.println("霍夫曼树为空，无法遍历");
        }else {
            System.out.println(root);
            if (root.left!=null){
                preOrder(root.left);
            }
            if (root.right != null) {
                preOrder(root.right);
            }
        }
    }
}


class byteNode implements Comparable<byteNode>{
    public Byte ch;//byte字节
    public int value;//权值
    public byteNode left;
    public byteNode right;

    public byteNode(Byte ch, int value) {
        this.ch = ch;
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", byteNode.class.getSimpleName() + "[", "]")
                .add("ch=" + ch)
                .add("value=" + value)
                .toString();
    }

    @Override
    public int compareTo(byteNode o) {
        return this.value- o.value;
    }
}