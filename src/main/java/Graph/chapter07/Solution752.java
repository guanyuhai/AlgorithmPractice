package Graph.chapter07;

import java.util.*;

/**
 * @description: 打开转盘锁:  https://leetcode-cn.com/problems/open-the-lock/
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class Solution752 {

    public int openLock(String[] deadends, String target) {

        //为加快检索的速度
        HashSet<String> deadSet = new HashSet<>();
        for (String deadend : deadends)
            deadSet.add(deadend);

        if (deadSet.contains(target))
            return -1;

        if (deadSet.contains("0000"))
            return -1;

        if (target.equals("0000"))
            return 0;

        // BFS
        Queue<String> queue = new LinkedList<>();
        //HashSet<String> visited = new HashSet<>();  //字符串类型, 使用set来记录是否被访问过
        queue.add("0000");
        //记录步数, 同时也可以作访问记录
        Map<String,Integer> map = new HashMap<>();
        map.put("0000",0);
        while(!queue.isEmpty()){
            String cur = queue.remove();
            char[] curArray = cur.toCharArray();
            //记录接下来的8个状态
            List<String> nexts = new ArrayList<>();
            //todo
            for (int i = 0; i < curArray.length; i++) {

                char o = curArray[i];
                // +1
                curArray[i] = Character.forDigit((curArray[i]-'0'+1)%10,10);
                nexts.add(new String(curArray));

                curArray[i] = o;
                // -1
                curArray[i] = Character.forDigit((curArray[i]-'0'+9)%10,10);
                nexts.add(new String(curArray));
                curArray[i] = o;
            }

            for (String next : nexts)
                if (!deadSet.contains(next) && !map.containsKey(next)) {
                    queue.add(next);
                    map.put(next, map.get(cur) + 1);

                    if (next.equals(target))
                        return map.get(next);
                }
        }
        return -1;
    }
}
