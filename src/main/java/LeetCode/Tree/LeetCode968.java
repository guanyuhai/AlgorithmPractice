package LeetCode.Tree;

/**
 * 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 21:05
 */
public class LeetCode968 {
    private int monitorCount = 0;

    // 每个节点有三种状态
    public int minCameraCover(TreeNode root) {
        if (root==null) return 0;

        if (dfs(root)==2) monitorCount++;
        return monitorCount;
    }

    // 0 该节点安装了监控；1 该节点可被监控，但未安装监控；2 该节点不可被监控
    private int dfs(TreeNode root) {
        if (root==null) return 1;

        int left = dfs(root.left);
        int right = dfs(root.right);
        // 1 处于不可被监控到的情况时，要增加一个监控
        if (left == 2 || right == 2) {
            monitorCount++;
            return 0;
        } else if (left == 0 || right == 0) { // 处于安装了监控的情况下，表示当前节点可被监控
            return 1;
        } else return 2;
    }

    // 复习
    private int help(TreeNode root) {
        if (root==null) return 1;

        int left = help(root.left);
        int right = help(root.right);
        if (left == 2 || right == 2) {
            monitorCount++;
            return 0;
        } else if (left == 0 || right == 0) {
            return 1;
        } else return 2;
    }
}
