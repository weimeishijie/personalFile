package thinking.in.java.Eexception.finallyWorks;

/**
 * finally 用来做什么：
 * 当要把内存之外的资源恢复到它们的初始状态时，就要用到finally子句。这种需要清理的资源包括：
 * 已经打开的文件或网络连接，在屏幕上画的图形，甚至可以是外部世界某个开关：
 */
public class FinallySwitch {
    private boolean state = false;
    public boolean read(){
        return state;
    }
    public void on(){
        state = true;
        System.out.println(this);
    }
    public void off(){
        state = false;
        System.out.println(this);
    }

    public String toString() {
        return state ? "on": "off";
    }
}


class OnOffException1 extends Exception{}
class OnOffException2 extends Exception{}

class OnOffSwitch{
    private static FinallySwitch sw = new FinallySwitch();
    public static void f() throws OnOffException1,OnOffException2{}

    public static void main(String[] args) {
        try{
            sw.on();
            // Code that can throw exceptions...
            f();
        } catch (OnOffException1 onOffException1) {
            onOffException1.printStackTrace();
        } catch (OnOffException2 onOffException2) {
            onOffException2.printStackTrace();
        } finally {
            sw.off();
        }
    }
}
