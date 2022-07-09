package LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N叉树的层序遍历
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/28 15:56
 */
public class LeetCode429 {
    public List<List<Integer>> levelOrder(Node root) {
        if (root==null) return new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                list.add(cur.val);

                for (Node child : cur.children) {
                    if (child!=null) queue.add(child);
                }
            }
            results.add(list);
        }

        return results;
    }

    // 复习
    public List<List<Integer>> level(Node root) {
        if (root==null) return new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                res.add(cur.val);

                for (Node child : cur.children) {
                    if (child!=null) queue.add(child);
                }
            }
            results.add(res);
        }

        return results;
    }

    // N叉树
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}


