package com.effective.blockingQueue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenweichao on 15-4-21.
 */
public class Bank {
    public static final int  COUNT = 100;
    public static final Semaphore semaphore = new Semaphore(2, true);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            final int count = i;
            new Thread(){
                @Override
                public void run(){
                    try {
                        if (semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)) {
                            try {
                                Teller.getService(count);
                            } finally{
                                semaphore.release();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
class Teller {
    public static void getService(int i) {
        try {
            System.out.println("Serving: " + i);
            Thread.sleep((long) (Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
