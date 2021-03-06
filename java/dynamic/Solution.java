package com.xdja.security.dynamic;

import com.xdja.security.reverse.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     *
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * @param triangle
     * @return
     */
    Integer num[][];
    public  int minTrancleTotal0(List<List<Integer>> triangle){
        num = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) return 0;
        if (num[i][j] != null) return num[i][j];
        return num[i][j] = Math.min(dfs(triangle, i+1, j+1), dfs(triangle, i+1, j)) + triangle.get(i).get(j);
    }

    public static int minTrancleTotal(List<List<Integer>> triangle){
        int size = triangle.size();
        int[][] dp = new int[size+1][size+1];
        for (int i = size-2; i >=0 ; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public static int minTrancleTotal1(List<List<Integer>> triangle){
        int size = triangle.size();
        int dp[] = new int[size+1];
        for (int i = size-1; i >=0 ; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
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

    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 提示：
     *     1 <= arr.length <= 10^5
     *     -100 <= arr[i] <= 100
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //1. 暴力求解，双层for循环
        //2. 寻找重复性问题、max_sum[i] = sub_max(max_sum[i-1], 0) + sum[i]
        int res = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            nums[i] += Math.max(nums[i-1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 示例 1:
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // 暴力求解，双重for循环迭代求解
        // 找重复子问题,找出最大  max_pro[i] = max(sub_max(i-1) * max_pro[i], max_pro[i])
        // 找出最小
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(nums[i]*imax, nums[i]);
            imin = Math.min(nums[i]*imin, nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 你可以认为每种硬币的数量是无限的。
     *
     * 示例 1：
     *
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public int coinChange(int[] coins, int amount) {
        if(amount < 1) {
            return 0;
        }
        return this.coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int count[]) {
        if (rem < 0) {
            return -1;
        }

        if (rem == 0) {
            return 0;
        }
        //加入缓存，不进行重复计算
        if (count[rem-1] != 0) {
            return count[rem - 1];
        }
        //添加哨兵节点
        int min = Integer.MAX_VALUE;
        //遍历所有硬币
        for (int coin : coins) {
            //总数减去当前的硬币面值
            int res = coinChange(coins, rem-coin, count);
            //如果满足条件则添加一枚硬币数量
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        //缓存
        count[rem-1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }

    public int coinChange1(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int max = amount + 1;
        int dp[] = new int[amount + 1];

        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * dp[j]代表含义：填满容量为j的背包最少需要多少硬币
     * 初始化dp数组：因为硬币的数量一定不会超过amount，而amount <= 10410^4104，因此初始化数组值为10001；dp[0] = 0
     * 转移方程：dp[j] = min(dp[j], dp[j - coin] + 1)
     * 当前填满容量j最少需要的硬币 = min( 之前填满容量j最少需要的硬币, 填满容量 j - coin 需要的硬币 + 1个当前硬币）
     * 返回dp[amount]，如果dp[amount]的值为10001没有变过，说明找不到硬币组合，返回-1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
            //System.out.println(coin);
        }
        return dp[amount] != 10001 ? dp[amount] : -1;
    }

    public static void main(String[] args) {
       /* int i = uniquePath(13, 7);
        System.out.println(i);

        int j = uniquePath1(13, 7);
        System.out.println(j);

        int[][] arr = new int[1][1];
        arr[0][0] = 0;
        int i1 = uniquePathWithObstacle(arr);
        System.out.println(i1);

        int i2 = longestCommonSubsequence("abc", "ab");
        System.out.println(i2);*/
        //List<List<Integer>> trangle = new ArrayList<>();
        //List<Integer> list = new ArrayList<>();
        //list.add(1);
        //int[][] arr = {[[2],[3,4],[6,5,7],[4,1,8,3]]};
        int[] arr = {1,2,5};
        Solution solution = new Solution();
        int i = solution.coinChange2(arr, 12);
        System.out.println(i);
    }
}
