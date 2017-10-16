package com.personal.file.string.regular.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式可以拥有标记
 */
public class ReFlags {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher("java has regex\nJava hae regex\n" +
                "JAVA has pretty good regular expressions\n" +
                "Regular expressions are in Java");
        while (m.find()){
            System.out.println(m.group());
        }
    }
}
