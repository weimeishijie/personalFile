package thinking.in.java.Eexception.finallyWorks;

/**
 * 一种更加简单的异常丢失现象:
 * 运行程序会发现即使抛出了异常也不会有任何的输出
 */
public class SimpleLostException {
    public static void main(String[] args) {
        try{
            throw new NullPointerException();
        } finally {
            //Using 'return' inside the finally block
            //will silence any thrown exception;
            return;
        }
    }
}
