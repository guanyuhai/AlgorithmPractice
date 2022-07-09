package UnionFind;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/5 21:54
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
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
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
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

        parent[pRoot] = qRoot;
    }
}
