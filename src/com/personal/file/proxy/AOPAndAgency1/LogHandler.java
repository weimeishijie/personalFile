package com.personal.file.proxy.AOPAndAgency1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *静态代理虽隔离了与业务逻辑无关的代码，降低了耦合，
 * 让业务逻辑类更专注于业务逻辑，
 * 但无法减少代码量，系统重复代码过多，加大了程序员工作量。因此，
 * JDK动态代理完美解决了此问题，
 * 动态代理支持在系统运行期给类动态添加代理，
 * 然后通过操控代理类完成对目标类的调用。
 *
 * 继续演化上面举的例子，将静态代理改为动态代理，
 * 抽象类UserManager和目标类UserManagerImpl中的代码不变，
 * 将静态代理类UserManagerImplProxy删除，添加 LoadHandler 类，
 * 并让它实现 InvocationHandler 接口中的 invoke 方法，代码如下：
 */
public class LogHandler implements InvocationHandler {

    // 保留一份 targetObject 目标类对象
    private Object targetObject;

    // Proxy 类动态创建一份目标代理类
    public Object newProxyInstance(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        for(int i=0; i<args.length; i++){
            System.out.println("Object[] args: "+args[i]);
        }
        Object ret = null;
        try {
            //调用目标方法
            System.out.println("before");
            ret = method.invoke(targetObject, args);
            System.out.println("after");
            System.out.println("执行成功！");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("执行失败！");
            throw e;
        }
        return ret;
    }
}

/**
 * Proxy类所创建的目标类必须实现至少一个接口，
 * 在调用newProxyInstance方法时必须与目标类的类加载器和接口一致；
 * invoke方法非常类似Filter中的doFilter方法，
 * 它将调用目标类的所有方法在未到达UserManagerImpl之前截获，
 * 根据我们自己的需求进行预处理后，继续调用UserManagerImpl。
 *
 * 为了保持invoke方法的通用性，目标方法中的参数以数组args形式传递，
 * 如果方法中有返回值，则返回，没有返回值，则返回null。如此一来，
 * 程序员不必为每个目标类设计一个代理类，
 * 所有需要打印日志的类都可以共用这个LogHandler，
 * 如果不想使用日志功能就可以直接删除LogHandler类，
 * 对原功能没有丝毫影响，如同揭去显示器上的保护膜，不会影响显示器的使用一般。
 */
















