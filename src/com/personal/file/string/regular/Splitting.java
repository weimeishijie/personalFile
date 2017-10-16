package com.personal.file.string.regular;

import java.util.Arrays;

/**
 * string字符串操作中自带的正则表达式方法：
 * String.split()方法:根据符合表达式进行字符串分割
 * String.replaceAll()、String.replaceFirst() ： 符合正则表达式的部分进行替换；
 */
public class Splitting {
    public static String knights = "Then, when you have found the shrubbery, you must " +
            "cut down the mightist tree in the forest..." +
            "with... a herring!";
    public static void split(String regex){
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    static String s = knights;
    public static void main(String[] args) {
        split(" "); // Doesn't have to contain regex chars
        split("\\W+"); // Non-word characters
        split("n\\W+"); // 'n' followed by non-word characters
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));
    }
}
