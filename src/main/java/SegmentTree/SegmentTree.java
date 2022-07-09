package SegmentTree;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/3 15:44
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4*data.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    /**
     * 在treeIndex的位置创建表示区间 [l, r] 的线段树
     * @param treeIndex     线段树的起点
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 只有一个节点的情况下，可以直接返回
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 不适用 (l+r)/2 是为了避免数值过大导致的整型溢出问题
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1, r);

        // 根据具体业务来，比如本案例要求和，则使用求和方式
        //tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
        // 如果是允许用户设置自己想要的业务，可以通过接口实现
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index) {
        return 2*index+1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index) {
        return 2*index+2;
    }

    /**
     * 返回区间 [queryL, queryR] 的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length
                || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length-1, queryL, queryR);
    }

    // 在以treeID为根的线段树中[l,r] 的范围里，搜索区间[queryL, queryR] 的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 刚好要搜索的节点的左边界与树的左边界一致，右边界与树的右边界一致，则可以直接返回
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 要搜索的范围在右孩子中
        if (queryL >= mid +1 )
            return query(rightTreeIndex, mid+1, r, queryL,queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL,queryR);

        // 既没有完全落在左孩子中，也没有完全落在右孩子中
        E leftResult = query(leftTreeIndex,l, mid, queryL,mid);
        E rightResult = query(rightTreeIndex, mid+1, r,mid+1,queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 将index位置的值，更新为e
    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");

        data[index] = e;
        set(0, 0, data.length-1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 找到要更新的索引位置
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightTreeIndex,mid+1,r,index,e);
        } else {
            set(leftTreeIndex, l, mid,index,e);
        }

        // 相关的祖辈节点都要重新更新
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length-1)
                res.append(", ");
        }
        res.append(']');

        return res.toString();
    }
}
