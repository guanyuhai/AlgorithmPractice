package LeetCode.Heap;

import java.util.*;

/**
 * 数据流的中位数
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/1 10:50
 */
public class LeetCode295 {
    class MedianFinder {
        PriorityQueue<Integer> min ;
        PriorityQueue<Integer> max ;

        public MedianFinder() {
            min = new PriorityQueue<>();
            max = new PriorityQueue<>((a,b) -> {return  b - a ;});
        }

        public void addNum(int num) {
            max.add(num);
            min.add(max.remove());
            if (min.size() > max.size())
                max.add(min.remove());
        }

        public double findMedian() {
            if (max.size() == min.size())
                return (max.peek() + min.peek()) / 2.0;
            else
                return max.peek();
        }
    }
}
