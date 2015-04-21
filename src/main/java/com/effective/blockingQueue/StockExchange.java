package com.effective.blockingQueue;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chenweichao on 15-4-21.
 */
public class StockExchange {
    public static void main(String[] args) {
        System.out.printf("Hit Enter to TERMINATE%n%n");
        BlockingQueue<Integer> orderQueue = new LinkedBlockingDeque<Integer>();
        Seller seller = new Seller(orderQueue);
        Thread[] sellerThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            sellerThread[i] = new Thread(seller);
            sellerThread[i].start();
        }
        Buyer buyer = new Buyer(orderQueue);
        Thread[] buyerThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            buyerThread[i] = new Thread(buyer);
            buyerThread[i].start();
        }
        try {
            while (System.in.read() != '\n') {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Terminating");
        for (Thread t : sellerThread) {
            t.interrupt();
        }
        for (Thread t : buyerThread) {
            t.interrupt();
        }

    }
}


class Seller implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;

    public Seller(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void run() {
        while (shutdownRequest == false) {
            Integer quantity = (int) (Math.random() * 100);
            try {
                orderQueue.put(quantity);
//                System.out.println("Sell order by " + Thread.currentThread().getName() + " : " + quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }
            System.out.println();

        }
    }
}

class Buyer implements Runnable{
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;

    public Buyer(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }


    public void run() {
        while (shutdownRequest == false) {
            try {
                Integer quantity = (Integer) orderQueue.take();
//                System.out.println("Buy order by " + Thread.currentThread().getName() + ": " + quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }


        }
    }
}
