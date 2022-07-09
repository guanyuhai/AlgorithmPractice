package Sort;

public class InsertionSort {
    private InsertionSort(){}

    public static void main(String[] args) throws ClassNotFoundException {
        Integer[] datas = {6,4,2,3,1,5};

        /** 基本数据类型测试 */
        Integer[] sort_datas = sort4(datas);
        for (int i = 0; i < sort_datas.length; i++) {
            System.out.print(sort_datas[i] + " ");
        }
        System.out.println();

        /** 自定义类排序测试 */
        Student[] students = {new Student("Alice",98),
                new Student("coco",100),new Student("zsan",80)};
        Student[] sort_stu = sort3(students);
        for (Student student : sort_stu) {
            System.out.println(student +" ");
        }

        /** 性能测试 */
        /*int[] dataSize = {10000,100000};
        for (int n : dataSize) {
            Integer[] arr = sort.SortHelper.generateArray(n,n);
            sort.SortHelper.sortTest("sort.InsertionSort",arr);
        }*/

    }

    /**
     * 插入排序
     * @param datas
     * @return
     */
    public static <E extends Comparable<E>> E[] sort(E[] datas) {
        for (int i = 0; i < datas.length; i++) {
            for (int j = i+1; j < datas.length; j++) {
                if (datas[i].compareTo(datas[j])>0) {
                    E tmp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = tmp;
                }
            }
        }
        return datas;
    }

    public static <E extends Comparable<E>> E[] sort2(E[] datas) {
        for (int i = 0; i < datas.length; i++) {
            for (int j = i; j-1>=0; j--) {
                if (datas[j].compareTo(datas[j-1])<0) {
                    E tmp = datas[j];
                    datas[j] = datas[j-1];
                    datas[j-1] = tmp;
                } else break;
            }
            /** 简化代码 */
            /*for (int j = i; j-1>=0 && datas[j].compareTo(datas[j-1])<0; j--) {
                E tmp = datas[j];
                datas[j] = datas[j-1];
                datas[j-1] = tmp;
            }*/
        }
        return datas;
    }

    /**
     * 减少交换操作
     * @param datas
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E[] sort3(E[] datas) {
        for (int i = 0; i < datas.length; i++) {
            E tmp = datas[i];
            int j;
            for (j = i; j-1>=0; j--) {
                // 如果前一个元素比当前元素大，则前一个元素向后移动
                // 这是从小到大的情况
                if (datas[j-1].compareTo(tmp)>0) {
                    // 1 向后平移
                    datas[j] = datas[j-1];
                } else break;
            }
            // 将较小的元素插入到合适的位置，相当于只执行了一次交换
            datas[j] = tmp;
        }
        return datas;
    }

    // 对数组datas[l, r]进行排序
    public static <E extends Comparable<E>> E[] sort(E[] datas, int l, int r) {
        for (int i = l; i < r; i++) {
            E tmp = datas[i];
            int j;
            for (j = i; j-1>=l; j--) {
                // 如果前一个元素比当前元素大，则前一个元素向后移动
                // 这是从小到大的情况
                if (datas[j-1].compareTo(tmp)>0) {
                    // 1 向后平移
                    datas[j] = datas[j-1];
                } else break;
            }
            // 将较小的元素插入到合适的位置，相当于只执行了一次交换
            datas[j] = tmp;
        }
        return datas;
    }

    /**
     * 从后向前 实现插入排序
     * @param datas
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E[] sort4(E[] datas) {
        for (int i = datas.length-1; i >= 0 ; i--) {
            E tmp = datas[i];
            int j;
            for (j = i; j+1<datas.length; j++) {
                // 如果倒数第一个元素比倒数第二个元素小，则将倒数第二个元素向后移动
                // 这里datas[j]为倒数第二的元素，datas[j+1]为倒数第一的元素
                if (datas[j+1].compareTo(tmp)<0) {
                    datas[j] = datas[j+1];
                } else break;
            }
            datas[j] = tmp;
        }
        return datas;
    }
}
