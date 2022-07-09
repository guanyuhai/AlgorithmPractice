package UnionFind;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/7 22:18
 */
public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank;  // rank[i] 表示以i为根的集合所表示的树的层数

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素p所对应的集合编号
    // O(h) 的复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 当p和数组中p对应的值相同，即找到根节点，根节点自旋
        if (p != parent[p]) {
            // 直接找到最原始的根节点，压缩到底
            // 不断去找父级的根节点
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h) 的复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h) 的复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 让rank低的指向rank高的
        if (rank[pRoot] < rank[qRoot]) {
            // 让pRoot指向qRoot
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {  // == 的时候
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
