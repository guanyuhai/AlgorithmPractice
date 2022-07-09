package LeetCode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 接雨水
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/30 10:33
 */
public class LeetCode42 {
    public static int trap2(int[] height) {
        if (height == null) return 0;

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }

    // 双指针的解法
    public int trap01(int[] height) {
        int sum = 0;
        int length = height.length;
        for (int i = 0; i < length; i++) {
            // 第一个柱子和最后一个柱子不接雨水
            if (i==0 || i == length-1) continue;

            int rHeight = height[i]; // 记录左边柱子的最高高度
            int lHeight = height[i]; // 记录右边柱子的最高高度
            for (int r = i+1; r < length; r++) {
                if (height[r] > rHeight) rHeight = height[r];
            }
            for (int l = i-1; l >=0 ; l--) {
                if (height[l] > lHeight) lHeight = height[l];
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if (h>0) sum+=h;
        }

        return sum;
    }

    // 动态规划的解法
    public int trap02(int[] height) {
        int length = height.length;
        if (length<=2) return 0;

        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        // 记录每个柱子左边柱子最高的高度
        maxLeft[0] = height[0];
        for (int i = 1; i < length; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i-1]);
        }
        // 记录每个柱子右边柱子最高的高度
        maxRight[length-1] = height[length-1];
        for (int i = length-2; i >=0 ; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i+1]);
        }

        // 求和
        int sum=0;
        for (int i = 0; i < length; i++) {
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (count>0) sum+=count;
        }

        return sum;
    }


    // 未正确实现
    public static int trap(int[] height) {
        int res = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        int length = height.length;
        for (int i = 0; i < length-1; i++) {
            while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                int other = 0;
                for (int j = i; j < length; j++) {
                    if (height[j] >= height[stack.peek()]) {
                        int value = height[stack.pop()];
                        res+=(Math.min(value,height[j]) * (j-i))-other;
                        break;
                    } else if (height[j]>0) {
                        stack.push(j);
                        other++;
                    }
                    if (stack.isEmpty()) break;
                }
            }
            if (height[i]>0) stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(nums));
    }
}
