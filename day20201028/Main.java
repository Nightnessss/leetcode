package day20201028;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 *
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 *
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/28 15:45
 */
public class Main {
}

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int a : arr) {
            if (hashmap.containsKey(a)) {
                hashmap.put(a, hashmap.get(a) + 1);
                continue;
            }
            hashmap.put(a, 1);
        }
        int[] mark = new int[arr.length + 1];
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            if (mark[entry.getValue()] == 1) return false;
            mark[entry.getValue()] = 1;
        }
        return true;
    }
}
