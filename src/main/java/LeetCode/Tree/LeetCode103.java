package LeetCode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 21:35
 */
public class LeetCode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, res, 0);
        return res;
    }

    private void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }

        // &是按位与，计算的时候转为二进制计算， 都是1 结果位上才是1.
        // 即奇数的时候，当前层倒着添加
        if ((level & 1) == 1){
            res.get(level).add(0, root.val);
        } else { // 偶数的时候，正着添加
            res.get(level).add(root.val);
        }

        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }
}
