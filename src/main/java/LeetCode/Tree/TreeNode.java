package LeetCode.Tree;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 20:06
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
