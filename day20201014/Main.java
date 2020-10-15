package day20201014;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
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
