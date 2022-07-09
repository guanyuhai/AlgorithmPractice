package Sort;

import MaxHeap.HeapSort;
import MergeSort.MergeSort;
import QuickSort.QuickSort;

import java.util.Random;

public class SortHelper {
    private SortHelper(){}

    /**
     * 生成一个长度为n的随机数组，每个数字的范围是[0,bound)
     * @param n
     * @param bound
     * @return
     */
    public static Integer[] generateArray(int n,int bound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    /**
     * 判断排序后的数组是否有序
     * 比较当前元素是否比下一个元素小，如果比下一个元素大，则为false
     * @param arr
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i - 1].compareTo(arr[i]) > 0)
                return false;
        return true;
    }

    public static <E extends Comparable<E>>void sortTest(String sortClass, E[] arr) throws ClassNotFoundException {
        long start = System.nanoTime();
        /*if (sortName.equals("SelectioSort"))
            sort.SelectionSort.select_sort2(arr);*/
        // 简单使用反射机制
        Class c = Class.forName(sortClass);
        if (c.getName().equals("sort.SelectionSort"))
            SelectionSort.select_sort3(arr);
        else if (c.getName().equals("sort.InsertionSort"))
            InsertionSort.sort2(arr);
        else if (c.getName().equals("MergeSort.MergeSort"))
            MergeSort.sort(arr);
        else if (c.getName().equals("QuickSort.QuickSort"))
            QuickSort.sort2ways(arr);
        else if (c.getName().equals("MaxHeap.HeapSort"))
            HeapSort.sort(arr);

        long end = System.nanoTime();
        double time = (end-start) / 1000000000.0;

        /** 判断是否有序 */
        if (!SortHelper.isSorted(arr))
            throw new RuntimeException(sortClass + " failed");
        System.out.println(String.format("%s, n = %d : %f s",sortClass,arr.length,time));
    }
}
