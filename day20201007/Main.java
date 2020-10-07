package day20201007;

import java.util.HashMap;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @author weirwei 2020/10/7 21:58
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {0};
        new Solution().sortColors(nums);
        System.out.println(nums);
    }
}

class Solution {
    public void sortColors(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int n : nums) {
            if (n > 2 || n < 0) return;
            int add = 1;
            if (hashMap.containsKey(n)) {
                add = hashMap.get(n) + 1;
            }
            hashMap.put(n, add);
        }
        if (hashMap.size() == 0) return;
        int c = 0;
        for (int i = 0; i < 3; i++) {
            if (!hashMap.containsKey(i)) continue;
            for (int j = 0; j < hashMap.get(i); j++) {
                nums[c++] = i;
            }
        }
    }
}


