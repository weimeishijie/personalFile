package com.personal.file.string.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果希望toString方法打印出对象的内存地址，也许你会考虑使用this关键字
 * 这样的做法会无意识的用到了递归，会报一长串错误；原因:
 * 在这里发生了自动类型转换，有InfiniteRecursion类型转换成toString类型，
 * 因为编译器看到一个String对象后面跟着一个“+”，而再后面的对象不是String，
 * 于是编译器试着将this转换成一个String对象，他是怎么转换的？正是通过调用this上的
 * toString方法；
 * 如果想要打印出对象的地址，应该调用Object.toString方法；
 * 这里不应该使用this，而是应该调用super.toString
 */
public class InfiniteRecursion {
    public String toString(){
        return " InfiniteRecursion address: " + this + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<InfiniteRecursion>();
        for(int i = 0; i < 10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}
