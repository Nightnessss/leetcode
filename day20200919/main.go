package main

import "fmt"

/**
计算给定二叉树的所有左叶子之和。

示例：

    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-left-leaves
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	root := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 4,
			},
			Right: &TreeNode{
				Val: 5,
			},
		},
		Right: &TreeNode{
			Val: 3,
		},
	}
	res := sumOfLeftLeaves(root)
	fmt.Print(res)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func sumOfLeftLeaves(root *TreeNode) int {
	count := travel(root, 0)
	return count
}

func travel(root *TreeNode, count int) int {
	if root == nil {
		return count
	}
	if root.Left != nil && root.Left.Right == nil && root.Left.Left == nil {
		count += root.Left.Val
	}
	count = travel(root.Left, count)
	count = travel(root.Right, count)
	return count
}
