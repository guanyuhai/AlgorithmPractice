package LeetCode.Heap;

import java.util.PriorityQueue;

/**
 * 数据流中的第K个元素
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/2 9:55
 */
public class LeetCode703 {
    // 维护一个最小堆，堆的元素个数为常量 k，新加入一个元素和堆顶比较，如果比堆顶元素小，丢弃，否则删除堆顶元素，插入新元素。
    class KthLargest {
        final PriorityQueue<Integer> q ;
        final int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            q = new PriorityQueue<Integer>(k);
            for(int i: nums) {
                add(i);
            }
        }

        public int add(int val) {
            // 当前队列大小小于K，则继续add
            if(q.size() < k) {
                q.offer(val);
            } // 如果栈顶的元素小于当前值，则弹出栈顶元素，添加当前元素
            else if(q.peek() < val) {
                q.poll();
                q.offer(val);
            }
            return q.peek();
        }
    }
}
