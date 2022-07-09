package LeetCode.Search;

import java.util.*;

/**
 * 组合总和II
 * 要求元素不能重复使用
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/6 16:47
 */
public class LeetCode40 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    Deque<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    int sum = 0;

    /**
     * 使用标记的方式
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        //加标志数组，用来辅助判断同层节点是否已经遍历
        boolean[] flag = new boolean[candidates.length];
        combinationSumHelper(candidates, target, 0, flag);
        return result;
    }

    private void combinationSumHelper(int[] candidates, int target, int startIndex, boolean[] flag) {
        // 终止条件
        if (sum == target) {
            // 将当前符合条件的结果放入结果集，并返回
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && candidates[i]+sum <= target; i++) {
            //出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            sum += candidates[i];
            path.push(candidates[i]);
            combinationSumHelper(candidates, target,i+1, flag);
            // 回溯
            int tmp = path.pop();
            flag[i] = false;
            sum -= tmp;
        }
    }


    /**
     * 不使用标记的方式
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort( candidates );
        backTracking( candidates, target, 0 );
        return result;
    }

    private void backTracking( int[] candidates, int target, int start ) {
        if ( sum == target ) {
            result.add( new ArrayList<>( path ) );
            return;
        }
        for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
            if ( i > start && candidates[i] == candidates[i - 1] ) {
                continue;
            }

            sum += candidates[i];
            path.add( candidates[i] );
            // i+1 代表当前组内元素只选取一次
            backTracking( candidates, target, i + 1 );

            int temp = path.getLast();
            sum -= temp;
            path.removeLast();
        }
    }
}
