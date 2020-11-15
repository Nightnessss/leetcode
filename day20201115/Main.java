package day20201115;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/11/15 22:12
 */
public class Main {
    public static void main(String[] args) {
        String num = "10";
        int k = 1;
        Solution solution = new Solution();
        String res = solution.removeKdigits(num, k);
        System.out.println(res);
    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        char[] chars = num.toCharArray();
        Stack<Character> deque = new Stack<>();
        for (char c : chars) {
            while (deque.size() > 0 && c < deque.peek() && k > 0) {
                deque.pop();
                k--;
            }
            deque.add(c);
        }
        while (k > 0) {
            deque.pop();
            k--;
        }
        if (deque.size() == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (deque.size() > 0) {
            res.insert(0, deque.pop());
        }
        while (res.length() > 0 && res.substring(0, 1).equals("0")) {
            res.delete(0, 1);
        }
        if (res.length() == 0) {
            return "0";
        }
        return String.valueOf(res);
    }
}
