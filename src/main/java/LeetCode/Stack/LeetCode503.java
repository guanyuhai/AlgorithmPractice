package LeetCode.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 下一个更大的元素2
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 16:09
 */
public class LeetCode503 {
    // 使用循环数组，且输出下一个更大的元素值
    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();//栈中存放的是nums中的元素下标
        int[] result = new int[nums.length];
        Arrays.fill(result,-1);//默认全部初始化为-1

        int length = nums.length;
        for (int i = 0; i < 2*length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i%length]) {
                result[stack.peek()] = nums[i % length];//更新result
                stack.pop();//弹出栈顶
            }
            stack.push(i%length);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums2 = new int[]{5,4,3,2,1};
        int[] res = nextGreaterElements(nums2);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
