package Sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    private BubbleSort(){}

    public static <E extends Comparable<E>> void sort(E[] data) {
        for (int i = 0; i+1 < data.length; ) {
            // arr[n-i, n）已经排好序
            // 通过冒泡在 arr[n-i-1] 的位置放上合适的元素
            //boolean isSwapped = false;
            // 最后一次发生交换的位置，跳过后半部分已经排好序的部分
            int lastSwappedIndex = 0;
            for (int j = 0; j < data.length-i-1; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data,j,j+1);
                    lastSwappedIndex = j+1;
                }
            }
            // 避免剩余的元素已经有序，再进行重复循环
            //if (lastSwappedIndex==0) break;
            // i 记录多少个已经排好序了
            i = data.length - lastSwappedIndex;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
