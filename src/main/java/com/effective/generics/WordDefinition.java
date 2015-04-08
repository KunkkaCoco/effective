package com.effective.generics;

/**
 * Created by chenweichao on 15-4-7.
 */
public class WordDefinition<W, M> {
    //   static W word; illegal
    W word;
    M meaning;

    W get() {
        return word;
    }

    static void showMeaning() {
//        System.out.println(meaning);//illegal
        System.out.println("meaning");
    }
}
