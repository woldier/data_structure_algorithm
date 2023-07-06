package com.woldier.datastruacture.ch2.d10_tree;

/**
 * description 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/6 上午8:47
 */
public class E10Leetcode106 {

    public class TreeNode {
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree1(inorder,0,postorder,0,postorder.length);

    }


    /**
     * description 中后续遍历建树
     *
     * @param inorder
     * @param postorder
     * @return
     * @author: woldier
     * @date: 2023/7/6 上午8:48
     */
    private TreeNode buildTree1(int[] inorder, int start1, int[] postorder, int start2, int length) {
        if (length == 0) return null;
        if (length == 1) return new TreeNode(postorder[start2+length-1]);
        int p = 0;
        while(p<length&&inorder[p+start1]!=postorder[start2+length-1]) p++;

        TreeNode l, r;
        l = buildTree1(inorder,start1,postorder,start2,p);
        r = buildTree1(inorder,start1+p+1,postorder,start2+p,length-1-p);
        return new TreeNode(postorder[start2+length-1], l, r);
    }
}
