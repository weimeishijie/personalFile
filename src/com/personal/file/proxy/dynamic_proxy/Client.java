package com.personal.file.proxy.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * 客户端
 * Created by mj on 2017/10/21.
 */
public class Client {

    public static void main(String[] args) {

        // 方法一：规范的写法
        RealObject realObject = new RealObject();
        Subject subject = (Subject) Proxy.newProxyInstance(realObject.getClass().getClassLoader(),realObject.getClass().getInterfaces(),new JdkPorxySubject(realObject));
        subject.hello();

        System.out.println();
        // 方法二：方法写的不规范
        Subject subject1 = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),new Class[]{Subject.class},new JdkPorxySubject(new RealObject()));
        subject1.hello();
    }

}
