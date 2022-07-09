package LeetCode.Heap;

import java.util.*;

/**
 * 前K个高频元素
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/30 16:34
 */
public class LeetCode347 {
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length<=1) return nums;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int count = 0;
            if (hashMap.containsKey(nums[i]))
                count = hashMap.get(nums[i]);
            count++;
            hashMap.put(nums[i],count);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(hashMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //升序排序
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        int[] res = topKFrequent(nums, k);
    }
}
