package main

import "fmt"

/**
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subsets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	res := subsets([]int{9, 0, 3, 5, 7})
	fmt.Print(res)
}

func subsets(nums []int) [][]int {
	var res [][]int
	res = append(res, []int{})
	for i := 0; i < len(nums); i++ {
		n := len(res)
		for j := 0; j < n; j++ {
			tmp := res[j]
			tmp = append(tmp, nums[i])
			cp := make([]int, len(tmp))
			copy(cp, tmp)
			res = append(res, cp)
		}
	}
	return res
}
