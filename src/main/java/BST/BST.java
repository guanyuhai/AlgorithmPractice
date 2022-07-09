package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(E e) {
        root = add(root,e);
    }

    // 向以Node为根的二分搜索树中插入元素E，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left,e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right,e);
        return node;
    }

    // 查询二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root,e);
    }

    // 看以node为根的二分搜索树中是否包含元素e，递归算法
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;

        if (e.compareTo(node.e)==0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else return contains(node.right,e);
    }

    // 二分搜索树的前序遍历
    private void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的前序遍历，非递归写法
    public void preOrderNR() {
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right!=null)
                stack.push(cur.right);
            if (cur.left!=null)
                stack.push(cur.left);
        }
    }

    // 层序遍历，广度优先
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left!=null)
                queue.add(cur.left);
            if (cur.right!=null)
                queue.add(cur.right);
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node==null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 寻找二分搜索树中的最小值
    public E minimum() {
        if(size==0)
            throw new IllegalArgumentException("BST is emtpy");
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left==null)
            return node;
        return minimum(node.left);
    }

    public E maximum() {
        if(size==0)
            throw new IllegalArgumentException("BST is emtpy");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right==null)
            return node;
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在的节点，返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
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

    // 从二分搜索树中删除最大值所在的节点，返回最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大值节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left=null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root,e);
    }

    // 删除以node为根的二分搜索树中值为e的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null)
            return node;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
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
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的秒速二叉树的字符串
    private void generateBSTString(Node root, int depth, StringBuilder res) {
        if (root == null) {
            res.append(generateDepthString(depth) + "null\n");
        }

        res.append(generateDepthString(depth) + root.e + "\n");
        generateBSTString(root.left,depth+1,res);
        generateBSTString(root.right,depth+1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    /** 向二分搜索树中添加新的元素e，非递归写法 */
    public void add2(E e){

        // 对二分搜索树是空的情况特殊处理
        // 此时，直接让 root 指向新的节点即可
        if(root == null){
            root = new Node(e);
            size ++;
            return;
        }

        // 用 p 来跟踪待插入节点的上一个节点
        // p 的作用相当于链表插入节点时，pre 的作用
        Node p = root;
        while(p != null){

            // 如果待插入的值小于当前 p 节点的值
            // 说明新插入的值要放在 p 的左子树
            if(e.compareTo(p.e) < 0){
                // 如果 p 的左子树为空，则在 p.left 上放入新的节点
                if(p.left == null){
                    p.left = new Node(e);
                    size ++;
                    return; // 注意这里直接 return
                }

                // 否则 p = p.left
                p = p.left;
            }
            // 如果待插入的值大于当前 p 节点的值
            // 说明新插入的值要放在 p 的右子树
            else if(e.compareTo(p.e) > 0){
                // 如果 p 的右子树为空，则在 p.right 上放入新的节点
                if(p.right == null){
                    p.right = new Node(e);
                    size ++;
                    return; // 注意这里直接 return
                }

                // 否则 p = p.right
                p = p.right;
            }
            // 如果待插入的值等于当前 p 节点的值，说明二分搜索树中已经有这个值了
            // 直接 return
            else return;
        }
    }
}
