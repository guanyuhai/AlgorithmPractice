package LeetCode.Dual;

/**
 * 移动零
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/2 16:35
 */
public class LeetCode283 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length<=1) return;

        // 设置index，只有不为零时才会自增，并移动非零的数据
        int index = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int i = index; i < len; i++) {
            nums[i] = 0;
        }
    }
}
