package main

import "fmt"

/**
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	res := combinationSum3(3, 9)
	fmt.Print(res)
}

type A struct {
	res [][]int
}

func combinationSum3(k int, n int) [][]int {
	a := &A{}
	var h []int
	a.do(k, n, n, h)
	return a.res
}

func (a *A) do(k int, n int, pre int, h []int) {
	if k == 0 {
		return
	}
	t := n - accumulate(k-1)
	if t > pre {
		t = pre
	}
	if t > 9 {
		t = 9
	}
	for i := t; i > 0; i-- {
		if i == n {
			h = append(h, i)
			a.res = append(a.res, h)
			return
		}
		tmp := make([]int, len(h))
		copy(tmp, h)
		a.do(k-1, n-i, i-1, append(tmp, i))

	}
}

func accumulate(number int) (sum int) {
	sum = 0
	for i := 1; i <= number; i++ {
		sum += i
	}
	return
}
