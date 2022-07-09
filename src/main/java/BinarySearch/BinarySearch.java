package BinarySearch;

// 二分查找法
public class BinarySearch {
    private BinarySearch(){}

    // 递归实现二分查找法
    public static <E extends Comparable<E>> int serachR(E[] data, E target) {
        return searchR(data,0,data.length-1,target);
    }

    // 非递归实现二分查找法
    public static <E extends Comparable<E>> int serach(E[] data, E target) {
        int l = 0, r = data.length-1;

        // 在data[l,r] 范围中寻找元素target
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (data[mid].compareTo(target) == 0) {
                return mid;
            }
            // 如果mid下的元素小于target，说明target在右边大于mid的范围中
            if (data[mid].compareTo(target) < 0) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return -1;
    }

    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) return -1;

        int mid = l + (r-l)/2;

        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        if (data[mid].compareTo(target) < 0)
            return searchR(data, mid+1,r,target);
        return searchR(data,l,mid-1,target);
    }

    // > target的最小值的索引
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int l = 0, r = data.length;

        // 在data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r-l)/2;
            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    // > target, 返回最小值索引
    // ==target，返回最大索引
    public static <E extends Comparable<E>> int ceil(E[] data, E target) {
        int u = upper(data,target);
        if (u - 1>=0 && data[u - 1].compareTo(target) == 0) {
            return u - 1;
        }
        // 改成lower-ceil的实现（自行实现）
        /*while (u - 1>=0 && data[u - 1].compareTo(target) == 0) {
            u = u -1;
        }*/
        return u;
    }

    // < target 的最大索引值
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = -1, r = data.length-1;

        while (l < r) {
            // 使用+1，避免l和r相邻时出现死循环
            int mid = l + (r-l+1)/2;
            if (data[mid].compareTo(target) < 0) {
                l = mid;
            } else {
                r = mid-1;
            }
        }
        return l;
    }
}
