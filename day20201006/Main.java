package day20201006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
 * <p>
 * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
 * <p>
 * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
 * <p>
 * 示例 1:
 * <p>
 * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释:
 * 如下为给定的树的示意图：
 * 0
 * / \
 * 1   2
 * /|\
 * 3 4 5
 * <p>
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 * 说明: 1 <= N <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-distances-in-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/6 21:10
 */
public class Main {
}

class Solution {

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            hashMap.put(i, 0);
            List<int[]> list = new ArrayList<>(Arrays.asList(edges));
            int c = 0;
            while (!list.isEmpty()) {
                c++;
                int finalC = c;
                HashMap<Integer, Integer> tmp = new HashMap<>();
                int finalI = i;
                hashMap.forEach((k, v) -> {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j)[0] == k) {
                            tmp.put(list.get(j)[1], finalC);
                            res[finalI] += finalC;
                            list.remove(list.get(j--));
                        } else if (list.get(j)[1] == k) {
                            tmp.put(list.get(j)[0], finalC);
                            list.remove(list.get(j--));
                            res[finalI] += finalC;
                        }
                    }
                });
                hashMap = (HashMap<Integer, Integer>) tmp.clone();
            }
        }
        return res;
    }
}
