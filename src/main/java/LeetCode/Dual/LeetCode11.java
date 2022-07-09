package LeetCode.Dual;

/**
 * 盛最多水的容器
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/2 16:17
 */
public class LeetCode11 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(nums));
    }

    public static int maxArea(int[] height) {
        int max = 0;

        int l =0, r = height.length - 1;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            int temp = min * (r - l);
            max = Math.max(max, temp);

            // 快速跳过: 比当前height[l], height[r]还要小的高度就不必再算了
            while (height[l] <= min && l < r) {
                ++l;
            }
            while (height[r] <= min && l < r) {
                --r;
            }
        }

        return max;
    }
}
