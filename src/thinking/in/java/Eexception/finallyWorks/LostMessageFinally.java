package thinking.in.java.Eexception.finallyWorks;

/**
 * 异常丢失：用某些特殊的方式使用finally子句，就会出现这种情况
 *
 * 下面的例子：从输出中可以看出
 * VeryImportantException不见了，他被finally子句里的HoHumException所取代。
 *
 */

class VeryImportantException extends Exception{
    public String toString() {
        return "A very important exception!";
    }
}
class HoHumException extends  Exception{
    public String toString() {
        return "A trivial exception";
    }
}

public class LostMessageFinally {
    void f() throws VeryImportantException{
        throw new VeryImportantException();
    }
    void dispose() throws HoHumException{
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try{
            LostMessageFinally lm = new LostMessageFinally();
            try{
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
