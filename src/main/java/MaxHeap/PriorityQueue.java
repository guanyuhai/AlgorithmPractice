package MaxHeap;

import Queue.Queue;

/**
 * 优先队列实现
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmtpy();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return (E) maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return (E) maxHeap.findMax();
    }
}
