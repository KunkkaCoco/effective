package com.effective.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chenweichao on 15-5-6.
 */
public class EchoMultiClient {
    public static int counter;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            counter++;
            new Thread(new Client(counter)).start();
        }
    }

    private static class Client implements Runnable {
        private int counter;

        public Client(int counter) {
            this.counter = counter;
        }

        public void run() {
            try {
                Socket socketObj = new Socket();
                socketObj.connect(new InetSocketAddress("localhost", 10000), 1000);

                OutputStream out = socketObj.getOutputStream();
                String str = "Hello world from Client " + counter;
                out.write(str.getBytes());
                InputStream inputStream = socketObj.getInputStream();
                Scanner in = new Scanner(inputStream);
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    socketObj.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}