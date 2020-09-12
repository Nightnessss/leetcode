package main

import (
	"fmt"
)

/**
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。



示例 1：

输入：
    3
   / \
  9  20
    /  \
   15   7
输出：[3, 14.5, 11]
解释：
第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。


提示：

节点值的范围在32位有符号整数范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	root := &TreeNode{
		Val: 3,
		Left: &TreeNode{
			Val:   9,
			Left:  nil,
			Right: nil,
		},
		Right: &TreeNode{
			Val: 20,
			Left: &TreeNode{
				Val:   15,
				Left:  nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val:   7,
				Left:  nil,
				Right: nil,
			},
		},
	}
	res := averageOfLevels(root)
	fmt.Print(res)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func averageOfLevels(root *TreeNode) []float64 {
	dep := getTreeDepth(root)
	res := make([]float64, dep)
	count := make([]float64, dep)

	res, count = do(root, 0, res, count)
	for i := 0; i < len(res); i++ {
		res[i] = res[i] / count[i]
	}
	return res
}

func do(t *TreeNode, dep int, res []float64, count []float64) ([]float64, []float64) {
	if t == nil {
		return res, count
	}
	res[dep] += float64(t.Val)
	count[dep]++
	res, count = do(t.Left, dep+1, res, count)
	res, count = do(t.Right, dep+1, res, count)
	return res, count
}

func getTreeDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}

	lDepth := getTreeDepth(root.Left)
	rDepth := getTreeDepth(root.Right)
	if lDepth > rDepth {
		return lDepth + 1
	}
	return rDepth + 1
}
