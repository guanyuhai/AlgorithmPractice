package LeetCode.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 递增子序列
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 15:39
 */
public class LeetCode491 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex) {
        // 终止条件
        // 要求递增子序列大小至少为2
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
            //return;  注意这里不要加return，因为要取树上的所有节点
        }

        // 题目中已经说明数值范围[-100,100]，所以完全可以用数组来做哈希。
        int[] used = new int[201];
        int length = nums.length;
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast() ||
                    (used[nums[i] + 100] == 1)) continue;
            used[nums[i] + 100] = 1;
            path.addLast(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

    // 复习
    private void back(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }

        int[] used = new int[201];
        int length = nums.length;
        for (int i = startIndex; i < length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast() || (used[nums[i + 100]] == 1))
                continue;
            used[nums[i] + 100] = 1;
            path.addLast(nums[i]);
            back(nums, i+1);
            path.removeLast();
        }
    }
}
