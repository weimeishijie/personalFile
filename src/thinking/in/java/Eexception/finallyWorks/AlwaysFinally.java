package thinking.in.java.Eexception.finallyWorks;

/**
 * finally 甚至在异常没有被当前的异常处理程序捕获的情况下，
 * 异常处理机制也会在跳到更高一层的异常处理程序之前，执行finally子句
 */

class FourException extends Exception{}

public class AlwaysFinally {
    public static void main(String[] args) {
        System.out.println("Entering first try block");
        try{
            System.out.println("Entering second try block");
            try{
                throw new FourException();
            } finally {
                System.out.println("finally in 2nd try block");
            }
        } catch (FourException e){
            System.out.println("Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }
}
