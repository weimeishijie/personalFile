package com.personal.file.string.format;

import java.io.File;
import java.util.function.BinaryOperator;

/**
 * Created by mj on 2017/10/12.
 * 注意：在格式化中：d代表整数，f代表小数，s代表字符串
 */
public class Hex {
    public static String format(byte[] data){
        StringBuilder result = new StringBuilder();
        int n = 0;
        for(byte b : data){
            if(n % 16 == 0)
                result.append(String.format("%05X: ", n));
            result.append(String.format("%02 ", b));
            n++;
            if(n % 16 == 0)
                result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) {
        if(args.length == 0)
            // Test by displaying this class file:
            System.out.println("\\"+ "ni" +"\\");
//            System.out.println(format(BinaryFile.read("Hex.class")));
        else
            System.out.println("\\\\");
//            System.out.println(format(BinaryFile.read(new File(args[0]))));
    }
}
