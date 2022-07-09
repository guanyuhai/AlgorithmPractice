package AVLTree;

import java.util.ArrayList;

/**
 * AVL平衡二叉树其实就是在之前的二分搜索树的基础上实现的，其实就是在二分搜索树的基础上添加上自平衡的机制，
 * 使得二分搜索树在堆结点进行操作的时候，可以保证整棵树是平衡的（即每一个结点左右子树的高度差不超过1），不会退化。
 *
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/8 10:52
 */
public class AVLTree<K extends Comparable<K>, V> {
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 初始默认高度为1
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    //辅助函数，返回每一个结点的高度值（主要是为了处理结点为null的情况，后面就不需要判断结点为null的情况）
    private int getHeight(Node node)
    {
        if(node == null)
            return 0;//空结点的高度为0
        return node.height;
    }

    //辅助函数：计算结点的平衡因子
    private int getBalanceFactor(Node node) {
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);//左子树的高度减去右子树的高度
    }

    /** AVL 树既要满足平衡二叉树的特性（所有结点左右子树高度差小于1），又要满足二分搜索树的特性
     * （AVL树是改进二分搜索树，引入平衡因子，使得二分搜索树不会退化为链表） */
    // 辅助函数：判断该二叉树是否是一棵二分搜索树（利用二分搜索树中序遍历是升序的机制）
    private boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();//用于存储所有结点的key
        inOrder(root , keys);//利用中序遍历，将以root为根的树的所有结点添加到keys中

        for (int i = 1; i < keys.size(); i++)
        {
            if(keys.get(i-1).compareTo(keys.get(i)) > 0)//如果中序遍历后keys的key中不是升序，则该树不是二分搜索树
                return false;
        }
        return true;
    }
    //中序遍历的方法
    private void inOrder(Node node , ArrayList<K> keys) {
        if(node == null)
            return;
        inOrder(node.left , keys);
        keys.add(node.key);
        inOrder(node.right , keys);
    }

    // 辅助函数：判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {
        if(node == null)
            return true;
        //先获取当前结点的平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    //将以y为根的树右旋转，返回右旋转后的树的根结点
    private Node rightRotate(Node y)
    {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        //更新结点的height：只有y结点与x结点的高度有可能发生变化，因此更新x与y的高度即可（必须先更新y，再更新x）
        y.height = 1 + Math.max(getHeight(y.left) , getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left) , getHeight(x.right));

        return x;//返回更新后的树的根结点
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y)
    {
        Node x = y.right;
        Node T2 = x.left;

        //向左旋转
        x.left = y;
        y.right = T2;

        //更新x，y的高度（同样先更新y，后更新x）
        y.height = 1 + Math.max(getHeight(y.left) , getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left) , getHeight(x.right));

        return x;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    /**
     添加结点的时候需要维护相应结点的高度值
     */
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);//递归到底添加新结点，默认初始化高度为1，没问题
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        /**
         当前node的高度值有可能因为结点的添加发生变化，也有可能因为结点没有添加，高度值不变，
         因此这里添加后需要更新当前结点的高度值：node.height = 左右子树的高度最大值加1
         */
        node.height = 1 + Math.max(getHeight(node.left) , getHeight(node.right));

        /**添加结点并跟新每一个结点的高度值后，计算平衡因子，
         如果某个结点的平衡因子绝对值大于1，说明当前二叉树不是平衡二叉树，给出提示 */
        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor) > 1)
//            System.out.println("unbalanced : " + balanceFactor);

        /** 平衡维护
         分为 LL、RR、LR、RL 4种情况！
         */
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            //将以当前结点为根的树右旋转，直接返回右旋转后的树的根结点给上一层递归（此时返回的树满足二分搜索树和平衡二叉树性质）
            return rightRotate(node);

        //当整棵树都向右倾斜，我们将整棵树左旋转（RR）
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        //LR情况
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0)
        {
            //先将node结点的左孩子结点左旋转，并将旋转后的树的根接到node的左边，处理后转为LL的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);//最后将node结点右旋转并返回即可！
        }

        //RL情况
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0)
        {
            //将node的右孩子结点右旋转，处理后转为RR的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //--------------------------------------------------------------------------
    // 返回以node为根节点的二分搜索树中，key所在的节点
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

    //--------------------------------------------------------------------------
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    /**
     // 删除掉以node为根的二分搜索树中的最小节点
     // 返回删除节点后新的二分搜索树的根
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
     */

    // 从二分搜索树中删除键为key的节点
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

        // 存储要返回的node
        Node retNode;
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0
            /** 下面使用elseif，使得这几个过程互斥，否则有可能重复执行删除而出错。之前是直接删除后return不会出错 */
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                Node successor = minimum(node.right);
                /**
                 此处，removeMin这个方法可能打破整棵树的平衡性，因此我们需要在removeMin()方法中维护整棵树的平衡。
                 我们也可以通过递归调用 remove的方法，来删除node结点右子树的最小结点（其实这个最小结点就是successor）,
                 这样就不需要单独在removeMin()中添加平衡维护的方法。
                 */
                //            successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;

                retNode = successor;
            }
        }

        //删除后，得到的retNode可能为null，这样我们就需要对这种情况进行判断，否则下面可能出现空指针异常
        if(retNode == null)
            return null;//删除结点后返回新的树的根结点为null，那么肯定不需要平衡维护，直接返回null即可

        /**
         我们将删除相应结点后当前树的根结点保存到retNode中，删除相应结点后，当前树可能受影响而使得
         以当前结点为根的树变得不平衡，即当前结点的平衡因子大于1或者小于-1，此时需要对当前结点进行平衡的维护。
         整个维护的过程和添加结点的维护过程是一样的！
         */
        //更新当前结点的高度
        retNode.height = 1 + Math.max(getHeight(retNode.left) , getHeight(retNode.right));

        //计算当前结点的平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //进行4种情况的处理
        //LL（右旋转当前结点）
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        //RR（左旋转当前结点）
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0)
        {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0)
        {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        /*if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree.AVLTree<String, Integer>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());
            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }*/

        System.out.println();
    }
}
