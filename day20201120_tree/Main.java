package day20201120_tree;


import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 *
 *  pre 和 post 遍历中的值是不同的正整数。
 *
 *  
 *
 * 示例：
 *
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *  
 *
 * 提示：
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/11/20 18:45
 */
public class Main {
    public static void main(String[] args) {
        int[] pre = {1,2,4,5,3,6,7};
        int[] post = {4,5,2,6,7,3,1};
        Solution solution = new Solution();
        TreeNode res = solution.constructFromPrePost(pre, post);
        solution.midOut(res);
        System.out.println();
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return root;
        }
        int leftLength = 0;
        for (int i = 0; i < post.length; i++) {
            if (post[i] == pre[1]) {
                leftLength = i + 1;
            }
        }

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, leftLength + 1),
                Arrays.copyOfRange(post, 0, leftLength));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, leftLength + 1, pre.length),
                Arrays.copyOfRange(post, leftLength, post.length - 1));

        return root;
    }

    public void midOut(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        midOut(treeNode.left);
        System.out.print(treeNode.val + " ");
        midOut(treeNode.right);
    }
}
