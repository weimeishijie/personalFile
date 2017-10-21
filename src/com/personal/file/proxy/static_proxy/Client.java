package com.personal.file.proxy.static_proxy;

/**
 * 客户端
 * Created by mj on 2017/10/21.
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Proxy(new RealObject());
        subject.hello();
    }

}
