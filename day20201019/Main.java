package day20201019;

import java.util.*;

/**
 * @author weirwei 2020/10/19 22:05
 */
public class Main {
    public static void main(String[] args) {
        String s = "a##c";
        String t = "#a#c";
        System.out.println(new Solution().backspaceCompare(s, t));
    }
}

class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] sChars = S.toCharArray();
        char[] tChars = T.toCharArray();
        Stack<String> sList = new Stack<>();
        Stack<String> tList = new Stack<>();
        for (char c : sChars) {
            if (c == '#') {
                if (!sList.empty()) {
                    sList.pop();
                }
                continue;
            }
            sList.add(String.valueOf(c));
        }
        for (char c : tChars) {
            if (c == '#') {
                if (!tList.empty()) {
                    tList.pop();
                }
                continue;
            }
            tList.add(String.valueOf(c));
        }
        return sList.equals(tList);
    }
}
