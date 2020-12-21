package day20201221;

/**
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1:
 * <p>
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *  示例 2:
 * <p>
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/12/21 14:26
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] cost = {0, 0, 0, 1};
        System.out.println(solution.minCostClimbingStairs(cost));
    }
}

/**
 * 题意解读：
 * cost 的长度为 n , n 为总阶梯数
 * 以样例2 cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]为例
 * 先走 cost[0] = 1，然后走两步到 cost[2] = 1，然后走两步到 cost[4] = 1
 * 然后走两步到 cost[6] = 1，然后走一步到 cost[7] = 1，然后走两步到 cost[9] = 1，然后走一步到顶
 * 因为有走一步和两步两种状态，适合用动态规划
 * dp[n] = min(dp[n - 1] + cost[n], dp[n - 2] + cost[n])
 */
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
