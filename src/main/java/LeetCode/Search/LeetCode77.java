package LeetCode.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/6 15:20
 */
public class LeetCode77 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * n为 n叉，k为 k 层
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex) {
        // 终止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n - (k - path.size()) + 1 ; i++) {
            path.add(i);
            combineHelper(n, k, i+1);
            path.removeLast(); // 回溯撤销操作
        }
    }

    // 复习
    private void comb(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n-(k-path.size())+1; i++) {
            path.add(i);
            comb(n, k, i+1);
            path.removeLast();
        }
    }
}
