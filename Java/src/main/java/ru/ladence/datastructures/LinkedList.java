package ru.ladence.datastructures;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private ListNode<T> head, tail;

    void add(T data) {
        ListNode<T> insertable = new ListNode<>(data);

        if (head == null) {
            head = insertable;
            tail = head;
        } else {
            tail.setNext(insertable);
            tail = tail.getNext();
        }
    }

    void remove(T data) {
        // deleting from beginning of list
        if (head.getData().equals(data)) {
            if (head.getNext() != null) {
                head = head.getNext();
            } else {
                head = null;
            }
            return;
        }


        ListNode cur = head.getNext();
        ListNode prev = head;

        while (cur != null) {
            if (cur.getData().equals(data)) {
                prev.setNext(cur.getNext());
                if (prev.getNext() == null) {
                    tail = prev;
                }
            }
            prev = cur;
            cur = cur.getNext();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T token : this) {
            stringBuilder.append(token.toString()).append(" ");
        }
        return stringBuilder.toString();
    }

    ListNode<T> search(T data) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.getData().equals(data)) {
                return cur;
            }
            cur = cur.getNext();
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }


    private class LinkedListIterator<T> implements Iterator<T> {
        private ListNode<T> current;

        LinkedListIterator(LinkedList list) {
            current = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T ret = current.getData();
                current = current.getNext();
                return ret;
            }
        }
    }
}


class ListNode<T> {
    private T data;
    private ListNode next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
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

    public ListNode getNext() {
        return next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
