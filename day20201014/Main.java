package day20201014;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/14 16:20
 */
public class Main {
    public static void main(String[] args) {
        String[] s = {"cool","lock","cook"};
        List<String> res = new Solution().commonChars(s);
        System.out.println(res);
    }
}
class Solution {
    public List<String> commonChars(String[] A) {
        List<HashMap<String, Integer>> hashMapList = new ArrayList<>();
        for (String a : A) {
            HashMap<String, Integer> tmp = new HashMap<>();
            for (int i = 0; i < a.length(); i++) {
                String c = a.substring(i, i+1);
                if (tmp.containsKey(c)) {
                    tmp.put(c, tmp.get(c) + 1);
                    continue;
                }
                tmp.put(c, 1);
            }
            hashMapList.add(tmp);
        }
        HashMap<String, Integer> res = hashMapList.get(0);
        for (HashMap.Entry<String, Integer> entry : res.entrySet()) {
            for (HashMap<String, Integer> hashMap : hashMapList) {
                if (hashMap.containsKey(entry.getKey())) {
                    res.put(entry.getKey(), Math.min(hashMap.get(entry.getKey()), entry.getValue()));
                    continue;
                }
                res.put(entry.getKey(), 0);
            }
        }
        List<String> list = new ArrayList<>();
        res.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                list.add(k);
            }
        });
        return list;
    }
}
