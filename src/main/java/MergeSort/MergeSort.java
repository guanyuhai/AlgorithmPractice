package MergeSort;

import Sort.InsertionSort;

import java.util.Arrays;

public class MergeSort {
    private MergeSort(){}

    /**
     * 自顶向下的排序算法
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 提前开辟一个临时空间，避免后续合并方法中重复开辟
        E[] temp = Arrays.copyOf(arr,arr.length);
        sort(arr,0,arr.length-1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp) {
        if (l >= r) return;

        // 如果元素个数小于等于16，就执行插入排序，不进行归并排序
        // 但是在一些脚本语言中可以会影响性能
        /*if (r - l <= 15) {
            InsertionSort.sort(arr,l,r);
            return;
        }*/

        int mid = l + (r - l) / 2;
        sort(arr,l,mid, temp);
        sort(arr,mid+1, r, temp);

        // 只有前一个有序数组的最后一个值 大于 后一个有序数组的第一个组才进行合并排序
        // 否则两个组合再一起原本就是有序的
        if (arr[mid].compareTo(arr[mid+1])>0)
            merge(arr, l, mid, r, temp);
    }

    /**
     * 自底向上的算法
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr,arr.length);
        int n = arr.length;

        // 使用插入排序优化
        // 遍历一遍，对所有 arr[i, i + 15] 的区间，使用插入排序法
        // 注意 i 每次加 16
        for(int i = 0; i < n; i += 16)
            InsertionSort.sort(arr, i, Math.min(i + 15, n - 1)); // 也要注意这里 Math.min 的用法

        // 遍历合并的区间长度
        for (int size = 16; size < n; size+=size) {
            // 遍历合并的两个区间的起始位置 i
            // 合并 [i, i+size-1] 和 [i+size, Math.min(i+size+size-1, n-1)]
            for (int i = 0; i+size<n; i+=size+size) {
                if (arr[i + size - 1].compareTo(arr[i + size]) > 0) {
                    // i+size+size+1 可能会越界
                    merge(arr, i, i+size-1, Math.min(i+size+size-1, n-1), temp);
                }
            }
        }
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid+1 , r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        // 拷贝一份
        //E[] temp = Arrays.copyOfRange(arr, l, r+1);
        // 把一个数组中的元素拷贝到另一个数组中
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                // 需要减去偏移量l，提前拷贝temp的话，就没有偏移量了
                //arr[k] = temp[j-l]; j++;
                arr[k] = temp[j]; j++;
            } else if (j > r) {
                //arr[k] = temp[i-l]; i++;
                arr[k] = temp[i]; i++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                //arr[k] = temp[i-l]; i++;
                arr[k] = temp[i]; i++;
            } else {
                //arr[k] = temp[j-l]; j++;
                arr[k] = temp[j]; j++;
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int n = 100000;
        Integer arr[] = new Integer[100000];
    }
}
