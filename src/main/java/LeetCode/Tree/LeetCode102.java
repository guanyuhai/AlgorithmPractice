package LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 20:32
 */
public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                res.add(current.val);
                if (current.left!=null)
                    queue.add(current.left);
                if (current.right!=null)
                    queue.add(current.right);
            }
            result.add(res);
        }
        return result;
    }

    // 复习
    public List<List<Integer>> level(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>>  results = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                res.add(cur.val);
                if (cur.left!=null) queue.add(cur.left);
                if (cur.right!=null) queue.add(cur.right);
            }
            results.add(res);
        }

        return results;
    }
}
