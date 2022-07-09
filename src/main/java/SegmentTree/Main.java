package SegmentTree;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/3 16:13
 */
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2,0,3,-5,2,-1};
        /*SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a+b;
            }
        });*/
        // 可以更改为lamda表达式
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, (a,b) -> a + b);

        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0,2));
        System.out.println(segmentTree.query(2,5));
        System.out.println(segmentTree.query(0,5));
    }
}
