package LeetCode.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后问题
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 'Q' 和 '.' 分别代表了皇后和空位。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 17:23
 */
public class LeetCode51 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 构建棋盘，默认都为空位，
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return res;
    }

    private void backTrack(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0;col < n; ++col) {
            // 检测是否合法，如果合法则在当前位置添加皇后
            if (isValid (row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row+1, chessboard);
                // 回溯，撤销皇后
                chessboard[row][col] = '.';
            }
        }

    }

    // 将char[][] 转为list
    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }


    /**
     * 验证棋盘是否合法
     * 按照如下标准去重：
     * 1 不能同行
     * 2 不能同列
     * 3 不能同斜线 （45度和135度角）
     *
     * 为什么没有在同行进行检查呢？
     * 因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
     *
     * @param row
     * @param col
     * @param n
     * @param chessboard
     * @return
     */
    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
