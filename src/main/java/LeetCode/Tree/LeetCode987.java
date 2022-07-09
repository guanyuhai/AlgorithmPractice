package LeetCode.Tree;

import java.util.*;

/**
 * 二叉树的垂序遍历
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 19:21
 */
public class LeetCode987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> list = new ArrayList<>();
        dfs(root,list,0,0);
        List<List<Integer>> res = new ArrayList<>();
        Collections.sort(list,(o1,o2)->{
            if(o1.col == o2.col){
                if(o1.row == o2.row) return o1.val - o2.val;
                return o1.row - o2.row;
            }
            return o1.col - o2.col;
        });
        int i = 0;

        while(i < list.size()){
            List<Integer> temp = new ArrayList<>();
            int pre = Integer.MAX_VALUE;
            while(temp.size() == 0 || (pre == list.get(i).col)){
                pre = list.get(i).col;
                temp.add(list.get(i++).val);
                if(i >= list.size()) break;
            }
            res.add(temp);
        }
        return res;
    }
    private void dfs(TreeNode root,List<Node> list,int row,int col){
        if(root == null) return;
        Node curNode = new Node(row,col,root.val);
        list.add(curNode);
        if(root.left != null) dfs(root.left,list,row+1,col-1);
        if(root.right != null) dfs(root.right,list,row+1,col+1);
    }

    /**
     * 不完全正确，卡在
     * [3,1,4,0,2,2] 实际输出：[[0],[1],[2,2,3],[4]]    官方Output: [[0],[1],[3,2,2],[4]]
     *
     */
    // 记录同一列的数据
    //Map<Integer, List<Integer>> hashMap = new LinkedHashMap<>();

    // 思路：排序+DFS 先进行DFS记录每个节点的坐标,再通过排序遍历出相同col的值
    /*public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root==null) return new ArrayList<>();

        // 从跟节点开始计算位置
        dfs(root,0,0);

        // 排序Map的key
        List<Integer> keys = new ArrayList(hashMap.keySet());
        Collections.sort(keys,((o1, o2) -> {
            if (o1 < o2) return 1;
            else return -1;
        }));

        // 排序每一列的数据
        List<List<Integer>> result = new ArrayList<>();
        for (Object key : keys) {
            if ()
            Collections.sort(hashMap.get(key),(o1,o2) ->{
                if (o1 < o2) return 1;
                else return -1;
            });
            result.add(hashMap.get(key));
        }

        return result;
    }

    private void dfs(TreeNode root, int row, int col) {
        if (root==null) return;

        Node cur = new Node(row,col,root.val);
        List<Integer> datas = new ArrayList<>();
        if (hashMap.containsKey(col)) datas = hashMap.get(col);
        datas.add(cur.val);
        hashMap.put(col, datas);

        // 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。
        // 树的根结点位于 (0, 0) 。
        if (root.left!=null) dfs(root.left,row+1,col-1);
        if (root.right!=null) dfs(root.right, row+1, col+1);
    }*/

    class Node {
        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
