package LeetCode.Tree;

/**
 *
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 22:25
 */
public class LeetCode110 {
    // 判断是否是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;

        //先获取当前结点的平衡因子
        int balanceFactor = getBalanceFactor(root);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    //辅助函数：计算结点的平衡因子
    private int getBalanceFactor(TreeNode node) {
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);//左子树的高度减去右子树的高度
    }

    //辅助函数，返回每一个结点的高度值（主要是为了处理结点为null的情况，后面就不需要判断结点为null的情况）
    private int getHeight(TreeNode node) {
        //空结点的高度为0
        // 返回树的高度（或者说深度）
        return node == null ? 0 : Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

}
