package Trie;

import java.util.TreeMap;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/4 20:45
 */
public class Trie {
    class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获取Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        // 新添加的单词不在树中，即添加的单词的结尾字母不为true，为true说明已经添加过该单词
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        // 比如已经存在panda，而查找的是pan，但n 为false，说明没添加过pan这个单词
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀, PS：一个单词也是这个单词的前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
