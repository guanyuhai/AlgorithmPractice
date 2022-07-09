package SegmentTree;

/**
 * 融合器接口，用户自行实现，可以是求和、求最大值、最小值等
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/3 16:09
 */
public interface Merger<E> {
    E merge(E a, E b);
}
