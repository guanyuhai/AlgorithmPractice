package Sort;

import java.util.Arrays;

public class ShellSort {
    private ShellSort(){}

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length/2;

        while (h > 1) {
            for (int start = 0; start < h; start++) {
                // 对 data[start, start+h, start+2h, ...]，进行插入排序
                for (int i = start+h; i < data.length; i+=h) {
                    E t = data[i];
                    int j;
                    for (j = i; j-h > 0 && t.compareTo(data[j-h])<0 ; j-=h) {
                        data[j] = data[j-h];
                    }
                    data[j] = t;
                }
            }
            
            h/=2;
        }
    }

    // 减少为三重循环，直接遍历每一个元素，不过每次都隔着h步看
    public static <E extends Comparable<E>> void sort2(E[] data) {
        int h = data.length/2;

        while (h > 1) {
            // 对 data[h, n)，进行插入排序
            for (int i = h; i < data.length; i++) {
                E t = data[i];
                int j;
                for (j = i; j-h > 0 && t.compareTo(data[j-h])<0 ; j-=h) {
                    data[j] = data[j-h];
                }
                data[j] = t;
            }
            h/=2;
        }
    }

    // 使用不同的步长序列，如1 4  13  40 。。。即 后一个元素为前一个元素*3+1
    public static <E extends Comparable<E>> void sort3(E[] data) {
        int h = 1;
        while (h<data.length)
            h = h*3+1;

        while (h > 1) {
            // 对 data[h, n)，进行插入排序
            for (int i = h; i < data.length; i++) {
                E t = data[i];
                int j;
                for (j = i; j-h > 0 && t.compareTo(data[j-h])<0 ; j-=h) {
                    data[j] = data[j-h];
                }
                data[j] = t;
            }
            h/=3;
        }
    }

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr1,arr1.length);
    }
}
