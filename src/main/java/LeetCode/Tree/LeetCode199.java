package LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 20:43
 */
public class LeetCode199 {
    public List<Integer> rightSideView(TreeNode root) {
        if (root==null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.left!=null) queue.add(cur.left);
                if (cur.right!=null) queue.add(cur.right);
                if (i == size-1) // 将当前层的最后一个节点放入结果列表
                    res.add(cur.val);
            }
        }

        return res;
    }

    /**
     * 深度优先DFS的方式
     * 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问， 就可以保证每层都是最先访问最右边的节点的。
     * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序遍历每层最先访问的是最左边的节点）
     */
    List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
        if (root==null) return result;

        // 从根节点开始访问，根节点的深度是0
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth) {
        if (root==null) return;

        // 先访问当前节点，再递归访问右子树、左子树
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (depth == result.size()) {
            result.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
