package main

import (
	"fmt"
	"reflect"
	"sort"
)

/**

 */

func main() {
	candidates := []int{2, 5, 1, 2, 1}
	target := 5
	res := combinationSum2(candidates, target)
	fmt.Print(res)
}

type A struct {
	res [][]int
}

func combinationSum2(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	var h []int
	a := &A{}
	a.get(candidates, target, h)
	return a.res
}

func (a *A) get(arr []int, target int, h []int) {
	for i := len(arr) - 1; i >= 0; i-- {
		flag := true
		if arr[i] > target {
			continue
		}
		if arr[i] == target {

			tmp := append(h, arr[i])
			for _, u := range a.res {
				if reflect.DeepEqual(tmp, u) {
					flag = false
					break
				}
			}
			if flag {
				a.res = append(a.res, tmp)
			}

			continue
		}
		tmp := make([]int, len(h))
		copy(tmp, h)
		a.get(arr[:i], target-arr[i], append(tmp, arr[i]))
	}
}
