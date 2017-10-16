package com.personal.file.string.regular.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模式 \\w+ 将字符串划分为单词，find() 像迭代器一样遍历输出字符串；
 * 接受参数的的 find() 根据其参数值，不断重新设定搜索的起始位置
 */
public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Eventing is full of the linnet's wings");
        while (m.find()) {
            System.out.println(m.group() + " ");
        }
        System.out.println();
        int i = 0;
        while (m.find(i)){
            System.out.println(m.group() + " ");
            i++;
        }
    }
}
