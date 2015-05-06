package com.effective.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenweichao on 15-5-6.
 */
public class EchoMultiServer {

    private static ServerSocket server = null;
    private static ExecutorService threadPool;

    public static void main(String[] args) {
        try {
            threadPool = Executors.newCachedThreadPool();
            threadPool.submit(new Monitor());
            server = new ServerSocket(10000);
            System.out.println("Server listening on port 10000 ....");
            System.out.println("Hit Enter to stop the server");
            while (true) {
                Socket socketObject = server.accept();
                System.out.println("Thread created");
                threadPool.submit(new EchoThread(socketObject));


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void shutdownServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        System.exit(0);
    }



    private static class Monitor implements Runnable {

        public void run() {
            try {
                while (System.in.read() != '\n'){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            shutdownServer();
        }
    }

    static class EchoThread implements Runnable {

        private Socket socketObj = null;
        private byte buffer[] =new byte[512];

        public EchoThread(Socket socket) {
            this.socketObj = socket;
        }
        public void run() {
            InputStream reader = null;
            try {
                reader = socketObj.getInputStream();
                reader.read(buffer);
                OutputStream writer = socketObj.getOutputStream();
                writer.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();

            }finally {
                try {
                    socketObj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
