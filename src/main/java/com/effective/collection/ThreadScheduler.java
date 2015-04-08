package com.effective.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by chenweichao on 15-4-8.
 */
public class ThreadScheduler {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        System.out.println("ThreadScheduler.main");
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 10));
        }

        PriorityQueue<Integer> threadQueue = new PriorityQueue<Integer>();
        threadQueue.addAll(list);
        System.out.println("Waiting threads...");
        for (Integer thread : threadQueue) {
            System.out.print(thread + ",");

        }
        System.out.println("\nDeploying threads...");
        while (!threadQueue.isEmpty()) {
            System.out.print(threadQueue.remove() + ",");
        }
    }
}
