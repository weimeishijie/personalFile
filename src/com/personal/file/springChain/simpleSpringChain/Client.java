package com.personal.file.springChain.simpleSpringChain;

/**
 * Created by mj on 2017/10/30.
 */
public class Client {

    static class ClientHandlerA extends Handler{
        @Override
        public void handlerProcess() {
            System.out.println("handler A");
        }
    }

    static class ClientHandlerB extends Handler{
        @Override
        public void handlerProcess() {
            System.out.println("handler B");
        }
    }

    static class ClientHandlerC extends Handler{
        @Override
        public void handlerProcess() {
            System.out.println("handler C");
        }
    }

    public static void main(String[] args) {
        Handler handlerA = new ClientHandlerA();
        Handler handlerB = new ClientHandlerB();
        Handler handlerC = new ClientHandlerC();

        handlerA.setHandler(handlerB);
        handlerB.setHandler(handlerC);

        handlerA.execute();
    }


}
