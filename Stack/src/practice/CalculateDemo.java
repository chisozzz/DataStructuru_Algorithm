package practice;


//综合的中缀表达式的计算器
public class CalculateDemo {
    public static void main(String[] args) {
        //计算程序
        String expression = "123-2*3";//表达式
        //两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int num1 =0;//数栈第一个出栈的数
        int num2 =0;//数栈第二个出栈的数
        int oper =0;//符号栈弹出的操作符
        int ch = 0;//表达式的扫描字符
        int res = 0;//两数计算的结果
        int index =0;//指针索引遍历表达式
        StringBuilder sb = new StringBuilder();//对多位数拼接
        while (true){
            if (index == expression.length()){
                //index到了表达式的最后，指向空白
                break;
            }
                //从表达式中切割出ch
                ch = expression.charAt(index);
                //判断ch是否为字符
                if (operStack.isOper(ch)){
                    //是操作符，再判断符号栈是否为空
                    if (operStack.isEmpty()){
                        //空栈，直接加入ch
                        operStack.push(ch);
                    }else {
                        //不是空栈，判断字符优先级
                        if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                            //ch字符优先级小于符号栈栈顶的符号优先级
                            //从数栈弹出两个数，符号栈弹出一个，进行计算
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = operStack.cal(num1,num2,oper);
                            numStack.push(res);
                            operStack.push(ch);
                        }else {
                            //ch字符优先级大于符号栈栈顶的符号优先级
                            //直接将ch入栈
                            operStack.push(ch);
                        }
                    }
                }else {
                    //不是操作符，是数字
                    //多为数判断，ch的下一个数是操作符，则直接入数栈，不是则，拼接下一个数，接着判断
                    sb.append(ch - 48);
                    while (true){
                        if (index == expression.length()-1){
                            numStack.push(Integer.parseInt(sb.toString()));
                            sb = new StringBuilder();//将sb清空
                            break;
                        }else {
                            if (numStack.isOper(expression.charAt(index + 1))){
                                //ch的下一个数是操作符，则直接入数栈
                                numStack.push(Integer.parseInt(sb.toString()));
                                sb = new StringBuilder();//将sb清空
                                break;
                            }else {
                                //不是则，拼接下一个数，接着判断
                                sb.append(expression.charAt(index +1));
                                index++;
                            }
                        }
                    }
                }
                index++;

        }
        while (true) {
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d \n",expression,numStack.pop());
    }
}

//使用前面的数组模拟栈
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //查看栈顶的值
    public int peek(){
        return stack[top];
    }
    //判断符号的优先级,使用数字的大小比较
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {//不是以上的操作符
            return -1;
        }
    }

    //判断是否是操作符
    public boolean isOper(int oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    //两数计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
        }
        return res;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;//栈顶指向数组最后的下一个
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //数据入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满，入栈失败");
            return;
        }
        stack[++top] = num;
    }

    //数据出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈，出栈失败");
        }
        return stack[top--];
    }

    //查看栈
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }
}