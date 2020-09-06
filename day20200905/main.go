package main

import "fmt"

/**
给出集合[1,2,3,…,n]，其所有元素共有n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定n 和k，返回第k个排列。

说明：

给定 n的范围是 [1, 9]。
给定 k的范围是[1, n!]。
示例1:

输入: n = 3, k = 3
输出: "213"
示例2:

输入: n = 4, k = 9
输出: "2314"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutation-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	fmt.Println(getPermutation(4, 9))
}

func getPermutation(n int, k int) string {
	mark := make(map[int]int)
	for i := 1; i <= n; i++ {
		mark[i] = 0
	}
	k--
	return doMark(n, k, mark)
}

func doMark(n int, k int, mark map[int]int) string {
	if n == 0 {
		return ""
	}
	res := factorial(n - 1)
	first := k/res + 1

	var nk int
	var num int
	nk = k % res
	for i := 1; ; i++ {
		val := mark[i]
		key := i
		if val == 1 {
			continue
		}
		if first > 1 {
			first--
			continue
		}
		num = key
		break
	}
	mark[num] = 1

	s := doMark(n-1, nk, mark)

	return fmt.Sprintf("%d%s", num, s)
}
func factorial(n int) (facVal int) {
	facVal = 1
	if n < 0 {
		fmt.Print("Factorial of negative number doesn't exist.")
	} else {
		for i := 1; i <= n; i++ {
			facVal *= i
		}
	}
	return facVal

}
