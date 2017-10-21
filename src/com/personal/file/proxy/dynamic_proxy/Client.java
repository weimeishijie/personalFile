package com.personal.file.proxy.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * 客户端
 * Created by mj on 2017/10/21.
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),new Class[]{Subject.class},new JdkPorxySubject(new RealObject()));
        subject.hello();
    }

}
