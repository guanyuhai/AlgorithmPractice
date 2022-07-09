package LeetCode.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 出现次数最多的子树元素之和
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/26 17:06
 */
public class LeetCode508 {

    private int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        HashMap<Integer,Integer> map = new HashMap<>();
        getSum(root,map);

        //然后求出map中value最大值对应的Key
        List<Integer> res = new LinkedList<>();
        for(Integer i : map.keySet()){
            if(map.get(i) == max)
                res.add(i);
        }
        int[] resArr = new int[res.size()];
        for(int i = 0;i < res.size();i++){
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    private int getSum(TreeNode root,HashMap<Integer,Integer> map){
        if(root == null) return 0;
        //求出当前节点为根的元素和
        int left = getSum(root.left,map);
        int right = getSum(root.right,map);
        int val = left+right+root.val;
        map.put(val,map.getOrDefault(val,0)+1);
        max = Math.max(max,map.get(val));
        return val;
    }
}
