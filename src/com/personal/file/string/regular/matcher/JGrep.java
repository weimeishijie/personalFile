package com.personal.file.string.regular.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mj on 2017/10/12.
 */
public class JGrep {
    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile("args[1]");
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
//        for(String line : new TextFile(args[0])){
//            m.reset(line);
//            while (m.find())
//                System.out.println(index++ + ":"+ m.group()+" : "+m.start());
//
//        }
    }
}
