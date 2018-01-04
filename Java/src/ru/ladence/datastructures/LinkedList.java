package ru.ladence.datastructures;


public class LinkedList<T> {
    ListNode<T> head, tail;

    void add(T data) {
        ListNode<T> insertable = new ListNode<>(data);
        if (head == null) {
            head = insertable;
            tail = head;
        } else {
            tail.next = insertable;
            tail = tail.next;
        }
    }

    void remove(T data) {
        if (head.data.equals(data)) {
            if (head.next != null) {
                head = head.next;
            } else {
                head = null;
            }
        }

        ListNode cur = head.next;
        ListNode prev = head;

        while (cur.next != null) {
            if (cur.data.equals(data)) {
                prev.next = cur.next;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    void printList() {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.data + " -> ");
            cur = cur.next;
        }

        System.out.println(" X ");
    }

    boolean search(T data) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.data.equals(data)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
}

class ListNode<T> {
    T data;
    ListNode next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}
