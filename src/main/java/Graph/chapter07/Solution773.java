package Graph.chapter07;

import java.util.*;

/**
 * @description: 滑动砖瓦: https://leetcode-cn.com/problems/sliding-puzzle/
 *
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class Solution773 {
    private int[][] dirs = { {-1,0}, {0,1}, {1,0}, {0,-1} };

    public int slidingPuzzle(int[][] board) {   // 2×3

        Queue<String> queue = new LinkedList<>();
        Map<String,Integer> visited = new HashMap<>();

        String initialState = boardToString(board);
        if (initialState.equals("123450"))
            return 0;

        // BFS
        queue.add(initialState);
        visited.put(initialState,0);
        while (!queue.isEmpty()){
            String cur = queue.remove();
            List<String> nexts = getNexts(cur);
            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if (next.equals("123450"))
                        return visited.get(next);
                }
            }
        }

        return -1;
    }

    private List<String> getNexts(String cur) {
        int[][] board = stringToBoard(cur);
        // 找到当前0的位置在哪
        int zero;
        for (zero=0; zero<6; zero++) {
            if (board[zero / 3][zero % 3] == 0)
                break;
        }
        // 将zero转为对应的坐标
        int zx = zero / 3;
        int zy = zero % 3;

        // 由于移动格子，只能往上下左右移动，所以又是个四联通的问题
        List<String> res = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int nextX = zx + dirs[d][0];
            int nextY = zy + dirs[d][1];

            if (inArea(nextX,nextY)){
                swap(board,zx,zy,nextX,nextY);
                res.add(boardToString(board));
                swap(board,zx,zy,nextX,nextY); //交换回来, 便于获取剩下的状态
            }
        }
        return res;
    }

    // 交换格子的位置
    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private boolean inArea(int x, int y) {
        return x>=0 && x<2 && y>=0 && y<3;
    }

    private int[][] stringToBoard(String s){
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i++)
            board[i/3][i%3] = s.charAt(i) - '0';
        return board;
    }

    // 把board数组转成String
    private String boardToString(int[][] board){
        StringBuilder res = new StringBuilder();
        // 2*3的数组
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                res.append(board[i][j]);

        return res.toString();
    }
}
