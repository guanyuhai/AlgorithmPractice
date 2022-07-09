package Graph.chapter07;

import java.util.*;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/14 16:38
 */
public class Solution752_01 {
    public int openLock(String[] deadends, String target) {
        // 将死亡数字放到hashset中
        HashSet<String> deadset = new HashSet<>();
        for(String s: deadends)
            deadset.add(s);

        if(deadset.contains(target)) return -1;
        // 初始状态0000就是死亡数字，直接返回-1
        if(deadset.contains("0000")) return -1;
        if(target.equals("0000")) return 0;

        // BFS
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        queue.add("0000");
        visited.put("0000", 0);

        while(!queue.isEmpty()){
            String curs = queue.remove();
            char[] curarray = curs.toCharArray();

            ArrayList<String> nexts = new ArrayList<>();
            for(int i = 0; i < 4; i ++){
                char o = curarray[i];
                // 向后拨一位，使用forDigit 将十进制数字转为字符
                curarray[i] = Character.forDigit((curarray[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curarray));
                curarray[i] = o;

                // 向前拨一位
                curarray[i] = Character.forDigit((curarray[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curarray));
                curarray[i] = o;
            }

            for(String next: nexts)
                if(!deadset.contains(next) && !visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(curs) + 1);
                    // 已经达到目标值，直接返回
                    if(next.equals(target)) return visited.get(next);
                }
        }

        return -1;
    }
}
