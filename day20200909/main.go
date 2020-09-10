package main

import (
	"fmt"
	"sort"
)

/**
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]


提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	candidates := []int{7, 2, 3}
	target := 18
	res := combinationSum(candidates, target)
	fmt.Print(res)
}

type A struct {
	res [][]int
}

func combinationSum(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	var h []int
	a := &A{}
	a.get(candidates, target, h)
	return a.res
}

func (a *A) get(arr []int, target int, h []int) {

	for i := len(arr) - 1; i >= 0; i-- {

		if arr[i] > target {
			continue
		}
		if arr[i] == target {
			tmp := append(h, arr[i])
			a.res = append(a.res, tmp)
			continue
		}
		tmp := make([]int, len(h))
		copy(tmp, h)
		a.get(arr[:i+1], target-arr[i], append(tmp, arr[i]))
	}
}
