package main

import "fmt"

/**
在计算机界中，我们总是追求用有限的资源获取最大的收益。

现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

注意:

给定 0 和 1 的数量都不会超过 100。
给定字符串数组的长度不会超过 600。
示例 1:

输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
输出: 4

解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
示例 2:

输入: Array = {"10", "0", "1"}, m = 1, n = 1
输出: 2

解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ones-and-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	//array := []string{"10", "0001", "111001", "1", "0"}
	array := []string{"0", "11", "1000", "01", "0", "101", "1", "1", "1", "0", "0", "0", "0", "1", "0", "0110101", "0", "11", "01", "00", "01111", "0011", "1", "1000", "0", "11101", "1", "0", "10", "0111"}
	//array := []string{"10", "1", "0"}
	m := 9
	n := 80
	res := findMaxForm(array, m, n)
	fmt.Print(res)
}

func findMaxForm(strs []string, m int, n int) int {

	dp := make([][][]int, len(strs)+1)
	for k := 0; k <= len(strs); k++ {
		dp[k] = make([][]int, m+1)
		for i := 0; i < m+1; i++ {
			dp[k][i] = make([]int, n+1)
		}
	}

	for i := 0; i < len(strs); i++ {
		dp[i][0][0] = 0

	}
	for k, c := range strs {
		cnt := count(c)
		fmt.Print(c + " ")
		fmt.Println(k, cnt[0], cnt[1])
		for i := 0; i <= m; i++ {
			for j := 0; j <= n; j++ {
				dp[k+1][i][j] = dp[k][i][j]
				if i >= cnt[0] && j >= cnt[1] {
					dp[k+1][i][j] = max(dp[k][i][j], dp[k][i-cnt[0]][j-cnt[1]]+1)
				}
			}
		}

	}

	return dp[len(strs)][m][n]
}
func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func count(str string) (res [2]int) {
	for _, c := range str {
		if c == '0' {
			res[0]++
			continue
		}
		res[1]++
	}
	return res
}
