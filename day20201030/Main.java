package day20201030;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *  
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author weirwei 2020/10/30 12:34
 */
public class Main {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int res = new Solution().islandPerimeter(grid);
        System.out.println(res);
    }
}

class Solution {
    private int sum = 0;
    private final int[][] step = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };
    public int islandPerimeter(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    around(grid, row, col, grid.length, grid[row].length);
                }
            }
        }
        return sum;
    }

    private void around(int[][] grid, int row, int col, int rowMax, int colMax) {
        for (int i = 0; i < 4; i++) {
            int rowTmp = row + step[i][0];
            int colTmp = col + step[i][1];
            if (rowTmp >= rowMax || colTmp >= colMax || rowTmp < 0 || colTmp < 0 || grid[rowTmp][colTmp] == 0) {
                sum++;
            }
        }
    }
}
