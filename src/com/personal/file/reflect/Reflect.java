package com.personal.file.reflect;

import java.lang.reflect.Method;

/**
 * 反射机制代码演示
 */
public class Reflect {

    public void test(int x){
        System.out.println(x);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, Exception{

//        Class<?> forName = Class.forName("com.personal.file.reflect.Reflect");
//        Method method = forName.getDeclaredMethod("test", Integer.class);
//        method.invoke(forName.newInstance(), 10);

        Class<?> forName1 = Class.forName("com.personal.file.reflect.Reflect");
        Method method1 = forName1.getDeclaredMethod("test", int.class);
        method1.invoke(forName1.newInstance(),10);
//        Object object = forName1.newInstance();
//        Reflect reflect = null;
//        if(object instanceof Reflect) {
//            reflect = (Reflect) object;
//            reflect.test(10);
//        }
    }

}
