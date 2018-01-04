package ru.ladence.datastructures;

import java.util.ArrayList;

/**
 * Data structure : Queue
 * Based on array (using ArrayList collection)
 * Using generics.
 * @author Zakhar Karlin
 * @since 24.12.2017
 */
public class Queue<T> {
   ArrayList<T> arrayList;
   T head;
   T tail;

   Queue() {
       arrayList = new ArrayList<>();
       head = null;
       tail = null;
   }

   void push(T element) {
       arrayList.add(element);
       // if was empty queue
       if (arrayList.size() == 1) {
           head = element;
       }
       tail = element;
   }

   T pull() {
       T result = head;
       arrayList.remove(head);

       if (arrayList.size() != 0) {
           head = arrayList.get(0);
       }
       return result;
   }

   int size() {
       return arrayList.size();
   }
}
