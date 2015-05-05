package com.effective.exec;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenweichao on 15-5-5.
 */
public class MultipleService {
    public static class Exp implements Callable {

        private double m;
        private int n;

        public Exp(double m, int n) {
            this.m = m;
            this.n = n;
        }

        public Double call() {
            double result =1 ;
            for (int i = 0; i < n; i++) {
                result *= m;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.printf("%nComputed %.02f raised to %d%n", m, n);
            return result;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
        for (int i = 0; i < 10; i++) {
            double m = Math.random()*10;
            int n = (int) (Math.random() * 1000);
            System.out.printf("Created task for computing: " + "%.02f raise to %d\n", m, n);
            tasks.add(new Exp(m, n));
        }

        ExecutorCompletionService service = new ExecutorCompletionService(executor);
        for (Callable<Double> task : tasks) {
            service.submit(task);
        }
        Lock lock = new ReentrantLock();
        for (int i = 0; i < tasks.size(); i++) {
            lock.lock();
            try {
                Double d = (Double) service.take().get();
                System.out.printf("Result: %E%n",d);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
    }
}
