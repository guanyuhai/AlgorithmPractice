package LeetCode.Tree;

/**
 * 求根节点到叶子节点数字之和
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 20:50
 */
public class LeetCode129 {
    static int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        childSum(0, root);
        return sum;
    }
    public static void  childSum(int val, TreeNode root) {
        if(root == null) return;
        int k = (val * 10 + root.val) ;
        if(root.left == null && root.right == null) {
            sum += k;
        }
        childSum(k, root.left);
        childSum(k, root.right);
    }

    // 复习
    public void sum(int val, TreeNode root) {
        if (root == null) return;
        int k = (val*10 + root.val);
        if (root.left == null && root.right == null) {
            sum += k;
        }
        sum(k, root.left);
        sum(k, root.right);
    }
}
