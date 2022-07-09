package Queue;

import org.omg.CORBA.Object;

/**
 * 不使用Size属性的LoopQueue
 */
public class LoopQueueNotSize<E> implements Queue<E> {
    private E[] data;
    private int front,tail;

    public LoopQueueNotSize(int capacity) {
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
    }

    public LoopQueueNotSize() {
        this(10);
    }

    private int getCapacity() {
        return data.length-1;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }
}
