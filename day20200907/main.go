package main

import (
	"container/heap"
	"fmt"
)

/**
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。



示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]


提示：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
你可以按任意顺序返回答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func main() {
	num := []int{1, 1, 1, 2, 2, 3, 3, 3, 3, 5, 5}
	var t []int
	t = append(t, 1)
	t = append(t, 4)
	t = append(t, 3)
	a := topKFrequent(num, 2)
	fmt.Print(a)
}

type IHeap [][2]int

func (h IHeap) Len() int           { return len(h) }
func (h IHeap) Less(i, j int) bool { return h[i][1] < h[j][1] }
func (h IHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IHeap) Push(x interface{}) {
	*h = append(*h, x.([2]int))
}

func (h *IHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
func topKFrequent(nums []int, k int) []int {
	m := make(map[int]int)

	for _, v := range nums {
		m[v]++
	}
	h := &IHeap{}
	heap.Init(h)
	for key, value := range m {
		heap.Push(h, [2]int{key, value})
		if h.Len() > k {
			heap.Pop(h)
		}
	}
	ret := make([]int, k)
	for i := 0; i < k; i++ {
		ret[k-i-1] = heap.Pop(h).([2]int)[0]
	}
	return ret
}

//func quickSort(a []int, lo, hi int) {
//	if lo >= hi {
//		return
//	}
//	p := partition(a, lo, hi)
//	quickSort(a, lo, p-1)
//	quickSort(a, p+1, hi)
//}

//func partition(a []int, lo, hi int) int {
//	pivot := a[hi]
//	i := lo - 1
//	for j := lo; j < hi; j++ {
//		if a[j] < pivot {
//			i++
//			a[j], a[i] = a[i], a[j]
//		}
//	}
//	a[i+1], a[hi] = a[hi], a[i+1]
//	return i + 1
//}
