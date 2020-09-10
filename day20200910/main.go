package main

import (
	"fmt"
	"reflect"
	"sort"
)

/**
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	candidates := []int{2, 5, 1, 2, 1}
	target := 5
	res := combinationSum2(candidates, target)
	fmt.Print(res)
}

type A struct {
	res [][]int
}

func combinationSum2(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	var h []int
	a := &A{}
	a.get(candidates, target, h)
	return a.res
}

func (a *A) get(arr []int, target int, h []int) {
	for i := len(arr) - 1; i >= 0; i-- {
		flag := true
		if arr[i] > target {
			continue
		}
		if arr[i] == target {

			tmp := append(h, arr[i])
			for _, u := range a.res {
				if reflect.DeepEqual(tmp, u) {
					flag = false
					break
				}
			}
			if flag {
				a.res = append(a.res, tmp)
			}

			continue
		}
		tmp := make([]int, len(h))
		copy(tmp, h)
		a.get(arr[:i], target-arr[i], append(tmp, arr[i]))
	}
}
