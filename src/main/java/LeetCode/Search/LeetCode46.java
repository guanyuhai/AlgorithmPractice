package LeetCode.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 16:06
 */
public class LeetCode46 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0){
            return result;
        }

        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);
        return result;
    }

    private void backtracking(int[] nums, boolean[] used) {
        // 当收集元素的数组path的大小达到和nums数组一样大的时候，说明找到了一个全排列
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (used[i]) continue; // path里已经收录的元素，直接跳过
            used[i] = true;
            path.addLast(nums[i]);
            backtracking(nums, used);
            // 回溯
            path.removeLast();
            used[i] = false;
        }
    }
}
