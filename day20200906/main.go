package main

import (
	"fmt"
	"strconv"
)

/**

给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]
*/

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	list := []string{"0", "-3", "-2", "-9", "-8", "", "", "2", "", "", "-5", "5"}

	t := &TreeNode{}
	if len(list) == 0 {
		return
	}
	if len(list[0]) != 0 {
		val, _ := strconv.Atoi(list[0])
		t.Val = val
	}

	t.readTree(list, 0)
	res := levelOrderBottom(t)
	fmt.Printf("%v", res)
}

func levelOrderBottom(root *TreeNode) [][]int {
	levelOrder := [][]int{}
	if root == nil {
		return levelOrder
	}
	queue := []*TreeNode{}
	queue = append(queue, root)
	for len(queue) > 0 {
		level := []int{}
		size := len(queue)
		for i := 0; i < size; i++ {
			node := queue[0]
			queue = queue[1:]
			level = append(level, node.Val)
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
		levelOrder = append(levelOrder, level)
	}
	for i := 0; i < len(levelOrder)/2; i++ {
		levelOrder[i], levelOrder[len(levelOrder)-1-i] = levelOrder[len(levelOrder)-1-i], levelOrder[i]
	}
	return levelOrder
}

func (t *TreeNode) readTree(list []string, dep int) int {
	if t == nil {
		return dep
	}
	if t.Left == nil {
		dep++
		if dep > len(list)-1 {
			return dep
		}
		if len(list[dep]) != 0 {
			val, _ := strconv.Atoi(list[dep])
			t.Left = &TreeNode{
				Val: val,
			}
		}
	}
	if t.Right == nil {
		dep++
		if dep > len(list)-1 {
			return dep
		}
		if len(list[dep]) == 0 {
			return dep
		}
		val, _ := strconv.Atoi(list[dep])
		t.Right = &TreeNode{
			Val: val,
		}

	}

	dep = t.Left.readTree(list, dep)
	dep = t.Right.readTree(list, dep)
	return dep
}
