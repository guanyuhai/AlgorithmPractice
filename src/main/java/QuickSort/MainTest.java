package QuickSort;

import MergeSort.MergeSort;
import Sort.ArrayGenerator;
import Sort.SortHelper;

import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) throws ClassNotFoundException {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr1,arr1.length);

        SortHelper.sortTest(MergeSort.class.getName(), arr1);
        SortHelper.sortTest(QuickSort.class.getName(), arr2);

        // 再次使用有序的数组进行排序
        SortHelper.sortTest(MergeSort.class.getName(), arr1);
        SortHelper.sortTest(QuickSort.class.getName(), arr2);

        // 数组中所有元素都相同时
        Integer[] arr3 = ArrayGenerator.generateRandomArray(n,1);
        SortHelper.sortTest(QuickSort.class.getName(), arr3);
    }
}
