package RedBlackTree;

import java.util.ArrayList;

/**
 红黑树也是基于二分搜索树实现的！
 */
public class RBTree<K extends Comparable<K>, V> {

    //为了避免记红色与黑色的Boolean值，定义2个常量
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;//用于表示结点的颜色

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            /**
             初始化一个结点的颜色为红色：由于2-3树在添加结点的时候，新的结点永远不会添加到空的位置，而只会和我们最后找到的叶子结点做融合。
             我们新添加一个结点的时候，这个结点总要与某一个叶子结点进行融合（如果这个结点不是树的第一个结点）成为一个新的3/4结点，
             而红黑树中，红色结点表示它与他的父亲结点进行融合。
             即新添加的结点始终是要和某个结点进行融合，那么我们将新添加的结点设置为红色！代表新结点要在红黑树中对应的2-3树中的某一个结点进行融合！
             */
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    // 辅助函数：判断节点node的颜色
    private boolean isRed(Node node)
    {
        //如果结点为null，定义它是黑色的，那么返回BLACK=false，不是红色
        if(node == null)
            return BLACK;
        return node.color;//否则，直接返回node的颜色：RED=true表示是红色；BLACK=false表示不是红色
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    //当心添加的结点（红色）添加到叶子结点的右侧，需要对该叶子结点进行左旋转（逆时针）
    //此处x为新添加结点，而node为x的父节点
    private Node leftRotate(Node node)
    {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;

        //改变node与x颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        //右旋转
        node.left = x.right;
        x.right = node;

        //维护颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }


    //颜色翻转:Node为黑色，其左节点为红色，新添加的红色结点在其右侧
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //------------------------------------------------------------

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //------------------------------------------添加
    // 向红黑树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;// 1、必须将最终根节点设置为黑色节点
    }

    // 向以node为根的红黑树中插入元素(key, value)，递归算法
    // 返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value)
    {
        if(node == null){
            size ++;
            return new Node(key, value);//默认插入红色结点
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        /**
         以当前结点为根的树添加完结点后，需要对当前结点进行红黑树的维护。
         注意，下面3个过程包含我们之前分析的5类特殊情况（根结点为黑色我们在上面维护），
         且这几个过程不是互斥的，有可能都执行，而且顺序不可变。

         这整个过程会从底层向上传递，不断维护红黑树的性质
         */
        //当前结点为黑色或者红色，右节点为红色，左结点为黑色，将当前结点左旋转
        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);//注意将旋转后新的根结点赋予node

        //当前结点的左孩子，以及左孩子的左孩子都是红色，将当前结点右旋转
        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);//注意将旋转后新的根结点赋予node

        //当前结点的左右孩子都是红色，将当前结点进行颜色交换
        if(isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }
//------------------------------------------

    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        /*if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }*/

        System.out.println();
    }
}


