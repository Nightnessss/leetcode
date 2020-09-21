package main

import "fmt"

/**
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。



例如：

输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	root := &TreeNode{
		Val:   5,
		Left:  &TreeNode{Val: 2},
		Right: &TreeNode{Val: 13},
	}
	res := convertBST(root)
	fmt.Print(res)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var do func(root *TreeNode)

func convertBST(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	sum := 0
	do = func(root *TreeNode) {
		if root == nil {
			return
		}

		do(root.Right)
		sum += root.Val
		root.Val = sum
		do(root.Left)
	}
	do(root)
	return root
}
