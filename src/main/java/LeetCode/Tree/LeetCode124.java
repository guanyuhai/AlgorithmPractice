package LeetCode.Tree;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 20:01
 */
public class LeetCode124 {
    // 记录所有路径和的全局变量
    private int ret = Integer.MIN_VALUE;

    /**
     * 思路：后序遍历
     * 三种情况
     * 自底向上的方案
     * 1 左：左孩子向上找到父节点，父节点不继续向上（这里将父节点当成祖父节点的左/右孩子）
     * 2 右：右孩子向上找到父节点，父节点不继续向上
     * 3 左中右：左孩子向上找到父节点，父节点继续找到右孩子
     * 4 特殊的，当前单个节点作为一个路径
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后 向父节点回溯构成最大路径
         2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
         **/
        getMax(root);
        return ret;
    }

    // 拿到一边，包括该root的最大sum，即左/右的情况
    // 如果左/右孩子的值为负数，则不加进最大路径
    private int getMax(TreeNode r) {
        if(r == null) return 0;
        // 为求最大值，尽可能舍弃负数，但必经的路径不能舍弃
        // 如果所有节点都为负数，那就只是用当前节点作为最大和
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        // 更新一下全局变量
        ret = Math.max(ret, r.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和

        // 返回最大的一个节点树
        return Math.max(left, right) + r.val;
    }

    // 复习，获取最大路径和
    private int max(TreeNode root) {
        if (root==null) return 0;

        int left = Math.max(0, max(root.left));
        int right = Math.max(0, max(root.right));
        ret = Math.max(ret, root.val+left+right);

        return Math.max(left,right) + root.val;
    }
}



