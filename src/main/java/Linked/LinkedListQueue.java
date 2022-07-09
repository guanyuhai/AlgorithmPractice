package Linked;

import Queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    public class Node{
        public E e;
        public LinkedList.Node next;

        public Node(E e, LinkedList.Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 维护队首
    private LinkedList headList;
    // 维护队尾
    private LinkedList failList;
    private int size;

    public LinkedListQueue() {
        headList = new LinkedList<E>();
        failList = new LinkedList();
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    // 入队操作
    @Override
    public void enqueue(E e) {
        headList.addFirst(e);
        failList.set(0,e);
        size++;
    }

    // 出队操作
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an emtry queue. Illegal index.");
        // 避免
        size--;
        return (E) failList.removeFirst();
    }

    // 看看队首的元素是谁
    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is emtry. Illegal index.");
        return (E) headList.getFirst();
    }
}
