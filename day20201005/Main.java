package day20201005;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/5 22:49
 */
public class Main {
    public static void main(String[] args) {
//        int[] nums = {1, 0, -1, 0, -2, 2};
        int [] nums = {-4,-3,-2,-1,0,0,1,2,3,4};
        int target = 0;
        List<List<Integer>> res = new Solution().fourSum(nums, target);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, List<Integer[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                List<Integer[]> val = new ArrayList<>();
                if (map.containsKey(target - nums[i] - nums[j])) {
                    val = map.get(target - nums[i] - nums[j]);
                }
                val.add(new Integer[]{i, j});
                map.put((target - nums[i] - nums[j]), val);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(nums[i] + nums[j])) {
                    List<Integer[]> indexs = map.get(nums[i] + nums[j]);
                    for (Integer[] index : indexs) {
                        if (index[0] == i || index[1] == j || index[0] == j || index[1] == i) {
                            continue;
                        }
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[index[0]]);
                        list.add(nums[index[1]]);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.sort(Integer::compareTo);
                        if (res.contains(list)){
                            continue;
                        }
                        res.add(list);
                    }

                }
            }
        }
        return res;
    }
}
