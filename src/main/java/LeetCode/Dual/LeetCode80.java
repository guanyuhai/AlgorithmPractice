package LeetCode.Dual;

/**
 * 删除有序数组中的重复项II
 * 保留两个重复项
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/5 9:31
 */
public class LeetCode80 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if(nums==null || nums.length <= 2){
            return nums.length;
        }

        int index = 2;
        for(int i = 2; i < nums.length; i++){
            if(nums[i] != nums[index-2])
                nums[index++] = nums[i];
        }

        return index;
    }
}
