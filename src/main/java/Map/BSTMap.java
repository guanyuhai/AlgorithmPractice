package Map;

import BST.BST;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V>  {
    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }


    @Override
    public void add(K key, V value) {
        root = add(root,key, value);
    }

    // 向以Node为根的二分搜索树中插入元素E，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key,V value) {

        if (node == null) {
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else node.value = value;

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node,K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else return getNode(node.right,key);
    }

    private Node minimum(Node node) {
        if (node.left==null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小值节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right=null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if (node != null) {
            root = remove(root,key);
            return node.value; 
        }
        return null;
    }

    // 删除以node为根的二分搜索树中建为key的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null)
            return node;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right=null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left=null;
                size--;
                return leftNode;
            }
            // 待删除的节点均不为空的情况
            // 1 找到比待删除节点要大的最小节点，即待删除节点右子树的最小节点
            // 2 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            // 让right节点链接上右子树删掉最小节点后的根节点
            successor.right = removeMin(node.right);
            // 在removeMin中删除的节点实际还没删除，赋给了successor
            //size++;

            // 让left链接上顶替的那个节点的left
            successor.left = node.left;
            // 删除掉被被顶替的节点的left和right
            node.left = node.right = null;
            // 上一句的语句才是真正的删除了一个节点
            //size--;
            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
