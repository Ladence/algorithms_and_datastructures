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
        // deleting from beginning of list
        if (head.data.equals(data)) {
            if (head.next != null) {
                head = head.next;
            } else {
                head = null;
            }
            return;
        }


        ListNode cur = head.next;
        ListNode prev = head;

        while (cur != null) {
            if (cur.data.equals(data)) {
                prev.next = cur.next;
                if (prev.next == null) {
                    tail = prev;
                }
            }
            prev = cur;
            cur = cur.next;
        }
    }

    void printList() {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.data.toString() + " -> ");
            cur = cur.next;
        }

        System.out.println(" X ");
    }

    ListNode<T> search(T data) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.data.equals(data)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}

class ListNode<T> {
    T data;
    ListNode next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode<?> listNode = (ListNode<?>) o;

        if (!data.equals(listNode.data)) return false;
        if (next == null & listNode.next == null) return true;
        return next.equals(listNode.next);
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + next.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                '}';
    }
}
