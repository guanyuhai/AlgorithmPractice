package MaxHeap;

import MergeSort.MergeSort;
import Sort.ArrayGenerator;
import Sort.SortHelper;

import java.util.Arrays;

public class HeapSort {
    private HeapSort(){}

    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<E>();
        for (E e : data) {
            maxHeap.add(e);
        }

        for (int i = data.length-1; i >= 0 ; i--) {
            data[i] = maxHeap.extractMax();
        }
    }

    // 原地的堆排序
    public static <E extends Comparable<E>> void sort2(E[] data) {
        if (data.length<=1) return;

        for (int i = (data.length-2)/2; i >= 0 ; i--) {
            siftDown(data, i, data.length);
        }

        for (int i = data.length-1; i >=0 ; i--) {
            swap(data,0,i);
            siftDown(data,0,i);
        }
    }

    // 对data[0,n) 所形成的最大堆中，索引k的元素执行siftDown
    private static <E extends Comparable<E>> void siftDown(E[] data,int k, int n) {
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

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortHelper.sortTest(HeapSort.class.getName(),arr);
        SortHelper.sortTest(MergeSort.class.getName(),arr2);

    }
}
