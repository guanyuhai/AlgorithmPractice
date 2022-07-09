package LeetCode.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 * 不包含重复的元素
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/5 10:41
 */
public class LeetCode78 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        if (nums == null) return new ArrayList<>();

        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int all = results.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(results.get(j));
                tmp.add(nums[i]);
                results.add(tmp);
            }
        }

        return results;
    }

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        subsetsHelper(nums, 0);
        return result;
    }

    private void subsetsHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件, 可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }



}
