package LeetCode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和2
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 15:09
 */
public class LeetCode113 {
    // 记录所有路径和的全局变量
    private List<List<Integer>> paths = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> current = new ArrayList<>();
        queryPathSum(root,current,0, targetSum);
        return paths;
    }

    /**
     * 找到每一个路径和
     * @param node
     * @param current
     * @param sum       每次递归的和
     * @param target    最终的要求的值
     */
    private void queryPathSum(TreeNode node, List<Integer> current, int sum, int target) {
        if (node == null) return;

        // 递归到底的情况, 即到达最后一个子树（最小子树）
        if (node.left == null && node.right == null && (node.val + sum) == target) {
            current.add(node.val);
            paths.add(new ArrayList<>(current));
            current.remove(current.size()-1);
            return;
        }

        current.add(node.val);
        queryPathSum(node.left, current, sum+node.val, target);
        queryPathSum(node.right, current, sum+node.val, target);
        current.remove(current.size()-1);
    }

    // 复习
    private void query(TreeNode node, List<Integer> current, int sum, int target) {
        if (node==null) return;

        if (node.left == null && node.right == null && (node.val + sum) == target) {
            current.add(node.val);
            paths.add(new ArrayList<>(current));
            current.remove(current.size()-1);
            return;
        }

        current.add(node.val);
        query(node.left, current, sum+node.val, target);
        query(node.right, current, sum+node.val, target);
        current.remove(current.size()-1);
    }
}
