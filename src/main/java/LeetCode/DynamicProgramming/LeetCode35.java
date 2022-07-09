package LeetCode.DynamicProgramming;

/**
 * 搜索插入
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 20:43
 */
public class LeetCode35 {
    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length;i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }
}
