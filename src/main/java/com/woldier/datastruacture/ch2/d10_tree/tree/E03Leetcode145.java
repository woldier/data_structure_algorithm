package com.woldier.datastruacture.ch2.d10_tree.tree;

import java.util.ArrayList;
import java.util.List;

public class E03Leetcode145 {
    public static class TreeNode {
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


    private List<Integer> preOrder(TreeNode p) {
        ArrayList<TreeNode> stack = new ArrayList();
        List<Integer> res = new ArrayList();
        TreeNode old = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.add(p); //入栈
                p = p.left; // 遍历左子树
            } else {
                TreeNode peek = stack.get(stack.size() - 1);

                if (peek.right == null || peek.right == old) {
                    res.add(stack.remove(stack.size() - 1).val);
                    old = peek;
                } else {
                    p = peek.right;
                }
            }
        }
        return res;
    }


}
