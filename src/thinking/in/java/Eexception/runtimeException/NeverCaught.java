package thinking.in.java.Eexception.runtimeException;

/**
 * 在此类可以发现：RuntimeException(或任何从它继承的异常)是一个特例。对于这种异常类型，编译器不需要异常说明
 * 其输出被报告给了System.err:
 *
 * 如果RuntimeException没有被捕获而直达main()，那么在程序退出前将调用异常的printStackTrace()方法
 */
public class NeverCaught {
    static void f(){
        throw new RuntimeException("From f()");
    }
    static void g(){
        f();
    }

    public static void main(String[] args) {
        g();
    }
}
