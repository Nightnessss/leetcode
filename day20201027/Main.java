package day20201027;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/27 20:47
 */
public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        4,
                        new TreeNode(2),
                        null
                ),
                new TreeNode(3)
        );
        List<Integer> res = new Solution().preorderTraversal(root);
        System.out.println(res);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!(queue.size() == 0)) {
            TreeNode node = queue.pop();
            res.add(node.val);
            if (node.right != null) {
                queue.addFirst(node.right);
            }
            if (node.left != null) {
                queue.addFirst(node.left);
            }

        }
        return res;
    }
}