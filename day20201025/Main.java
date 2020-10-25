package day20201025;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 *
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 *
 * 如果不含有 “山脉” 则返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 *
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *  
 *
 * 提示：
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/25 23:04
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {
                2,0,2,0};
        System.out.println(new Solution().longestMountain(a));
    }
}

class Solution {
    public static int UP = 0;
    public static int DOWN = 1;
    public static int UNKNOWN = -1;

    public int longestMountain(int[] A) {
        if (A.length == 0) return 0;
        int max = 0;
        int tmp = 1;
        int pre = A[0];
        int status = UNKNOWN;
        boolean flag = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > pre && status != DOWN) {
                tmp++;
                status = UP;
            } else if (A[i] < pre && status != UNKNOWN) {
                tmp++;
                status = DOWN;
                flag = true;
            } else if (A[i] > pre && status == DOWN) {
                max = Math.max(max, tmp);
                tmp = 2;
                status = UP;
            } else if (A[i] == pre) {
                if (flag) {
                    max = Math.max(max, tmp);
                }
                tmp = 1;
                status = UNKNOWN;
            }
            pre = A[i];
        }
        if (flag) {
            max = Math.max(max, tmp);
        }
        return max == 1 ? 0 : max;
    }
}