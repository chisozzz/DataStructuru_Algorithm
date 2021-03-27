
public class Student {
    private int id;
    private String name;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == this) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student s = (Student) o;
//        if (this.id != s.id) return false;
//        return name != null ? name.equals(s.name) : s.name == null;
//    }


    @Override
    public boolean equals(Object o) {
        //首先判断两者是不是同一个对象
        if (this == o) return true;
        //判断对比的对象是不是空，或者两者的类型是不是一样
        if (o == null || getClass() != o.getClass()) return false;

        //满足了上面的情况，证明对象是同类型，且不为空，可以强制转换，进行内容的比较
        Student student = (Student) o;

        //id是基本数据类型，直接==比较,如果还有其他基本类型，一一比较，返回结果
        if (id != student.id) return false;
        //name是引用数据类型，使用equals比较，同时因为null可以占位，判断null是否相同
        //三元运算的高级应用,需要慢慢的逻辑分析
        return name != null ? name.equals(student.name) : student.name == null;
    }

}
