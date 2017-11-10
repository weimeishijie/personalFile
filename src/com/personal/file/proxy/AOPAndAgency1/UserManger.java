package com.personal.file.proxy.AOPAndAgency1;

/**1.动态代理
 * 代理分为静态代理和动态代理，采用代理是为了通过不修改源代码的情况下给程序动态统一添加功能，
 * 利用代理技术可以将业务逻辑中一些非业务逻辑的代码分离出来，把他们独立到业务逻辑类外，
 * 比如日志记录，性能统计，安全控制，事务处理，异常处理等。这样做，
 * 不仅降低了业务逻辑和非业务逻辑的耦合性，提高程序的可重用性，同时提高了开发的效率。
 *
 * 下面以添加日志记录为例，分析动态代理的使用。创建一个用户管理类UserManagerImpl，
 * 并创建添加用户方法addUser，为其良好扩展性，创建一个通用接口UserManager，代码分别如下：
 *
 * 接口代码
 */
public interface UserManger {
    void addUser(String userId, String userName);
}
