package com.personal.file.string.format;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * Created by mj on 2017/10/12.
 * 注意：在格式化中：d代表整数，f代表小数，s代表字符串
 */
public class Turtle {
    private String name;
    private Formatter f;
    public Turtle(String name, Formatter f){
        this.name = name;
        this.f = f;
    }
    public void move(int x, int y){
        f.format("%s The Turtle is at (%d,%d)\n",name, x, y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.err;
        Turtle tommy = new Turtle("Tommy", new Formatter(System.out));
        Turtle terry = new Turtle("Terry", new Formatter(outAlias));
        tommy.move(0,0);
        terry.move(4,8);
        terry.move(3,4);
        tommy.move(2,5);
        terry.move(3,3);
        tommy.move(3,3);
    }
}
