package LeetCode.Stack;

import java.util.*;

/**
 * 函数的独占时间
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 21:31
 */
public class LeetCode636 {
    public static int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> stack = new Stack<>();

        int[] res = new int[n];
        for (String log : logs) {
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            int time = Integer.parseInt(split[2]);
            if ("start".equals(split[1])) {
                stack.push(new int[]{id, time});
            } else {
                int[] pop = stack.pop();
                int interval = time - pop[1] + 1;
                res[pop[0]] += interval;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= interval;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n =2;
        String[] strs = new String[]{"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
        List<String> logs = Arrays.asList(strs);
        int[] res = exclusiveTime(n, logs);
    }
}
