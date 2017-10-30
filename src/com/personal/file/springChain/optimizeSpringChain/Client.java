package com.personal.file.springChain.optimizeSpringChain;

import java.util.Arrays;
import java.util.List;

/**
 * spring链式结构设计
 * Created by mj on 2017/10/30.
 */
public class Client {

    static class ClientHandlerA extends ChainHandler{
        @Override
        public void handlerProcess() {
            System.out.println("handler A");
        }
    }

    static class ClientHandlerB extends ChainHandler{
        @Override
        public void handlerProcess() {
            System.out.println("handler B");
        }
    }

    static class ClientHandlerC extends ChainHandler{
        @Override
        public void handlerProcess() {
            System.out.println("handler C");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = Arrays.asList(
                new ClientHandlerA(),
                new ClientHandlerB(),
                new ClientHandlerC()
        );

        Chain chain = new Chain(handlers);
        chain.process();
    }

}
