package UnionFind;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/5 21:30
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
