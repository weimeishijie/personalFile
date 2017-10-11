package thinking.in.java.Eexception.limitException;

/**
 * 异常的限制：
 * 当覆盖方法的时候，只能抛出在基类方法的异常说明里列出的那些异常，此限制的作用是：
 * 当基类使用的代码应用到其派生类对象的时候，一样能够工作（这是面向对象的基本概念）
 */

class BaseballException extends Exception{}
class Foul extends BaseballException{}
class Strike extends BaseballException{}

abstract class Inning{
    public Inning() throws BaseballException{}
    public void event() throws BaseballException{
        // Doesn't actually have to throw anything
    }
    public abstract void atBat() throws Strike, Foul;
    public void walk(){}// Throws no checked exceptions
}

class StormException extends Exception{}
class RainedOut extends StormException{}
class PopFoul extends Foul{}

interface Storm{
    void event() throws RainedOut;
    void rainHard() throws RainedOut;
}

public class LimitException extends Inning implements Storm{

    // OK to add new exceptions for constructors, but you must deal with the base for constructor exceptions:
    public LimitException() throws BaseballException {}

    public LimitException(String s) throws Foul, BaseballException{}

    // Regular methods must conform to base class:
    //! void walk() throws PopFoul{} // Compile error

    // Interface CANNOT add exceptions to exiting methods from the base class:
    //! public void event() throws RainedOut{}

    // you can choose to not throw any exceptions, even if the base version does:
    public void event(){}

    // If the method doesn't already exist in the base class, the exception is OK:
    public void rainHard() throws RainedOut {}

    // Overridden methods can throw inherited exceptions:
    public void atBat() throws PopFoul {}

    public static void main(String[] args) {
        try{
            LimitException si = new LimitException();
            si.atBat();
        } catch (PopFoul e){
            System.out.println("Pop foul");
        } catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }
        // Strike not thrown in derived version
        try{
            // What happens if you upcast?
            Inning i = new LimitException();
            i.atBat();
            // you must catch the exceptions from the base-class version of the method:
        } catch (Strike e){
            System.out.println("Strike");
        } catch (Foul e){
            System.out.println("Foul");
        } catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }
    }
}
