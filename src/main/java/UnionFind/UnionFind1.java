package UnionFind;

/**
 * 这里只是用数组做模拟
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/5 21:35
 */
public class UnionFind1 implements UF{
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];

        // 初始给每个id赋予不同的值，表示互不相连
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素p 所对应的集合编号，复杂度为O(1), quickFind
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");

        return id[p];
    }

    // 查看元素p和元素q是否所属一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {

        int pId = find(p);
        int qId = find(q);

        // 如果属于同一集合，就没必要再做相连操作
        if (pId == qId)
            return;

        // 这样遍历的复杂度为O(N)
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId)
                id[i] = qId;
        }
    }
}
