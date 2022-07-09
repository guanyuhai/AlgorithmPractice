package SegmentTree;

/**
 * leecode 练习题303号
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/3 20:47
 */
public class NumArrays {
    // 预处理
    private int[] sum; // sum[i] 存储前i个元素和, sum[0] = 0, 即从1 开始存储nums的数据
                        // sum[i] 存储nums[0 ... i-1] 的和

    // 构造的时候要O(n) 的复杂度
    public NumArrays(int[] nums) {
        sum = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
    }

    // 但每次调用只需要O(1) 的复杂度
    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }
}
