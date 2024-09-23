package com.sam.myRPC.server;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCServer implements RPCServer {
    private ThreadPoolExecutor threadPool ;
    private ServiceProvider serviceProvider;

    ThreadPoolRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
    }


    @Override
    public void start(int port) {
        System.out.println("Server started on port " + port);

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.submit(new WorkThread(socket, serviceProvider));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void stop() {
    }
}
