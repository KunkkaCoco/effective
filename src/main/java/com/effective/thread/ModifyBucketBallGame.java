package com.effective.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenweichao on 15-4-10.
 */
public class ModifyBucketBallGame {
    private int bucket[] = {10000, 10000};
    private static boolean RIGHT_TO_LEFT;
    ReentrantLock lock = new ReentrantLock();


    private void doTransfers() {
        for (int i = 0; i < 50; i++) {
            new Thread(new TransferThread(!RIGHT_TO_LEFT)).start();
            new Thread(new TransferThread(RIGHT_TO_LEFT)).start();
        }
    }

    public static void main(String[] args) {
        new ModifyBucketBallGame().doTransfers();
    }

    public void transfer(boolean direction, int numToTransfer) {
        lock.lock();
        if (direction == RIGHT_TO_LEFT) {
            bucket[0] += numToTransfer;
            bucket[1] -= numToTransfer;
        } else {
            bucket[0] -= numToTransfer;
            bucket[1] += numToTransfer;
        }
        System.out.println("Total: " + (bucket[0] + bucket[1]));
        lock.unlock();
    }

    private class TransferThread implements Runnable {
        private boolean direction;

        public TransferThread(boolean direction) {
            this.direction = direction;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                transfer(direction, (int) (Math.random() * 2000));
                try {
                    Thread.sleep((long) (Math.random() * 100));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
