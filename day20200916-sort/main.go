package main

import (
	"fmt"
)

/**
给你一个整数数组 nums，请你将该数组升序排列。



示例 1：

输入：nums = [5,2,3,1]
输出：[1,2,3,5]
示例 2：

输入：nums = [5,1,1,2,0,0]
输出：[0,0,1,1,2,5]


提示：

1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

const (
	bubbleSortType  = 0
	selectSortType  = 1
	quickSortType   = 2
	quickSortGoType = 3
)

func main() {
	nums := []int{5, 2, 3, 1}
	sort(nums, 3)
	fmt.Print(nums)
}

func sort(nums []int, sortType int) {
	switch sortType {
	case bubbleSortType:
		bubbleSort(nums)
	case selectSortType:
		selectSort(nums)
	case quickSortType:
		quickSort(nums, 0, len(nums)-1)
	case quickSortGoType:
		done := make(chan struct{})
		go quickSort_go(nums, 0, len(nums)-1, done, 5)
		<-done
	default:
		bubbleSort(nums)
	}
}

func bubbleSort(nums []int) {
	for j := 0; j < len(nums)-1; j++ {
		for i := 0; i < len(nums)-1-j; i++ {
			if nums[i] > nums[i+1] {
				nums[i], nums[i+1] = nums[i+1], nums[i]
			}
		}
	}
}

func selectSort(nums []int) {
	for j := 0; j < len(nums)-1; j++ {
		min := j
		for i := j; i < len(nums); i++ {
			if nums[min] > nums[i] {
				min = i
			}
		}
		nums[min], nums[j] = nums[j], nums[min]
	}
}

func quickSort(nums []int, left int, right int) {
	if right <= left {
		return
	}
	var partition = func(nums []int, left int, right int) int {
		pivot := nums[right]
		i := left
		for j := left; j < len(nums)-1; j++ {
			if nums[j] < pivot {
				nums[i], nums[j] = nums[j], nums[i]
				i++
			}
		}
		nums[i], nums[right] = nums[right], nums[i]
		return i
	}

	p := partition(nums, left, right)
	quickSort(nums, left, p-1)
	quickSort(nums, p+1, right)

}

func quickSort_go(a []int, lo, hi int, done chan struct{}, depth int) {
	if lo >= hi {
		done <- struct{}{}
		return
	}
	depth--
	var partition = func(nums []int, left int, right int) int {
		pivot := nums[right]
		i := left
		for j := left; j < len(nums)-1; j++ {
			if nums[j] < pivot {
				nums[i], nums[j] = nums[j], nums[i]
				i++
			}
		}
		nums[i], nums[right] = nums[right], nums[i]
		return i
	}
	p := partition(a, lo, hi)
	if depth > 0 {
		childDone := make(chan struct{}, 2)
		go quickSort_go(a, lo, p-1, childDone, depth)
		go quickSort_go(a, p+1, hi, childDone, depth)
		<-childDone
		<-childDone
	} else {
		quickSort(a, lo, p-1)
		quickSort(a, p+1, hi)
	}
	done <- struct{}{}
}
