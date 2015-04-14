package com.effective.thread;

/**
 * Created by chenweichao on 15-4-9.
 */
public class PrinmeNumberGenerator {
    public static void main(String[] args) {


        Thread png = new Thread(new WorkerThread());
        png.setDaemon(true);
        png.start();

//        Thread png1= new Thread(new WorkerThread());
//        png1.setDaemon(true);
//        png1.start();

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getStackTrace());
        System.out.printf("Number of active threads in the %s group equals %d%n", png.getThreadGroup().getName(), Thread.activeCount());

        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            System.out.printf("%s\tpriority:%d%n", thread.getName(), thread.getPriority());
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerThread implements Runnable {

    public void run() {
        long i = 1;
        while (true) {
            long j;
            for (j = 2; j < i; j++) {
                long n = i % j;
                if (n == 0) {
                    break;
                }
            }
            if (i == j) {
                System.out.println(Thread.currentThread().getName()+" . . ." + i);
            }
            i++;

            if (Thread.interrupted()) {
                System.out.println("\nStopping prime number generator");
                return;
            }
        }
    }
}