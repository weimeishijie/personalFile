package com.personal.file.AOPAndAgency;

/**4.静态代理
 * 客户端调用代码如下
 */
public class Client {
    public static void main(String[] args) {
        UserManger userManger = new UserManagerImplProxy(new UserManagerImpl());
        userManger.addUser("0111","贺慧");
    }
}
