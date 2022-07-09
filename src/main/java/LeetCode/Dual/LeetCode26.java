package LeetCode.Dual;

/**
 * 删除有序数组中的重复项
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/2 10:29
 */
public class LeetCode26 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        // 使用双指针
        if(nums==null || nums.length == 1){
            return nums.length;
        }
        int i = 0,j =1;
        while(j<nums.length){
            if(nums[i]==nums[j]){
                j++;
            }else{
                i++;
                // 如果存在相同的元素，则把后一位元素往前排
                nums[i]=nums[j];
                j++;
            }
        }
        return i+1;
    }
}
