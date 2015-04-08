package com.effective.generics;

/**
 * Created by chenweichao on 15-4-7.
 */
public class NumberStack<T extends Number> {
    T stack[];

    NumberStack(T[] stack) {
        this.stack = stack;
    }

    public static void main(String[] args) {
        Double[] doubleArray = new Double[100];
        NumberStack<Number> doubleStack = new NumberStack<Number>(doubleArray);


//  编译不通过  illegal
//     NumberStack<Double> stack = new NumberStack<Double>[10];
    }
}
