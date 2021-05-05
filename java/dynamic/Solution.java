package com.xdja.security.dynamic;

import java.util.Arrays;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 *     例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 *
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * 提示：
 *
 *     1 <= text1.length, text2.length <= 1000
 *     text1 和 text2 仅由小写英文字符组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public static void main(String[] args) {
        int i = uniquePath(13, 7);
        System.out.println(i);

        int j = uniquePath1(13, 7);
        System.out.println(j);

        int[][] arr = new int[1][1];
        arr[0][0] = 0;
        int i1 = uniquePathWithObstacle(arr);
        System.out.println(i1);

        int i2 = longestCommonSubsequence("abc", "ab");
        System.out.println(i2);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }

    private static int uniquePathWithObstacle2(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int []dp = new int[m];
        dp[0] = 1;
        for (int row[] : obstacleGrid){
            for (int i = 0; i < m; i++) {
                if (row[i] == 1) {
                    dp[i] = 0;
                }else if (i-1 >= 0) {
                    dp[i] += dp[i-1];
                }
            }
        }
        return dp[m-1];
    }
    private static int uniquePathWithObstacle1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];

        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                }else if (obstacleGrid[i][j] == 0 && j-1 >=0){
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[n-1];
    }

    private static int uniquePathWithObstacle(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 从本质上来说，只需要把上一行的值记录下来，然后进行层层累加，并不需要二维数组，所以可以进一步优化为一维数组
     * 通过使用一维数组记录上一组的状态，第二组进行累加
     * @param m
     * @param n
     * @return
     */
    private static int uniquePath1(int m, int n) {
        int arr[] = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[j] += arr[j-1];
            }
        }
        return arr[n-1];
    }

    /**
     * 动态规划题目技巧，首先找出规律，最笨的办法f[i][j] = f[i-1][j] + f[i][j-1]
     * @param m
     * @param n
     * @return
     */
    private static int uniquePath(int m, int n) {
       int[][] f =  new int[m][n];

        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            f[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i-1][j] + f[i][j-1];
            }
        }
        return f[m-1][n-1];
    }
}
