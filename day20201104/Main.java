package day20201104;

import java.util.*;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。

 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

  

 示例 1：

 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出：[[1,5],[6,9]]
 示例 2：

 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出：[[1,2],[3,10],[12,16]]
 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
  

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insert-interval
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * @author weirwei 2020/11/4 13:53
 */
public class Main {
    public static void main(String[] args) {
//        int[][] a = {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newInterval = {4,8};
        int[][] a = {{1,5}};
        int[] newInterval = {2,3};
        int[][] res = new Solution().insert(a, newInterval);
        System.out.println(Arrays.deepToString(res));
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][] {newInterval};
        List<int[]> res = new ArrayList<>();
        int min = newInterval[0];
        int max = newInterval[1];
        int flag = 0;
        for (int[] interval : intervals) {
            if (min <= interval[1] && max > interval[1] || min < interval[0] && max >= interval[0]) {
                max = Math.max(max, interval[1]);
                min = Math.min(min, interval[0]);
                flag = 1;
                continue;
            }
            if (flag == 1) {
                res.add(new int[]{min, max});
                flag = -1;
            }
            if (min >= interval[0] && max <= interval[1]) {
                flag = -1;
            }
            res.add(interval);
        }
        if (flag != -1) {
            res.add(new int[]{min, max});
        }
        res.sort(Comparator.comparingInt(o -> o[0]));
        int[][] resArr = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
}