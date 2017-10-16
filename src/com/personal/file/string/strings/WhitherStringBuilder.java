package com.personal.file.string.strings;

import java.util.ArrayList;

/**
 * 当你为一个类编写toString方法时，如果字符串操作比较简单，可以信赖编译器；
 * 如果要在toString方法中使用循环，那么最好自己创建一个StringBuilder对象，用它来构造最终结果
 */
public class WhitherStringBuilder {
    public String implicit(String[] fields){
        String result = "";
        for(int i=0; i<fields.length; i++){
            result += fields[i];
        }
        return result;
    }
    public String explicit(String[] fields){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++){
            result.append(fields[i]);
        }
        return result.toString();
    }

}
