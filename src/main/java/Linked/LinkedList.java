package Linked;

public class LinkedList<E> {
    public class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
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

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null,null);
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
        /*if (index == 0) {
            addFirst(e);
        } else {*/
            Node prev = dummyHead;
            // 找到待插入的节点的前面的那个节点
            // 使用dummyHead后，就不需要对0这个节点做特殊处理
            for (int i = 0; i < index; i++)
                prev = prev.next;

            // 插入元素，把前一个元素的下一个元素指向转移给插入的node节点
            Node node = new Node(e, prev.next);
            prev.next = node;
            size++;
        //}
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/

        // 简写
        /*head = new Node(e,head);
        size++;*/
        add(0,e);
    }

    // 在链表末尾添加元素e
    public void addLast(E e) {
        add(size, e);
    }


    // 获取链表的index（0-based）位置添加行的元素e
    // 在链表中不是一个常用操作，仅供练习
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取链表的最后一个元素
    public E getLast() {
        return get(size-1);
    }

    // 修改链表的index（0-based）位置添加行的元素e
    // 在链表中不是一个常用操作，仅供练习
    public void set(int index,E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 从链表中删除index（0-based）位置的元素e，并返回删除的元素e
    // 在链表中不是一个常用操作，仅供练习
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    // 待验证
    public void removeElent(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node deNode = prev.next;
            prev.next = deNode.next;
            deNode.next = null;
        }
    }

    // 从链表中删除第一个元素，并返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素，并返回删除的元素
    public E removeLast() {
        return remove(size-1);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        /*Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur.e + " -> ");
            cur = cur.next;
        }*/

        // 另一种循环方式
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur.e + " -> ");
        }

        res.append("Null");
        return res.toString();
    }
}
