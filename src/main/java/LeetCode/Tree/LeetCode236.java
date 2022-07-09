package LeetCode.Tree;

/**
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 16:00
 */
public class LeetCode236 {
    int p_index;
    int q_index;

    /**
     * 考虑一个节点是另一个节点的情况
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
         注意p,q必然存在树内, 且所有节点的值唯一!!!
         递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
         表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
         1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
         2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
         3. 左右子树返回值均为null, p和q均不在树中, 返回null
         **/
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null) return null;
        else if(left != null && right != null) return root;
        else return left == null ? right : left;

    }

    // 复习
    public TreeNode lowest(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return root;
        // 其中一个节点是另一个节点的祖先的情况
        if (root == p || root==q) return root;

        TreeNode left = lowest(root.left, p, q);
        TreeNode right = lowest(root.right, p, q);
        if (left == null && right == null) return null;
        else if (left!=null && right!=null) return root;
        else return left==null?right:left;
    }

    /*private int parent(int index){
        if (index==0)
            throw new IllegalArgumentException("index-0 doesn't hava parent");
        return (index - 1) / 2;
    }

    private int searchIndex(TreeNode root, int rootIndex,TreeNode node) {
        if (root==null) return 0;

        if (root.left.val==node.val)
            return rootIndex*2+1;
        if (root.right.val==node.val)
            return rootIndex*2+2;

        int leftChildIndex = searchIndex(root.left,rootIndex*2+1, node);
        int rightChildIndex = searchIndex(root.right, rootIndex*2+2, node);

        return 0;
    }*/
}
