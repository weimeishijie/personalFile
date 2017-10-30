package com.personal.file.springChain.optimizeSpringChain;

/**
 * spring链式设计模式优化
 * Created by mj on 2017/10/30.
 */
public abstract class ChainHandler {

    public void execute(Chain chain){
        handlerProcess();
        chain.process();
    }

    public abstract void handlerProcess();
}
