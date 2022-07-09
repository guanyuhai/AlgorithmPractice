package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/25 17:19
 */
public class LeetCode104 {
    // 最简单的方案：DFS深度优先+分治
    // 代码虽然简洁，但是耗时比DFS（深度优先）和 BFS（广度优先）耗时久
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.min(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // BFS广度优先搜索，层序遍历
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // 将当前这一层的所有节点都移除
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                if (current.left!=null) queue.add(current.left);
                if (current.right!=null) queue.add(current.right);
            }
        }

        return level;
    }


    // DFS深度优先搜索
    static int maxLevel = 0;
    public static int maxDepth3(TreeNode root) {
        if (root==null) return 0;

        dfs(root, 1);
        return maxLevel;
    }

    public static void dfs(TreeNode root, int level) {
        if (root==null) return;

        if (level > maxLevel) maxLevel = level;
        dfs(root.left, level+1);
        dfs(root.right, level+1);
    }


    private static TreeNode root = new TreeNode();
    public static void main(String[] args) {
        Integer[] datas = new Integer[]{3,9,20,null,null,15,7};

        for (int i = 0; i < datas.length; i++) {
            if (datas[i]!=null) add(datas[i]);
            // 错误写法
            /*int left = 2*i+1;
            int right = 2*i+2;
            if (i>=datas.length || left>=datas.length)
                continue;
            root.val = datas[i];
            if (datas[left]!=null) root.left = new TreeNode(datas[left]);
            if (datas[right]!=null) root.right = new TreeNode(datas[right]);*/
        }

        System.out.println(maxDepth2(root));
        System.out.println(maxDepth3(root));
    }

    private static void add(Integer e) {
        root = add(root,e);
    }

    private static TreeNode add(TreeNode node ,Integer e) {
        if (node == null) {
            return new TreeNode(e);
        }

        if (e < node.val)
            node.left = add(node.left,e);
        else if (e > node.val)
            node.right = add(node.right,e);
        return node;
    }
}

