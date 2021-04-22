package practice;

import java.nio.channels.GatheringByteChannel;
import java.text.SimpleDateFormat;
import java.util.*;

public class Practice {
    public static void main(String[] args) {
        String str="i like like like java do you like a java";
        byte[] bytes = zipString(str);
//        System.out.println(Arrays.toString(bytes));
        //解压文件
        byte[] decode = decode(huffmanCodes, bytes);
        StringBuilder sb = new StringBuilder();
        //将字节转变成字符，变成原始的字符串
        for (byte b : decode) {
            sb.append((char) b);
        }
        System.out.println(sb.toString());
    }
    static Map<Byte,String>huffmanCodes=new HashMap<>();
    private static byte[] decode(Map<Byte,String>huffmanCodes,byte[]bytes){
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            boolean flag=(aByte == bytes[bytes.length-1]);
            sb.append(byteToBitString(!flag,aByte));
        }
        //将字符串按照指定的编码解码
        //将霍夫曼编码进行逆转
        Map<String,Byte> codes=new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            codes.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list=new ArrayList<>();
        for (int i = 0; i < sb.length();) {
            int count=1;
            boolean flag=true;
            Byte b=null;
            while (flag) {
                String s = sb.substring(i, i+count);
                b = codes.get(s);
                if (b == null) {
                    count++;
                }else {
                    flag=false;
                }
            }
            list.add(b);
            i+=count;
        }
        byte[]by=new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            by[i]=list.get(i);
        }
        return by;
    }
    //将压缩的字符数组解码成二进制字符串
    private static String byteToBitString(boolean flag,byte b){
        int temp=b;//将byte数组转换成int，方便使用Integer类转换成二进制
        //如果temp是正数，则需要补高位，但是最后字符数组的最后一位不需要
        //其他位置都是8个bit组成的所以生成的int可能只有四位，1111但是实际的代码是00001111所以需要补高位
        if (flag){
            temp |=256;
        }
        //进行转换，int是32位，切取后8位的数字
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length()-8);
        }else {//如果为false，负数和最后一位的情况
            return str;
        }
    }
    //将霍夫曼树编码封装成一个方法
    public static byte[] zipString(String str){
        //统计字符串str中的每个字符的出现次数，将字符和次数放入node中组成集合，便于生成霍夫曼树
        List<byteNode> nodeList = getList(str);
        //生成霍夫曼树
        byteNode huffman = createHuffman(nodeList);
        //通过霍夫曼树到达每个路径生成霍夫曼树编码
        huffmanCodes = getHuffmanCodes(huffman);
        //通过霍夫曼编码将字符串str压缩成字符数组，数组元素是8个二进制的编码成的10进制
        byte[] zip = zip(str, huffmanCodes);
        return zip;
    }
    //练习生成霍夫曼树，对于大量的数据可以使用归并来排序
    public static Node createHuffmanTree(int[]arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent=new Node(leftNode.value+ rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //将字符串统计字符出现的次数,装入list集合中
    public static List<byteNode> getList(String str ){
        Map<Byte,Integer> map=new HashMap<>();
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            Integer count = map.get(aByte);
            if (count!=null){
                map.put(aByte,count+1);
            }else {
                map.put(aByte,1);
            }
        }
        List<byteNode> list=new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new byteNode(entry.getKey(),entry.getValue()));
        }
        return list;
    }
    //利用nodes集合生成霍夫曼树
    public static byteNode createHuffman(List<byteNode> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);
            byteNode leftNode = nodes.get(0);
            byteNode rightNode = nodes.get(1);
            byteNode parent=new byteNode(null,leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //通过生成的霍夫曼树获取每个叶子节点的路径，该路径就是变长编码，每个变长编码都是前缀编码
    //规定霍夫曼树向左结点为0，向右结点为1，生成霍夫曼树编码
    public static Map<Byte,String> getHuffmanCodes(byteNode root){
        //霍夫曼树编码，key=字符，value=二进制编码
        //拼接字符串
        StringBuilder sb =new StringBuilder();
        if (root ==null){
            return null;
        }else {
            getCodes(root,"",sb,huffmanCodes);
        }
        return huffmanCodes;
    }

    private static void getCodes(byteNode node, String s, StringBuilder sb, Map<Byte, String> huffmanCodes) {
        //每次拼接，为了防止上一个字符的编码和下一个编码连接在一起，每次拼接重写创建一个sb2，拼接字符串编码
        //这一步很重要，不然拼接编码会出错
        StringBuilder sb2=new StringBuilder(sb);
        sb2.append(s);
        if (node == null) {
            return;
        }
        if (node.ch == null) {
            getCodes(node.left,"0",sb2,huffmanCodes);
            getCodes(node.right,"1",sb2,huffmanCodes);
        }else {
            huffmanCodes.put(node.ch,sb2.toString());
        }
    }
    //将字符串中的字符通过霍夫曼树编码表转换成二进制编码
    public static byte[] zip(String str,Map<Byte,String> huffmanCodes){
        StringBuilder sb=new StringBuilder();
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            String s = huffmanCodes.get(aByte);
            sb.append(s);
        }
        int len=sb.length()%8==0?sb.length()/8:sb.length()/8+1;//这个更好理解
        //int len = (sb.length()+7)/8;//这个利用了除数除不尽会自动取整数部分，舍弃小数部分的机制
        int count=0;
        byte[] zip = new byte[len];
        for (int i = 0; i < sb.length(); i+=8) {
            String substring;
            //substring方法获取子串，从i开始，到i+8结束，不包括i+8，左闭右开
            //如果只有一个i，则截取从i到字符串末尾，包括i，左闭右开
            if (i+8>sb.length()){
                substring=sb.substring(i);//从i到末尾所有字符串
            }else {
             substring= sb.substring(i, i + 8);//[i,i+8)范围的字符串,8个
            }
            //进制的转换都可以使用Integer.parseInt，radix填入相应进制数2,8,10,16将字符串转换成相应进制
            zip[count]= (byte) Integer.parseInt(substring, 2);//整数类型可以相互转换，
            count++;
        }
        //这个携带了编码后的字符数组就是压缩后的字符串
        return zip;
    }
}

