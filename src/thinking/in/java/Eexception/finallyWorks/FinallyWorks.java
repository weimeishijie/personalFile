package thinking.in.java.Eexception.finallyWorks;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * 对于一些代码，可能会希望无论try块中的异常是否抛出，他们都能得到执行。
 * 可以在异常处理程序后面加上finally字句：
 * try{
 *     //The guarded region: Dangerous activities
 *     // that miight throw A,B, or C
 * } catch (A a1){
 *     // Handler for situation A
 * } catch (B b1){
 *     // Handler for situation B
 * } catch (C c1){
 *     // Handler for situation C
 * } finally {
 *     // Activities that happen every time
 * }
 */

class ThreeException extends Exception {}

public class FinallyWorks {
    static int count = 0;
    public static void main(String[] args){
        while (true){
            try{
                //Post-increment is zero first time:
                if(count++ == 0)
                    throw new ThreeException();
                System.out.println("No exception");
            } catch (ThreeException ex){
                System.out.println("ThreeException");
//                ex.printStackTrace();
            } finally {
                System.out.println("In finally clause");
                if(count == 2) break;//out of "while"
            }
        }
    }
}
