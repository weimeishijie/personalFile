package com.personal.file.proxy.dynamic_proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象
 * Created by mj on 2017/10/21.
 */
public class JdkPorxySubject implements InvocationHandler {

    RealObject realObject;

    public JdkPorxySubject(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object object = null;
        try {
            object = method.invoke(realObject, args);
        } finally {
            System.out.println("after");
        }
        return object;
    }
}
