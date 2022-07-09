package LeetCode.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化的反序列化
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 16:29
 */
public class LeetCode297 {
    // 正确方案
    public String serialize(TreeNode root) {      //用StringBuilder
        StringBuilder res = ser_help(root, new StringBuilder());
        return res.toString();
    }

    public StringBuilder ser_help(TreeNode root, StringBuilder str){
        if(null == root){
            str.append("null,");
            return str;
        }
        str.append(root.val);
        str.append(",");
        str = ser_help(root.left, str);
        str = ser_help(root.right, str);
        return str;
    }

    public TreeNode deserialize(String data) {
        String[] str_word = data.split(",");
        List<String> list_word = new LinkedList<String>(Arrays.asList(str_word));
        return deser_help(list_word);
    }

    public TreeNode deser_help(List<String> li){
        if(li.get(0).equals("null")){
            li.remove(0);
            return null;
        }
        TreeNode res = new TreeNode(Integer.valueOf(li.get(0)));
        li.remove(0);
        res.left = deser_help(li);
        res.right = deser_help(li);
        return res;
    }




    // Encodes a tree to a single string.
    /*public String serialize(TreeNode root) {
        if (root==null) return "null";

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        StringBuilder serialize = new StringBuilder();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur == null) {
                    serialize.append("null,");
                    continue;
                } else {
                    serialize.append(cur.val);
                    serialize.append(",");
                }
                if (cur.left!=null) queue.add(cur.left);
                else queue.add(null);
                if (cur.right!=null) queue.add(cur.right);
                else queue.add(null);
            }
        }
        return serialize.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;

        String[] str_word = data.split(",");
        List<String> list_word = new LinkedList<String>(Arrays.asList(str_word));

        TreeNode root = new TreeNode();
        root.val=Integer.valueOf(list_word.get(0));
        for (int i = 1; i < list_word.size(); i++) {
            String value = list_word.get(i);
            if (i == (i - 1) * 2 + 1) {
                if (value.equals("null")) {
                    root.left = null;
                    continue;
                }
                root.left = new TreeNode(Integer.valueOf(value));
            }
            if (i == (i - 1) * 2 + 2) {
                if (value.equals("null")) {
                    root.right = null;
                    continue;
                }
                root.right = new TreeNode(Integer.valueOf(value));
            }
        }

        return root;
    }*/
}
