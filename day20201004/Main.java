package day20201004;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/4 23:00
 */
public class Main {
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int p = 0;
        ListNode head = new ListNode();
        ListNode root = head;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = new ListNode((l2.val + p) % 10);
                p = (l2.val + p) / 10;
                l2 = l2.next;
                head = head.next;
                continue;
            }
            if (l2 == null) {
                head.next = new ListNode((l1.val + p) % 10);
                p = (l1.val + p) / 10;
                l1 = l1.next;
                head = head.next;
                continue;
            }
            int[] res = add(l1, l2, p);
            head.next = new ListNode(res[0]);
            p = res[1];
            l1 = l1.next;
            l2 = l2.next;
            head = head.next;
        }
        if (p != 0) {
            head.next = new ListNode(p);
        }
        return root.next;
    }

    public int[] add(ListNode l1, ListNode l2, int a) {
        int[] res = new int[2];
        res[0] = (l1.val + l2.val + a) % 10;
        res[1] = (l1.val + l2.val + a) / 10;
        return res;
    }
}
