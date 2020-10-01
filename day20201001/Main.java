package day20201001;

/**
 * @author weirwei 2020/10/1 18:11
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.minimumOperations("rrryyyyyyrr");
        System.out.println(res);
    }
}

class Solution {
    public int minimumOperations(String leaves) {
        char[] chars = leaves.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][3];
        dp[0][0] = isRed(chars[0]);
        dp[0][1] = n;
        dp[0][2] = n;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + isRed(chars[i]);
            dp[i][1] = Math.min(dp[i - 1][0] + isYellow(chars[i]),
                    dp[i - 1][1] + isYellow(chars[i]));
            dp[i][2] = Math.min(dp[i - 1][1] + isRed(chars[i]),
                    dp[i - 1][2] + isRed(chars[i]));
        }
        return dp[n - 1][2];
    }

    private int isRed(char c) {
        if (c == 'r') {
            return 0;
        }
        return 1;
    }

    private int isYellow(char c) {
        if (c == 'y') {
            return 0;
        }
        return 1;
    }
}
