package LeetCode.Stack;

import java.util.*;

/**
 * 行星碰撞
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 11:50
 */
public class LeetCode735 {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        int len = asteroids.length;
        for(int i = 0; i < len; i++){
            int cur = asteroids[i];
            boolean add = true;
            // 当前元素为负数，且栈顶为正数时，才执行碰撞操作
            while(!st.isEmpty() && cur < 0 && st.peek() > 0){
                if(Math.abs(cur) == st.peek()){
                    st.pop();
                    add = false;
                    break;
                }else if(Math.abs(cur) > st.peek()){
                    st.pop();
                }else{
                    add = false;
                    break;
                }
            }
            if(add) st.push(cur);
        }
        int[] res = new int[st.size()];
        int pos = res.length - 1;
        while(pos >= 0){
            res[pos--] = st.pop();
        }
        return res;
    }


    // 不完全正确
    /*public static int[] asteroidCollision2(int[] asteroids) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < asteroids.length; i++) {
            res.add(asteroids[i]);
        }

        List<Integer> removeIndex = new LinkedList<>();
        for (int i = 0; i < asteroids.length-1; i++) {
            for (int j = i+1; j < asteroids.length; j++) {
                // 左边为正数，右边为负数，会相撞
                if (asteroids[i] > 0 && asteroids[j] < 0) {
                    if (Math.abs(asteroids[i])>Math.abs(asteroids[j]))
                        removeIndex.add(j);
                    else if (Math.abs(asteroids[i])<Math.abs(asteroids[j]))
                        removeIndex.add(i);
                    else {
                        removeIndex.add(i);
                        removeIndex.add(j);
                    }
                }
                break;
            }
        }

        removeIndex.sort(((o1, o2) -> {
            if (o1>o2) return 1;
            else return -1;
        }));
        int i = 0;
        for (Integer index : removeIndex) {
            res.remove(index-i);
            i++;
        }

        int[] result = new int[res.size()];
        for (int j = 0; j < res.size(); j++) {
            result[j] = (int) res.get(j);
        }

        return result;
    }*/


    public static void main(String[] args) {
        int[] test = new int[]{10,2,-5};
        int[] res = asteroidCollision(test);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
