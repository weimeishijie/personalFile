package com.personal.file.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.Objects.requireNonNull;

/**
 * 注释：
 * ServerSocket:Java中一个专门用来建立Socket服务器的类,
 * 可以用服务器需要使用的端口号作为参数来创建服务器对象。
 * model:ServerSocket serverSocket=new ServerSocket(port);
 *
 * socket = serverSocket.accept();// 监听,等待连接,一旦有 client 端连接便创建 socket 实例.
 *
 * 然后通过socket交互数据.
 *
 * serverSocket.accept();的这一方法可以说是阻塞式的,没有client端连接就一直监听着,
 * 等待连接.直到有 client 端连接进来才通过 socket 实例与 client 端进行交互,
 * 一个 server 端可以被多个 client 端连接,每连接一次都会创建一个 socket 实例,派发服务线程.
 *
 * 如果把监听写进 while() 循环里便可实现不断的监听.
 */
public class SocketDemo {

    private class ConnectionListener implements Runnable {

        private ServerSocket serverSocket;

        private volatile boolean terminated;

        private final Executor clientExecutor = Executors.newCachedThreadPool();

        public void terminate() {
            terminated = true;
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException exc) {

            } finally {
                // Make sure we dispose of the used server socket.
                serverSocket = null;
            }
        }

        @Override
        public void run() {
            // Set up the listening socket.
            try {
                // new ServerSocket() 相当于服务启动了，监听了端口listenPort
                serverSocket = new ServerSocket(55555);
            } catch (IOException exc) {
                throw new IllegalStateException("IOException trying to create server socket at Port " + 55555, exc);
            }
            // Accept new connections until terminated from outside.
            while (!terminated) {
                try {
                    // serverSocket.accept() 等待客户端连接端口listenPort
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connection from " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                    // TODO liwenya 疑问这里的线程有可能收不回来吗？
                    clientExecutor.execute(new ConnectionHandler(clientSocket));
                } catch (SocketException exc) {
                    // Check if we're supposed to terminate.
                    if (terminated) {
                        System.out.println("Received termination signal.");
                    } else {
                        System.out.println("SocketException without termination flag set");
                    }
                } catch (IOException exc) {
                    System.out.println("IOException listening for connections");
                }
            }
            System.out.println("Terminated connection listener.");
        }
    }

    private class ConnectionHandler implements Runnable{

        private static final int IN_BUF_SIZE = 16384;

        private final Socket socket;

        ConnectionHandler(Socket clientSocket) {
            socket = requireNonNull(clientSocket, "clientSocket");
            if (!clientSocket.isConnected()) {
                System.out.println("clientSocket is not connected");
                throw new IllegalArgumentException("clientSocket is not connected");
            }
        }

        @Override
        public void run() {

        }
    }

}
