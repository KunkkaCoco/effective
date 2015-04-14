package com.effective.thread;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chenweichao on 15-4-9.
 */
public class ControlledPrimeNumberGenerator {
    public static void main(String[] args) {


        Thread png = new Thread(new WorkerThread());
        png.start();

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getStackTrace());
        System.out.printf("Number of active threads in the %s group equals %d%n", png.getThreadGroup().getName(), Thread.activeCount());

        InputStreamReader is = new InputStreamReader(System.in);


        try {
            while (is.read() != '\n') {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        png.interrupt();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (png.isInterrupted()) {
            System.out.println("\nNumber generation has already been interrupted");
        } else {
            System.out.println("Number generator is not currently running");
        }

        Thread lazyWorker = new Thread(new LazyWorker());
        lazyWorker.start();
        System.out.println("\nRunning lazy worker");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lazyWorker.interrupt();
    }
}

class LazyWorker implements Runnable {

    public void run() {

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException ex) {
            System.out.println("Lazy worker:"+ ex.toString());
            Thread.interrupted();
        }
    }
}