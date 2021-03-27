public class LinearSearch {
    private LinearSearch() {
    }

    //线性查找的通用泛型方法，参数1是泛型的数组，参数2是泛型,返回下标，没有为-1
    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {//一个循环条件O(n)时间复杂度
            //泛型类使用equals方法比对内容，该equals方法需要在类型中重写
            if (data[i].equals(target)) {
                return i;
            }
        }
        //如果上面执行没有返回索引，说明数组中没有找到参数2，返回一个-1表示没有结果
        return -1;
    }

    public static void main(String[] args) {
        //性能测试
        int[] ns={1000000,10000000};
        for (int n : ns) {

            Integer[] data = ArrayGenertor.product(n);
            long startTime = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                LinearSearch.search(data, n);
            }
            long endTime = System.nanoTime();
            System.out.println("n: " + n + " 100 runs :" + (endTime - startTime) / 1000000000.0 + " s");
        }

    }
}
