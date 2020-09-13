package main

import "fmt"

/**
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false


提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
*/

func main() {
	board := [][]byte{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}
	//board := [][]byte{{'a', 'a'}}
	word := "ABCESEEEFS"
	find := exist(board, word)
	fmt.Print(find)
}

func exist(board [][]byte, word string) bool {
	mark := make([][]int, len(board))
	for index := range mark {
		mark[index] = make([]int, len(board[0]))
	}
	for i := 0; i < len(board); i++ {
		for j := 0; j < len(board[i]); j++ {
			find := dfs(board, word, i, j, mark)
			if find {
				return true
			}

		}
	}
	return false
}

var step = [][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func dfs(board [][]byte, word string, startI int, startJ int, mark [][]int) bool {

	if board[startI][startJ] != word[0] {
		return false
	}
	if len(word) == 1 {
		return true
	}
	mark[startI][startJ] = 1
	defer func() { mark[startI][startJ] = 0 }()
	for i := 0; i < 4; i++ {
		targetI := startI + step[i][0]
		targetJ := startJ + step[i][1]
		if targetI < 0 || targetI >= len(board) || targetJ < 0 || targetJ >= len(board[0]) ||
			mark[targetI][targetJ] == 1 {
			continue
		}

		find := dfs(board, word[1:], targetI, targetJ, mark)
		if find {
			return true
		}
	}
	return false
}
