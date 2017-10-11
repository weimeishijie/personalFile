package thinking.in.java.Eexception.finallyWorks;

/**
 * 在return中使用finally
 * 因为finally子句总是会被执行的，所以在一个方法中可以从多个点返回，
 * 并且可以保证重要的清理工作仍就会执行
 */
public class ReturnFinally {
    public static void f(int i){
        System.out.println("Initialization that requires cleanup");
        try{
            System.out.println("Point 1");
            if(i == 1) return;
            System.out.println("Point 2");
            if(i == 2) return;
            System.out.println("Point 3");
            if(i == 3) return;
            System.out.println("End");
            return;
        } finally {
            System.out.println("Performing cleanup");
        }
    }

    public static void main(String[] args) {
        for(int i = 1; i<= 4; i++){
            f(i);
        }
    }
}
