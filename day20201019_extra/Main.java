package day20201019_extra;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 已知前序和中序求后序遍历
 * @author weirwei 2020/10/19 14:49
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pre = {1,2,4,5,3,6,7};
        int[] mid = {4,2,5,1,6,3,7};
        TreeNode res = solution.constructFromPreMid(pre, mid);
        solution.traver(res);
        System.out.println();
        solution.postOut(res);
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public TreeNode constructFromPreMid(int[] pre, int[] mid) {
        if (pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return root;
        }

        int leftLen = 0;
        for (int i = 0; i < mid.length; i++) {
            if (mid[i] == pre[0]) {
                leftLen = i;
            }
        }
        root.left = constructFromPreMid(Arrays.copyOfRange(pre, 1, leftLen + 1),
                Arrays.copyOfRange(mid, 0, leftLen));
        root.right = constructFromPreMid(Arrays.copyOfRange(pre, leftLen + 1, pre.length),
                Arrays.copyOfRange(mid, leftLen + 1, mid.length));

        return root;
    }

    public void traver(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode tmp = queue.poll();
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
            System.out.print(tmp.val + " ");
        }
    }

    public void postOut(TreeNode root) {
        if (root == null) {
            return;
        }

        postOut(root.left);
        System.out.print(root.val + " ");
        postOut(root.right);
    }
}
