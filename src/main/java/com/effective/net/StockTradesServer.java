package com.effective.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by chenweichao on 15-5-7.
 */
public class StockTradesServer {
    private static class StockTradesGenerator implements Runnable {

        private DatagramSocket broadcastSocket = null;
        private String[] stockSymbols = {"IBM", "SNE", "XRX", "MHP", "NOK"};
        private static final String TIME_FORMAT_NOW = "HH:mm:ss";

        public StockTradesGenerator() {
            try {
                broadcastSocket = new DatagramSocket(4445);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] buffer = new byte[80];
            try {


                while (true) {
                    int index = (int) (Math.random() * 5);
                    float trade = generateRandomTrade(index);
                    String lastTrade = String.format("%s %.2f @%s", stockSymbols[index], trade, now());
                    buffer = lastTrade.getBytes();

                    try {
                        InetAddress groupBroadcastAddress = InetAddress.getByName("230.0.0.1");
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupBroadcastAddress, 4446);
                        broadcastSocket.send(packet);
                        Thread.sleep((long) (Math.random() * 2000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                broadcastSocket.close();
            }
        }

        private float generateRandomTrade(int index) {
            float trade = (float) Math.random();
            switch (index) {
                case 0:
                    trade += 118;
                    break;
                case 1:
                    trade += 29;
                    break;
                case 3:
                    trade += 26;
                    break;
                case 4:
                    trade += 14;
                    break;

            }
            return trade;
        }

        private String now() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
            return sdf.format(calendar.getTime());
        }

        public static void main(String[] args) {
            Thread tradesGenerator = new Thread(new StockTradesGenerator());
            tradesGenerator.setDaemon(true);
            tradesGenerator.start();
            System.out.println("Stock trades broadcast server started!");
            System.out.println("Hit Enter to stop server!");
            try {
                while (System.in.read() != '\n') {

                }
            } catch (IOException e) {
                System.out.println(" Error starting server");
            }

        }
    }
}
