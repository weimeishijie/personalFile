package com.personal.file.clone;

import java.io.*;

/**
 * 克隆即实现对象的复制
 */
public class JavaClone {

    //Java中的赋值，原始类型
    int a = 1;
    int b = a;

    // 引用类型
    String[] weekdays = new String[5];
    String[] gongzuori = weekdays;//仅拷贝引用

    /**
     * 在上述代码中 ：
     * 1.如果是原始数据类型，赋值传递的为真实的值；
     * 2.如果是引用数据类型，赋值传递的为对象的引用，而不是对象；
     * 了解了数据类型和引用类型这个区别，便于我们了解clone
     *
     * Clone :
     * 在Java中，clone是将已有对象在内存中复制出另一个与之相同的对象的过程。java中的克隆为
     * 涿域复制。在Java中想要支持clone方法，需要首先实现Cloneable接口
     * Cloneable其实是有点奇怪的，它不同与我们常用到的接口，它内部不包含任何方法，它仅仅是
     * 一个标记接口。其源码如下：
     * public interface Cloneable{   }
     *
     * 关于cloneable,需要注意的
     * 1.如果想要支持clone，就需要实现Cloneable接口；
     * 2.如果没有实现Cloneable接口的调用clone方法，会抛出CloneNotSupportedException异常,
     * 然后是重写clone方法，并修改成public访问级别
     */
    static class CloneableImp implements Cloneable{
        public Child child;

        @Override
        public Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }
    //调用clone方法复制对象
    private static void cloneDemo(){
        CloneableImp imp1 = new CloneableImp();
        imp1.child = new Child("Andy");
        try {
            Object obj = imp1.clone();
            CloneableImp imp2 = (CloneableImp) obj;
            System.out.println("main imp2.child.name = "+imp2.child.name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
//    public static void main(String[] args) {
//        cloneDemo();
//    }

    /**
     * 浅拷贝 ：
     * 上面的代码实现的clone实际上是属于浅拷贝（Shallow Copy）。
     * 关于浅拷贝 ：
     * 1.使用默认的clone方法
     * 2.对于原始数据域进行值拷贝
     * 3.对于引用类型仅拷贝引用
     * 4.执行快，效率高
     * 5.不能做到数据的100%分离
     * 6.如果一个对象只包含原始数据域或者不可变对象域，推荐使用浅拷贝
     *
     * 关于无法做到数据分离，我们可以使用下面代码验证 ：
     */
    private static void cloneDemo1(){
        CloneableImp imp1 = new CloneableImp();
        imp1.child = new Child("Andy");
        try {
            Object obj = imp1.clone();
            CloneableImp imp2 = (CloneableImp) obj;
            imp2.child.name = "Bob";

            System.out.println("main imp1.child.name = "+imp1.child.name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        cloneDemo1();
//    }

    /**
     * 上述代码我们使用了imp1的clone方法克隆出imp2,然后修改imp2.child.name为Bob，
     * 然后打印imp1.child.name得到的结果是 main imp1.child.name = Bob
     * 原因是浅拷贝并没有做到数据的100%分离，imp1和imp2共享一个Child对象，所以一个修改会影响到另一个
     *
     * 深拷贝 ：
     * 深拷贝可以解决数据100%分离的问题。只需要对上面代码进行一些修改即可。
     * 1.Children实现Cloneable接口
     */
    static class CloneableImp1 implements Cloneable{
        public int cout;
        public Children children;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            CloneableImp1 obj = (CloneableImp1) super.clone();
            obj.children = (Children)children.clone();
            return obj;
        }
    }

    /**
     * 当我们再次修改imp2.children.name就不会影响到imp1.children.name的值了，因为imp1和imp2各自拥有
     * 自己的children对象，做到了数据100%隔离；关于深拷贝的一些特点
     * 1.需要重写clone方法，不仅仅只调用父类的方法，还需要调用属性的clone方法
     * 2.做到了原对象与克隆对象之间100%数据分离
     * 3.如果是对象存在引用类型的属性，建议使用深拷贝
     * 4.深拷贝比浅拷贝要更加耗时，效率更低
     *
     * 为什么使用克隆 ：
     * 很重要并且常见的就是：某个API需要提供一个List集合，但是又不希望调用者的修改影响到自身的变化，
     * 因此需要克隆一份对象，以此达到数据隔离的目的；
     *
     * 应尽量避免clone ：
     * 1.通常情况下，实现接口是为了表明类可以为它的客户做些什么，而Cloneable仅仅是一个标记接口，而且
     * 还改变了超类中的受保护的方法的行为，是接口的一种极端非典型的用法，不值得消防。
     * 2.Clone方法约定及其脆弱clone方法的Javadoc描述有点暧昧模糊，如下为JavaSE8的约定
     * clone方法创建并返回该对象的一个拷贝。而拷贝的精确含义取决于该对象的类。一般的含义是，对于任何对象
     * x，表达式
     * x.clone() != x 为 true x.clone().getClass() == x.getClass() 也返回true，
     * 但非必须x.clone().equals(x)也返回true，但也不是必须的
     * 上面的第二个和第三个表达式很容易就返回false。因而唯一能保证永久为true的就是表达式一，即两个对象
     * 为独立的对象
     * 3.可变对象final域在克隆方法中，如果我们需要对可变对象的final域也进行拷贝，由于final的限制，所以
     * 实际上是无法编译通过的，因此为了实现克隆，我们需要考虑舍去该可变对象域的final关键字。
     * 4.线程安全如果你决定用线程安全的类实现Cloneable接口，需要保证它的clone方法做好同步工作。默认的
     * Object.clone方法是没有做同步的。
     *
     * 总的来说，java中的clone方法实际上并不是完善的，建议尽量避免使用。如下是一些替代方案
     *
     * Copy constructors
     * 1.使用复制构造器也是构造器的一种
     * 2.只接受一个参数，参数类型为当前的类
     * 3.目的是生成一个与参与相同的新对象
     *
     * 复制构造器相比clone方法的优势是简单，易于实现。一段使用了复制构造器的代码示例Car.java
     *
     * 使用Serializable实现深拷贝
     *
     * 其实，使用序列化也可以实现对象的深拷贝。简略代码DeepCopyExample.java
     */

    /**
     * 使用实例兼测测试代码
     */
    private static void cloneDemo2(){
        DeepCopyExample example = new DeepCopyExample();
        example.children = new Children("Example");

        DeepCopyExample copy = example.copy();
        if(copy != null){
            copy.children.name = "Copied";
            System.out.println("example.children = "+example.children+";copy.children = "+copy.children);
        }
    }

    public static void main(String[] args) {
        cloneDemo2();
    }




}















