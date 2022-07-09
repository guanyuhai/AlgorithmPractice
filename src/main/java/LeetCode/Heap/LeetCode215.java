package LeetCode.Heap;


import java.util.ArrayList;
import java.util.Collections;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/30 13:24
 */
public class LeetCode215 {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest2(nums, 4));
    }


    public static int findKthLargest2(int[] nums, int k) {
        Integer[] nums2 = new Integer[nums.length];
        int length = nums2.length;
        for (int i = 0; i < length; i++) {
            nums2[i] = nums[i];
        }

        for (int i = (length-2)/2; i >= 0 ; i--) {
            siftDown(nums2, i, length);
        }

        for (int i = length-1; i >=0 ; i--) {
            swap(nums2,0,i);
            siftDown(nums2,0,i);
        }

        int j = 0;
        for (int i = length-1; i >= 0; i--) {
            nums[j++] = nums2[i];
        }
        return nums[k-1];
    }
    // 对data[0,n) 所形成的最大堆中，索引k的元素执行siftDown
    private static <E extends Comparable<E>> void siftDown(Integer[] data,int k, int n) {
        // 如果k的左孩子节点的索引大于当前数组大小，那肯定得越界退出
        while (2*k+1 < n) {
            int j = 2*k+1;
            // j + 1 表示的就是右节点的索引
            if (j + 1 < n && data[j+1].compareTo(data[j]) > 0)
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(data,k,j);
            k = j;
        }
    }

    private static <E extends Comparable<E>> void swap(Integer[] arr, int i, int j) {
        Integer t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //
    public static int findKthLargest(int[] nums, int k) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        for (Integer e : nums) {
            maxHeap.add(e);
        }

        int size = maxHeap.size();
        for (int i = 0; i < size ; i++) {
            nums[i] = maxHeap.extractMax();
        }

        return nums[k-1];
    }


    public static class MaxHeap<E extends Comparable<E>> {
        private ArrayList<E> data;
        public MaxHeap(int capacity) {
            data = new ArrayList(capacity);
        }

        public MaxHeap() {
            data = new ArrayList<E>();
        }

        // 返回堆中的元素个数
        public int size() {
            return data.size();
        }

        // 返回一个布尔值，表示堆中是否为空
        public boolean isEmtpy() {
            return data.isEmpty();
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index) {
            if (index==0)
                throw new IllegalArgumentException("index-0 doesn't hava parent");
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index) {
            return index * 2 + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return index * 2 + 2;
        }

        public void add(E e) {
            data.add(e);
            siftUp(data.size()-1);
        }

        // 上浮节点
        private void siftUp(int k) {
            while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
                Collections.swap(data,k,parent(k));
                k = parent(k);
            }
        }

        // 查找堆中最大的元素
        public E findMax() {
            if (data.size()==0)
                throw new IllegalArgumentException("Can not findMax when heap is empty!");
            return data.get(0);
        }

        // 取出堆中的最大元素
        public E extractMax() {
            E ret = findMax();
            // 1 删除取出的最大的元素
            Collections.swap(data,0, data.size()-1);
            data.remove(data.size()-1);

            // 节点下沉
            siftDown(0);

            return ret;
        }

        private void siftDown(int k) {
            // 如果k的左孩子节点的索引大于当前数组大小，那肯定得越界退出
            while (leftChild(k) < data.size()) {
                int j = leftChild(k);
                // j + 1 表示的就是右节点的索引
                if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0)
                    j = rightChild(k);
                // data[j] 是 leftChild 和 rightChild 中的最大值
                if (data.get(k).compareTo(data.get(j)) >= 0) {
                    break;
                }
                Collections.swap(data,k,j);
                k = j;
            }
        }

        // 取出堆中的最大元素，并且替换成元素e
        public E replace(E e) {
            E ret = findMax();
            data.set(0,e);
            siftDown(0);

            return ret;
        }

        public MaxHeap(E[] arr) {
            data = new ArrayList<E>();
            for (int i = 0; i < arr.length; i++) {
                data.add(arr[i]);
            }
            for (int i = parent(arr.length-1); i >=0 ; i--) {
                siftDown(i);
            }
        }
    }
}
