package LeetCode.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/6 16:26
 */
public class LeetCode39 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先进行排序
        combinationSumHelper(candidates, target, 0, 0);
        return result;
    }

    private void combinationSumHelper(int[] candidates, int target, int startIndex, int sum) {
        // 终止条件
        if (sum == target) {
            // 将当前符合条件的结果放入结果集，并返回
            result.add(new ArrayList<>(path));
            return;
        }

        int size = candidates.length;
        for (int i = startIndex; i < size; i++) {
            // 如果 sum + candidates[i] > target 就终止遍历
            if (sum + candidates[i] > target) break;

            path.add(candidates[i]);
            // 关键点：不用i+1，表示可以重复读取当前的值
            combinationSumHelper(candidates, target,i, sum + candidates[i]);
            // 回溯
            path.removeLast();
        }
    }
}
