package Trie;

import java.util.TreeMap;

/**
 * leecode 677
 * Trie当成一个映射在使用
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/4 21:32
 */
public class MapSum {
    class Node{
        public TreeMap<Character, Node> next;
        // 省略isWord，如果value为0，表示当前不是一个词
        public int value;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }
    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }

        // 累加找到的单词的所有前缀的值
        return sum(cur);
    }

    private int sum(Node node) {
        // 递归到底的情况: 当前节点为叶子节点
        if (node.next.size() == 0)
            return node.value;

        int res = node.value;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }

        return res;
    }

}
