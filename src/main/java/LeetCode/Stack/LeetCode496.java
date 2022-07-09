package LeetCode.Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 下一个更大的元素
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 15:48
 */
public class LeetCode496 {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        // 存储nums2数组值，及对应的下一个更大的值
        HashMap<Integer, Integer> hasMap = new HashMap<Integer, Integer>();

        int[] result = new int[nums1.length];
        for(int num : nums2) {
            // 如果当前值比栈顶的值大，在将栈顶的值作为key，当前值作为栈顶值的下一个更大的值
            while(!stack.isEmpty() && stack.peek()<num){
                hasMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for(int i = 0; i < nums1.length; i++) {
            result[i] = hasMap.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,2,3,4};
        int[] res = nextGreaterElement(nums1, nums2);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
