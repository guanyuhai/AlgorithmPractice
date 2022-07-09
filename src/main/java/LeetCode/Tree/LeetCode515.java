package LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 15:47
 */
public class LeetCode515 {
    public List<Integer> largestValues(TreeNode root) {
        if (root==null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Integer> datas = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.val>max) max = cur.val;
                if (cur.left!=null) queue.add(cur.left);
                if (cur.right!=null) queue.add(cur.right);
            }
            datas.add(max);
        }

        return datas;
    }

    // 复习
    public List<Integer> largest(TreeNode root) {
        if (root==null) return new ArrayList<>();

        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root);

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.val>max) max=cur.val;
                if (cur.left!=null) queue.add(cur.left);
                if (cur.right!=null) queue.add(cur.right);
            }
            res.add(max);
        }

        return res;
    }
}
