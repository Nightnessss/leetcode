package main

import (
	"container/list"
	"fmt"
)

/**
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
输出:
合并后的树:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
注意: 合并必须从两个树的根节点开始。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-two-binary-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	a := []int{1, 3, 2, 5}
	b := []int{2, 1, 3, -1, 4, -1, 7}
	ta := makeTree(a)
	tb := makeTree(b)
	fmt.Println(ta)
	fmt.Println(tb)

	res := mergeTrees(ta, tb)
	f := printTree(res)
	fmt.Println(f)

}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func mergeTrees(t1 *TreeNode, t2 *TreeNode) *TreeNode {
	if t1 == nil {
		if t2 == nil {
			return t1
		}
		t1 = t2
		return t1
	}
	var left, right *TreeNode
	if t2 != nil {
		left = t2.Left
		right = t2.Right
		t1.Val += t2.Val
	}
	t1.Left = mergeTrees(t1.Left, left)
	t1.Right = mergeTrees(t1.Right, right)
	return t1
}

type Queue struct {
	list.List
}

func (queue *Queue) Push(data interface{}) {
	queue.PushBack(data)
}
func (queue *Queue) Pop() interface{} {
	return queue.Remove(queue.Front())
}
func (queue *Queue) Size() int {
	return queue.Len()
}

func makeTree(arr []int) *TreeNode {
	p := &TreeNode{
		Val: arr[0],
	}
	root := p
	q := &Queue{}
	i := 0
	for q.Size() > 0 || i == 0 {
		if 2*i+1 < len(arr) && p != nil {
			if arr[2*i+1] == -1 {
				p.Left = nil
			} else {
				p.Left = &TreeNode{Val: arr[2*i+1]}
			}
			q.Push(p.Left)
		}
		if 2*i+2 < len(arr) && p != nil {
			if arr[2*i+2] == -1 {
				p.Right = nil
			} else {
				p.Right = &TreeNode{Val: arr[2*i+2]}
			}
			q.Push(p.Right)
		}
		i++
		p = q.Pop().(*TreeNode)
	}
	return root
}

func printTree(tree *TreeNode) (res []int) {
	if tree == nil {
		return nil
	}
	res = append(res, tree.Val)

	q := &Queue{}
	i := 0
	for q.Size() > 0 || i == 0 {
		i = 1
		if tree.Left != nil {
			res = append(res, tree.Left.Val)
			q.Push(tree.Left)
		} else {
			res = append(res, -1)
		}
		if tree.Right != nil {
			res = append(res, tree.Right.Val)
			q.Push(tree.Right)
		} else {
			res = append(res, -1)
		}
		tree = q.Pop().(*TreeNode)
	}
	return res
}
