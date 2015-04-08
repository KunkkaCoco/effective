package com.effective.generics;


/**
 * Created by chenweichao on 15-4-7.
 */
public class UniverseType<T> {
    T a;
    UniverseType(T a){
        this.a = a;
    }

    public static void main(String[] args) {
        UniverseType<Float> universeType = new UniverseType<Float>(5f);
        UniverseType<Double> utd = new UniverseType<Double>(5.0);

    }
}
