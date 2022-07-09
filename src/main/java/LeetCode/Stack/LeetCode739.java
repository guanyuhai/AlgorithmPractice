package LeetCode.Stack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 每日温度
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 10:55
 */
public class LeetCode739 {
    // 最简单的方式，双重循环
    public int[] dailyTemperatures(int[] temperatures) {
        int[] results = new int[temperatures.length];

        for (int i = 0; i < temperatures.length-1; i++) {
            for (int j = i+1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    results[i] = j-i;
                    break;
                }
            }
        }

        return results;
    }

    /**
     * 根据题意，从最后一天推到第一天，这样会简单很多。因为最后一天显然不会再有升高的可能，结果直接为0。
     * 再看倒数第二天的温度，如果比倒数第一天低，那么答案显然为1，如果比倒数第一天高，又因为倒数第一天
     * 对应的结果为0，即表示之后不会再升高，所以倒数第二天的结果也应该为0。
     * 自此我们容易观察出规律，要求出第i天对应的结果，只需要知道第i+1天对应的结果就可以：
     * - 若T[i] < T[i+1]，那么res[i]=1；
     * - 若T[i] > T[i+1]
     *   - res[i+1]=0，那么res[i]=0;
     *   - res[i+1]!=0，那就比较T[i]和T[i+1+res[i+1]]（即将第i天的温度与比第i+1天大的那天的温度进行比较）
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] res = new int[temperatures.length];
        // 最后一天，直接为0
        res[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            // 每次前进res[j] 步长
            for (int j = i + 1; j < temperatures.length; j += res[j]) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }

    //栈中存储的是数组元素下标，并且对应元素值得规律是单调递减
    //一旦当前数字大于栈顶元素对应值，那么依次出栈比较，找到所有小于当前元素的值，计算下标距离
    public int[] dailyTemperatures3(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // 弹出栈顶的元素
                int tmp = stack.pop();
                res[tmp] = i - tmp;
            }
            stack.push(i);
        }

        return res;
    }
    // 复习
    /**
     * 单调栈，栈内顺序要么从大到小 要么从小到大,本题从大到小
     * 入站元素要和当前栈内栈首元素进行比较
     * 若大于栈首则 则与元素下标做差
     * 若大于等于则放入
     * @param temperatures
     * @return
     */
    public int[] daily(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            // 取出下标进行元素值的比较
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int preIndex = stack.pop();
                res[preIndex] = i - preIndex;
            }

            stack.push(i);
        }
        return res;
    }
}
