package com.personal.file.classs;

import com.personal.file.map.values.MapValues;

/**
 *
 */
public class MethodDemo {

    public static void main(String[] args) {
        // com.personal.file.map.values.MapValues
        System.out.println(MapValues.class.getName());
        // class java.lang.Class
        System.out.println(MapValues.class.getClass());
        // class com.personal.file.map.values.MapValues
        System.out.println(new MapValues().getClass());
        // MapValues
        System.out.println(MapValues.class.getSimpleName());
        // com.personal.file.map.values.MapValues
        System.out.println(MapValues.class.getTypeName());

    }


}
