package com.personal.file.scan_in;

import java.util.Scanner;

/**
 * 可以修改扫描器的定界符：比如这个类中将定界符修改为逗号
 */
public class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNext()){
            System.out.println(scanner.nextInt());
        }
    }
}
