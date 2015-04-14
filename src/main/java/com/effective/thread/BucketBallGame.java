package com.effective.thread;

/**
 * Created by chenweichao on 15-4-9.
 */
public class BucketBallGame {
    private int bucket[] = {10000, 10000};
    private static boolean RIGHT_TO_LEFT;

    private void doTransfers() {
        for (int i = 0; i < 50; i++) {
            new Thread(new TransferThread(!RIGHT_TO_LEFT)).start();
            new Thread(new TransferThread(RIGHT_TO_LEFT)).start();
        }
    }
    public  synchronized static final void method(){}
    public synchronized void transfer(boolean direction, int numToTransfer) {
//    public  void transfer(boolean direction, int numToTransfer) {  非线程安全
        if (direction == RIGHT_TO_LEFT) {
            bucket[0] += numToTransfer;
            bucket[1] -= numToTransfer;

        } else {
            bucket[0] -= numToTransfer;
            bucket[1] += numToTransfer;

        }
        System.out.println("Total: " + (bucket[0] + bucket[1]));
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
                    Thread.sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new BucketBallGame().doTransfers();
    }

}
