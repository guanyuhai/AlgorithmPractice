package QuickSort;

import java.util.Random;

public class QuickSort {
    private QuickSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random random = new Random();
        sort(arr,0,arr.length-1,random);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random random) {
        if (l>=r) return;

        // 使用 Insertion Sort 优化
        /*if(r - l <= 15){
            InsertionSort.sort(arr, l, r);
            return; // 注意，这里要 return！
        }*/

        int p = partition(arr,l,r,random);
        sort(arr,l,p-1, random);
        sort(arr,p+1,r, random);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r, Random random) {
        // 生成 [l,r] 之间的随机索引，避免完全有序的情况下，复杂度变为n2
        int p = l + random.nextInt(r-l+1);
        swap(arr,l,p);

        // 循环不变量：arr[l+1 ... j] < v,  arr[j+1 ... i] >= v
        int j = l;
        for (int i = l+1; i < r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j ++ ;
                swap(arr, i, j);
            }
        }

        swap(arr,l,j);
        return j;
    }

    /**
     * 双路快速排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort2ways(E[] arr) {
        Random random = new Random();
        sort2ways(arr,0,arr.length-1,random);
    }

    private static <E extends Comparable<E>> void sort2ways(E[] arr, int l, int r, Random random) {
        if (l>=r) return;

        int p = partition2(arr,l,r,random);
        sort2ways(arr,l,p-1, random);
        sort2ways(arr,p+1,r, random);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        // 生成 [l,r] 之间的随机索引，避免完全有序的情况下，复杂度变为n2
        int p = l + random.nextInt(r-l+1);
        swap(arr,l,p);

        // 循环不变量：arr[l+1 ... i-1] <= v,  arr[j+1 ... r] >= v
        int i = l + 1, j = r;
        while (true) {
            while (i <= j && arr[i].compareTo(arr[l])<0) {
                i++;
            }

            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j--;
            }

            if (i>=j) break;

            swap(arr,i,j);
            i++;
            j--;
        }

        swap(arr,l,j);
        return j;
    }

    /**
     * 三路快速排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort3ways(E[] arr) {
        Random random = new Random();
        sort3ways(arr,0,arr.length-1,random);
    }

    private static <E extends Comparable<E>> void sort3ways(E[] arr, int l, int r, Random random) {
        if (l>=r) return;

        // 生成 [l,r] 之间的随机索引，避免完全有序的情况下，复杂度变为n2
        int p = l + random.nextInt(r-l+1);
        swap(arr,l,p);

        // 循环不变量：arr[l+1, lt-1] < v, arr[lt, i-1]==v, arr[gt, r] > v
        int lt = l, i = l+1, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {
                lt++;
                swap(arr,i,lt);
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                gt--;
                swap(arr, i, gt);
            } else { // arr[i] == arr[l]
                i++;
            }
        }

        swap(arr,l,lt);
        // arr[l, lt-1] < v, arr[lt, gt-1]==v, arr[gt, r] > v

        sort3ways(arr,l, lt-1,random);
        sort3ways(arr,gt, r,random);
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
