package LeetCode.Heap;

import Linked.LinkedList;

import java.util.*;

/**
 * 合并K个升序链表
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/30 14:13
 */
public class LeetCode23 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next= new ListNode(4);
        listNode1.next.next = new ListNode(5);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);

        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(6);

        ListNode[] lists = new ListNode[3];
        lists[0] = listNode1;
        lists[1] = listNode2;
        lists[2] = listNode3;

        ListNode[] listNodes = new ListNode[1];
        listNodes[0] = null;

        ListNode node = mergeKLists2(listNodes);
    }

    /**
     * 分治算法
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        if(lists.length == 2){
            return mergeTwoLists(lists[0],lists[1]);
        }

        int mid = lists.length/2;
        ListNode[] l1 = new ListNode[mid];
        for(int i = 0; i < mid; i++){
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));

    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = null;
        if (l1.val <= l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }


    public ListNode mergeKLists3(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }

    // 采用归并排序的方式
    // [[],[]] 的情况未能解决
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length==0) return null;
        if(lists.length == 1)
            return lists[0];

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            nums.add(lists[i].val);
            while (lists[i].next != null) {
                nums.add(lists[i].next.val);
                lists[i] = lists[i].next;
            }
        }

        Integer[] datas = new Integer[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            datas[i] = nums.get(i);
        }

        Integer[] temp = Arrays.copyOf(datas,datas.length);
        sort(datas, 0, datas.length-1, temp);

        // 重新构建链表
        ListNode res = new ListNode(datas[0]);
        int length = datas.length;
        for (int i = 1; i < length; i++) {
            add(res,i-1, datas[i]);
        }
        return res;
    }

    public static void add(ListNode dummyHead, int index, int e) {
        ListNode prev = dummyHead;
        // 找到待插入的节点的前面的那个节点
        // 使用dummyHead后，就不需要对0这个节点做特殊处理
        for (int i = 0; i < index; i++)
            prev = prev.next;

        // 插入元素，把前一个元素的下一个元素指向转移给插入的node节点
        ListNode node = new ListNode(e, prev.next);
        prev.next = node;
    }

    private static <E extends Comparable<E>> void sort(Integer[] arr, int l, int r, Integer[] temp) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort(arr,l,mid, temp);
        sort(arr,mid+1, r, temp);

        // 只有前一个有序数组的最后一个值 大于 后一个有序数组的第一个组才进行合并排序
        // 否则两个组合再一起原本就是有序的
        if (arr[mid] > arr[mid+1])
            merge(arr, l, mid, r, temp);
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid+1 , r]
    private static <E extends Comparable<E>> void merge(Integer[] arr, int l, int mid, int r, Integer[] temp) {
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
            } else if (temp[i] <= temp[j]) {
                //arr[k] = temp[i-l]; i++;
                arr[k] = temp[i]; i++;
            } else {
                //arr[k] = temp[j-l]; j++;
                arr[k] = temp[j]; j++;
            }
        }
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
