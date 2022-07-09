package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 15:06
 */
public class LeetCode111 {
    // 递归实现方式
    public int minDepth(TreeNode root) {
        if (root==null) return 0;

        // null节点不参与比较
        if (root.left==null && root.right!=null)
            return 1+minDepth(root.right);
        if (root.right==null && root.left!=null)
            return 1+minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // DFS深度优先实现方式
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        //左、右子树深度
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //左(右)子树为空，深度为右(左)子树深度+当前节点深度1
        if(left == 0) return right + 1;
        if(right == 0) return left + 1;
        //左、右子树不为空，深度为左、右子树深度较小者+当前节点深度1
        return Math.min(left, right) + 1;
    }


    // 未正确实现的
    public int minDepth3(TreeNode root) {
        if (root==null) return 0;

        Queue<TreeNode> queue1 = new LinkedList();
        Queue<TreeNode> queue2 = new LinkedList();
        if (root.left!=null) queue1.add(root.left);
        if (root.right!=null) queue2.add(root.right);

        // 获取左子树的高度
        int left_depth = 1;
        while (!queue1.isEmpty()) {
            int size = queue1.size();
            left_depth++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue1.remove();
                if (cur.left!=null) queue1.add(cur.left);
                if (cur.right!=null) queue1.add(cur.right);
            }
        }

        // 获取右子树的高度
        int right_depth = 1;
        while (!queue2.isEmpty()) {
            int size = queue2.size();
            right_depth++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue2.remove();
                if (cur.left!=null) queue2.add(cur.left);
                if (cur.right!=null) queue2.add(cur.right);
            }
        }

        // 如果左子树都为空，则返回右子树的高度
        if (left_depth==1) return right_depth;
        else if (right_depth==1) return left_depth;

        return Math.min(left_depth, right_depth);
    }
}
