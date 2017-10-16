package com.personal.file.string.format;

/**
 * 注意：在格式化中：d代表整数，f代表小数，s代表字符串
 */
public class Formatting {
    public static void main(String[] args) {
        int x = 55;
        float y = 6.6f;
        String s = "nihao";
        System.out.format("Row 1: %d %2.5f %s",x,y,s);
    }
}
