package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 21:05
 */
public class LeetCode101 {
    // 递归写法
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;

        return cmp(root.left, root.right);
    }

    private boolean cmp(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return cmp(node1.left, node2.right) && cmp(node1.right, node2.left);
    }

    // 迭代的写法
    public boolean isSymmetric2(TreeNode root) {
        if (root==null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            if (leftNode==null && rightNode==null) continue;

            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }

            // 对称的另外两种情况
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }

        return true;
    }
}
