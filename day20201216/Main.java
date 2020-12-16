package day20201216;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/12/16 16:17
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String pattern = "abba", str = "dog cat cat dog";
        System.out.println(solution.wordPattern(pattern, str));
    }
}

class Solution {
    public boolean wordPattern(String pattern, String s) {
        char[] patterns = pattern.toCharArray();
        String[] strs = s.split(" ");
        if (patterns.length != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>(patterns.length);
        int i = 0;
        while (i < patterns.length) {
            if (map.containsKey(patterns[i])) {
                String tmp = map.get(patterns[i]);
                if (!tmp.equals(strs[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(strs[i])) {
                    return false;
                }
                map.put(patterns[i], strs[i]);
            }
            i++;
        }
        return true;

    }
}

