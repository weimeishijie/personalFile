package thinking.in.java.Eexception.matchException;

/**
 * Created by mj on 2017/10/11.
 */

class Annoyance extends Exception {}
class Sneeze extends Annoyance {}

public class Human {
    public static void main(String[] args) {
        // Catch the exact type:
        try{
            throw new Sneeze();
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch (Annoyance a){
            System.out.println("Caught Annoyance");
        }
        // Catch the base type:
        try{
            throw new Sneeze();
        } catch (Annoyance a) {
            System.out.println("Caught Annoyance");
        }
    }
}
