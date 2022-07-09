package Linked;

import javafx.util.Pair;

/**
 * 使用递归的方式实现链表
 * @param <E>
 */
public class LinkedList2<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList2() {
        head = null;
        size=0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size==0;
    }

    // 在链表的index（0-based）位置添加行的元素e
    // 在链表中不是一个常用操作，仅供练习
    public void add(int index, E e) {
        if (index<0 || index>size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        // 每次对更小的的链表头追加元素
        head = add(head,index,e);
        size++;
    }

    private Node add(Node node, int index, E e) {
        if (index == 0)
            return new Node(e,node);
        node.next = add(node.next,index-1, e);
        return node;
    }

    // 获取链表的index（0-based）位置添加行的元素e
    // 在链表中不是一个常用操作，仅供练习
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        head = get(head,index);
        return head.e;
    }

    public Node get(Node node, int index) {
        // 如果要获取的元素为第一个元素，则返回第一个Node
        if (index == 0)
            return node;
        node = this.get(node.next,index-1);
        return node.next;
    }


    // 从链表中删除index（0-based）位置的元素e，并返回删除的元素e
    // 在链表中不是一个常用操作，仅供练习
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");

        Pair<Node,E> res = remove(head,index);
        size--;
        head = res.getKey();

        return res.getValue();
    }

    public Pair<Node,E> remove(Node node, int index) {
        if (index==0)
            return new Pair<Node, E>(node.next,node.e);

        Pair<Node,E> res = remove(node.next, index-1);
        node.next = res.getKey();
        return new Pair<Node, E>(node,res.getValue());
    }
}
