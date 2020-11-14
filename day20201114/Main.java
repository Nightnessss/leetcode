package day20201114;

import java.util.*;

/**
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *  
 *
 * 提示：
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/11/14 22:32
 */
public class Main {
    public static void main(String[] args) {
        int[] arr1 = {28, 6, 22, 8, 44, 17};
        int[] arr2 = {22, 28, 8, 6};

        Solution solution = new Solution();
        solution.quickSort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
        int[] res = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(res));

    }
}

class Solution {
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {

        int i = 0;
        int j = arr1.length - 1;
        int t = 0;
        while (t < arr2.length) {
            while (i < j) {
                while (arr1[i] == arr2[t]) {
                    i++;
                    j = arr1.length - 1;
                }
                while (arr1[j] != arr2[t] && i < j) {
                    j--;
                }
                if (i < j) {
                    swap(arr1, i, j);
                }
            }
            t++;
            j = arr1.length - 1;
        }
        quickSort(arr1, i, arr1.length - 1);

        return arr1;
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < arr.length - 1; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Integer[] tmp = new Integer[arr1.length];
        int c = 0;
        for (int a : arr1) {
            tmp[c++] = a;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            if (map.containsKey(o1) && map.containsKey(o2)) {
                return map.get(o1) > map.get(o2) ? 1 : -1;
            }
            if (map.containsKey(o1)) {
                return -1;
            }
            if (map.containsKey(o2)) {
                return 1;
            }
            return o1 > o2 ? 1 : -1;
        };
        Arrays.sort(tmp, comparator);
        c = 0;
        for (int a : tmp) {
            arr1[c++] = a;
        }
        return arr1;
    }

}
