package LeetCode.Tree;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 21:38
 */
public class LeetCode1028 {
    // 用于标识当前遍历到二叉树的哪个位置
    int start = 0;
    public TreeNode recoverFromPreorder(String traversal) {
        return recover(0,traversal);
    }

    TreeNode recover(int level,String traversal){
        // 分别用于记录当前的值与深度
        int val=0,nextlevel = 0, i;
        TreeNode node = null;
        for(i=start;i<traversal.length();i++){
            if(traversal.charAt(i)=='-') nextlevel++;
            else{
                while(i<traversal.length() && traversal.charAt(i)!='-'){
                    val = val*10 + traversal.charAt(i)-'0';
                    i++;
                }
                break;
            }
        }
        // 当前深度与目标深度不一致时，回溯
        if(level == nextlevel){
            node = new TreeNode();
            node.val = val;
            start = i;
            node.left =  recover(level+1,traversal);
            node.right = recover(level+1,traversal);
        }
        return node;
    }
}
