package LeetCode.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和III
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/6 15:48
 */
public class LeetCode216 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSumHelper(n,k, 1,0);
        return result;
    }

    /**
     *
     * @param targetSum     目标和，即n
     * @param k
     * @param sum           已搜集元素的和，即path里的元素总和
     * @param startIndex    下一层for循环搜索的起始位置
     */
    private void combinationSumHelper(int targetSum, int k, int startIndex, int sum) {
        // 剪枝: 如果已选元素总和大于n，后续就无须再遍历
        if (sum > targetSum) {
            return;
        }

        if (path.size() == k) {
            if (sum == targetSum) result.add(new ArrayList<>(path));
            return; // 如果path.size() == k 但sum != targetSum 直接返回
        }

        // 剪枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1 ; i++) {
            path.add(i);
            sum += i;
            combinationSumHelper(targetSum, k, i+1, sum);
            // 回溯
            path.removeLast();
            // 回溯
            sum -= i;
        }
    }
}
