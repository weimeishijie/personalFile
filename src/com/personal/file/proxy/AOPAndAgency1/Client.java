package com.personal.file.proxy.AOPAndAgency1;


/**4.动态代理
 * 客户端调用代码如下：
 */
public class Client {

    public static void main(String[] args) {

        LogHandler logHandler = new LogHandler();

        UserManger userManger = (UserManger) logHandler.newProxyInstance(new UserManagerImpl());

        userManger.addUser("id", "name");
    }
}
