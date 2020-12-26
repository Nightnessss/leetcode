package day20201226_stack;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/12/26 14:12
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0}));
    }
}

/**
 * 单调栈 + 哨兵
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int[] newheights = new int[heights.length + 2];

        for (int i = 0; i < newheights.length; i++) {
            if (i == 0 || i == newheights.length - 1) {
                newheights[i] = 0;
            } else {
                newheights[i] = heights[i - 1];
            }
        }
        stack.push(0);
        for (int i = 1; i < newheights.length; i++) {
            while (newheights[stack.peek()] > newheights[i]) {
                int top = stack.pop();
                max = Math.max(max, newheights[top] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return max;
    }
}
