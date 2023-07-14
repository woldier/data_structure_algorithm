package com.woldier.datastruacture.ch2.d10_tree.tree;

/**
 * description 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * @author: woldier
 * @date: 2023/7/5 下午7:24
 */
public class E07Leetcode226 {
    public TreeNode invertTree(TreeNode root) {

        return null;
    }


    private TreeNode invertTreeRecursion(TreeNode p) {
        if (p == null) return p;

        TreeNode l = invertTreeRecursion(p.left);
        TreeNode r = invertTreeRecursion(p.right);
        p.left = r;
        p.right = l;
        return p;
    }
}
