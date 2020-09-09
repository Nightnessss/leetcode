package main

import "fmt"

/**
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combinations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	fmt.Print(combine(4, 2))
}

func combine(n int, k int) (ans [][]int) {
	// 初始化
	// 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
	// 末尾加一位 n + 1 作为哨兵
	temp := []int{}
	for i := 1; i <= k; i++ {
		temp = append(temp, i)
	}
	temp = append(temp, n+1)

	for j := 0; j < k; {
		comb := make([]int, k)
		copy(comb, temp[:k])
		ans = append(ans, comb)
		// 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
		// 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
		for j = 0; j < k && temp[j]+1 == temp[j+1]; j++ {
			temp[j] = j + 1
		}
		// j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
		temp[j]++
	}
	return
}
