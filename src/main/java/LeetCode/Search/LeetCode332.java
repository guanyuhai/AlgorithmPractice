package LeetCode.Search;

import java.util.*;

/**
 * 重新安排行程
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 17:04
 */
public class LeetCode332 {
    private Deque<String> res;
    // map<出发机场, map<到达机场, 航班次数>>
    private Map<String, Map<String, Integer>> map;

    public List<String> findItinerary(List<List<String>> tickets) {
        // 初始化
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();

        // 构建Map
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            // 是否已包含出发机场记录
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                // 在原本的到达航班次数值上+1
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序Map
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);
        }

        // 题目中是从JFK机场出发
        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }

    private boolean backTracking(int ticketNum){
        if(res.size() == ticketNum + 1){
            return true;
        }
        String last = res.getLast();
        if(map.containsKey(last)){//防止出现null
            // 遍历Map中的值
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    // 只需要找到一条航线
                    res.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking(ticketNum)) return true;
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }
}
