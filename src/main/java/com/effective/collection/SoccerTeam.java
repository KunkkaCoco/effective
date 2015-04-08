package com.effective.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by chenweichao on 15-4-8.
 */
public class SoccerTeam {
    public static void main(String[] args) {
        List<String> maleTeam = new LinkedList<String>();
        maleTeam.add("John");
        maleTeam.add("Tom");
        maleTeam.add("Sam");
        maleTeam.add("Vijay");
        maleTeam.add("Anthony");
        System.out.println("Male Team:" + maleTeam);

        List<String> femaleTeam = new LinkedList<String>();
        femaleTeam.add("Cathreine");
        femaleTeam.add("Mary");
        femaleTeam.add("Shilpa");
        femaleTeam.add("Jane");
        femaleTeam.add("Anita");
        System.out.println("Female Team:" + femaleTeam);

        ListIterator<String> maleLstIterator = maleTeam.listIterator();
        Iterator<String> femaleListIterator = femaleTeam.listIterator();

        while (femaleListIterator.hasNext()) {
            if (maleLstIterator.hasNext()) {
                maleLstIterator.next();
            }
            maleLstIterator.add(femaleListIterator.next());
        }

        System.out.println("Mixed Team:"+ maleTeam);

        List<String> disqualify = new LinkedList<String>();
        disqualify.add("Sam");
        disqualify.add("Tom");
        disqualify.add("Shilpa");
        maleTeam.removeAll(disqualify);
        System.out.println("Qualified Team:"+ maleTeam);


    }
}
