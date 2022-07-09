package LeetCode.Stack;

import java.util.*;

/**
 * 柱状图中的最大矩形
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/30 8:51
 */
public class LeetCode84 {
    // 优化版本
    /**
     * 我们遍历每个柱体，若当前的柱体高度大于等于栈顶柱体的高度，就直接将当前柱体入栈，
     * 否则若当前的柱体高度小于栈顶柱体的高度，说明当前栈顶柱体找到了右边的第一个小于自身的柱体，
     * 那么就可以将栈顶柱体出栈来计算以其为高的矩形的面积了。
     * @param heights
     * @return
     */
    public static int largestRectangleArea3(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            // stack为空 或 当前柱体高度大于栈顶柱体高度时压入
            stack.push(i);
        }

        return area;
    }

    // 复习，动态规划方案
    public int largest(int[] heights) {
        int length = heights.length;
        int[] minLeftIndex = new int[length];
        int[] maxRigthIndex = new int[length];

        // 记录左边第一个小于该柱子的下表
        minLeftIndex[0] = -1;
        for (int i = 1; i < length; i++) {
            int t = i-1;
            // 不断向右寻找
            while (t>=0 && heights[t]>=heights[i]) t = minLeftIndex[t];
            minLeftIndex[i] = t;
        }
        // 记录每个柱子 右边第一个小于该柱子的下标
        maxRigthIndex[length - 1] = length;
        for (int i = length - 2; i >= 0; i--) {
            int t = i + 1;
            while(t < length && heights[t] >= heights[i]) t = maxRigthIndex[t];
            maxRigthIndex[i] = t;
        }
        // 求和
        int result = 0;
        for (int i = 0; i < length; i++) {
            int sum = heights[i] * (maxRigthIndex[i] - minLeftIndex[i] - 1);
            result = Math.max(sum, result);
        }
        return result;
    }



    // 还是超出时间限制
    public static int largestRectangleArea2(int[] heights) {
        int area = 0, n = heights.length;
        // 遍历每个柱子，以当前柱子的高度作为矩形的高 h，
        // 从当前柱子向左右遍历，找到矩形的宽度 w。
        for (int i = 0; i < n; i++) {
            int w = 1, h = heights[i], j = i;
            // 不断往前找到下一个高度比当前高度高的柱状
            while (--j >= 0 && heights[j] >= h) {
                w++;
            }
            j = i;
            // 不断往后找到下一个高度比当前高度高的柱状
            while (++j < n && heights[j] >= h) {
                w++;
            }
            area = Math.max(area, w * h);
        }

        return area;
    }


    // 超出时间限制
    public static int largestRectangleArea(int[] heights) {
        List<Integer> sum = new ArrayList<>();
        List<Integer> copy = new ArrayList<>();
        // 存储当前循环的最小值
        HashMap<Integer,Integer> mins = new HashMap<>();

        int max = heights[0];
        //int min = heights[0];
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            sum.add(heights[i]);
            /*// 获取数组中的最小元素
            if (heights[i<min) min = heights[i];
            // 1 所有柱状图逐个合并成矩形
            if (min*(i+1) > max) max = min*i;
            // 2 单个柱状图作为矩形
            if (heights[i] > max) max = heights[i];]*/

            // 3 相邻的柱状图组成矩形
            int size = sum.size();
            for (int j = 0; j < size; j++) {
                int min = Collections.min(sum);
                mins.put(size+1-j, min);

                int value=0;
                if (mins.containsKey(size-j)) {
                    value = Math.min(mins.get(size-j), heights[i]) * (size-j);
                } else value = min * sum.size();
                if (value>max) max = value;
                copy.add(sum.get(0));
                sum.remove(0);
            }
            sum = new ArrayList<>(copy);
            copy.clear();
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea3(nums));
    }
}
