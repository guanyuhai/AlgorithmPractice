package LeetCode.Tree;

/**
 * 验证二叉搜索树
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 21:33
 */
public class LeetCode98 {
    // 80个用例，争取72个
    /*public boolean isValidBST(TreeNode root) {
        if (root==null) return true;

        if (root.left != null) {
            if (root.left.val >= root.val) return false;
            if (!isValidBST(root.left)) return false;
        }
        if (root.right != null) {
            if (root.right.val <= root.val)
                return false;
            if (!isValidBST(root.right))
                return false;
        }
        return true;
    }*/

    // 正式答案
    public boolean isValidBST(TreeNode root) {
        return isValidate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return  isValidate(node.left, min, node.val) && isValidate(node.right, node.val, max);
    }
}
