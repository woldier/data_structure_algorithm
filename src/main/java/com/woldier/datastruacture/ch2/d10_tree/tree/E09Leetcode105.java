package com.woldier.datastruacture.ch2.d10_tree.tree;

/**
 * description 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/5 下午8:20
 */
public class E09Leetcode105 {
    /**
     * description 算法思想如下
     *
     * <pre>
     *     {@code
     *
     * 给定先序  1 2 4 3 6 7
     *    中序  4 2 1 6 3 7
     *    可以先根据先序找到root 1 然后将中序分为两端
     *         4 2 1 6 3 7
     *             ↑
     *      就可以将其分为两部分  左边 1 2 , 4 2,右边 3 6 7 , 6 3 7
     *     }
     * </pre>
     *
     * @param preorder 先序序列列
     * @param inorder
     * @return
     * @author: woldier
     * @date: 2023/7/5 下午8:23
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return recursion(preorder, inorder);
        return recursion2(preorder,0,inorder,0,preorder.length);
    }


    private TreeNode recursion(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);

        int p = 0;
        while (p < preorder.length && preorder[0] != inorder[p]) {
            p++;
        }
        TreeNode l, r;

        int[] pre = new int[p];
        int[] in = new int[p];
        System.arraycopy(preorder, 1, pre, 0, p);
        System.arraycopy(inorder, 0, in, 0, p);
        l = recursion(pre, in);

        int[] pre2 = new int[inorder.length - 1 - p];
        int[] in2 = new int[inorder.length - 1 - p];
        if (p != inorder.length - 1) {
            System.arraycopy(preorder, p + 1, pre2, 0, inorder.length - 1 - p);
            System.arraycopy(inorder, p + 1, in2, 0, inorder.length - 1 - p);
        }
        r = recursion(pre2, in2);

        return new TreeNode(preorder[0], l, r);
    }


    private TreeNode recursion2(int[] preorder, int start1, int[] inorder, int start2, int length) {
        if (length == 0) return null;
        if (length == 1) return new TreeNode(preorder[start1]);

        int p = 0;
        while (p < length && preorder[start1] != inorder[start2+p]) {
            p++;
        }
        TreeNode l, r;
        l = recursion2(preorder,start1+1,inorder,start2,p);
        r = recursion2(preorder,start1+p+1,inorder,start2+p+1,length-1-p);
        return new TreeNode(preorder[start1], l, r);
    }

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
}
