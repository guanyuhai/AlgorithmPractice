package LeetCode.Tree;

/**
 * 删除二叉搜索树的节点
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 16:43
 */
public class LeetCode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null) return root;

        // 如果当前要删除的值小于根节点的值，则向左子树寻找
        if (key < root.val) {
            // 将根节点的左节点连接到删除后的节点
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            // 如果左子树为空，则删除右子树
            if (root.left == null) {
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            } else if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }
            // 待删除的节点均不为空的情况
            // 1 找到比待删除节点要大的最小节点，即待删除节点右子树的最小节点
            // 2 用这个节点顶替待删除节点的位置
            TreeNode successor = minimum(root.right);
            // 让right节点链接上右子树删掉最小节点后的根节点
            successor.right = removeMin(root.right);
            // 在removeMin中删除的节点实际还没删除，赋给了successor

            // 让left链接上顶替的那个节点的left
            successor.left = root.left;
            // 删除掉被被顶替的节点的left和right
            root.left = root.right = null;
            // 上一句的语句才是真正的删除了一个节点
            return successor;
        }
    }

    // 寻找二分搜索树中的最小值
    private TreeNode minimum(TreeNode node) {
        if (node.left==null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小值节点
    // 返回删除节点后新的二分搜索树的根
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right=null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }
}
