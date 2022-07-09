package LeetCode.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集II
 * 包含重复的元素
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/5 10:55
 */
public class LeetCode90 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        System.out.println(subsetsWithDup(nums));
    }

    /**
     * 实现方式1
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        // 要先排序，不排序的话，顺序不一样，导致结果不一样
        Arrays.sort(nums);

        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int all = results.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(results.get(j));
                tmp.add(nums[i]);
                if (!results.contains(tmp)) results.add(tmp);
            }
        }

        return results;
    }


    /**
     * 实现方式2
     */
    static List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    static LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    static boolean[] used;
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0){
            result.add(path);
            return result;
        }

        Arrays.sort(nums);
        used = new boolean[nums.length];
        subsetsWithDupHelper(nums, 0);
        return result;
    }

    // 使用标记的方法
    private static void subsetsWithDupHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));
        // 递归终止条件
        if (startIndex >= nums.length){
            return;
        }

        for (int i = startIndex; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }

    // 不使用标记的方法
    private void subsetsWithDupHelper2(int[] nums, int startIndex){
        // 获取树的所有节点
        result.add( new ArrayList<>( path ) );

        // 单层递归搜索逻辑
        for ( int i = startIndex; i < nums.length; i++ ) {
            // 跳过当前树层使用过的、相同的元素
            if ( i > startIndex && nums[i - 1] == nums[i] ) {
                continue;
            }
            path.add( nums[i] );
            subsetsWithDupHelper( nums, i + 1 );
            path.removeLast();
        }
    }

}
