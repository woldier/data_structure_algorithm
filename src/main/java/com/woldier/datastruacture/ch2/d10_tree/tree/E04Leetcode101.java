package com.woldier.datastruacture.ch2.d10_tree.tree;

import java.util.ArrayList;

/**
 * description 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * @author: woldier
 * @date: 2023/7/5 上午9:51
 */
public class E04Leetcode101 {
    public static class TreeNode {
        public



        int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode( TreeNode left,int val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {

        return false;
    }

    /**
     * description 对称二叉树的递归实现
     *
     * @param l
     * @param r
     * @return
     * @author: woldier
     * @date: 2023/7/5 上午10:27
     */
    public boolean isSymmetricRecursion(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;  //如果两者都为null 返回true
        if (l == null || r == null || l.val != r.val)
            return false; //如果两者有一个是null,说明不等返回false ,否则说明两个都不是null,进而取比较他们的值是否相等
        return isSymmetricRecursion(l.left, r.right) && isSymmetricRecursion(l.right, r.left);
    }

    /**
     * description 对称二叉树的非递归实现
     * 使用双栈,用栈记录访问的信息,便于节点回退
     *
     * @param l
     * @param r
     * @return
     * @author: woldier
     * @date: 2023/7/5 上午10:27
     */
    public boolean isSymmetricStack(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        Stack stackL = new Stack();
        Stack stackR = new Stack();
        while ((l != null && r != null) || (!stackL.isEmpty() && !stackR.isEmpty())) { //当lr都不为空,或者说l栈,r栈都右元素时才进行循环
            if (l != null && r != null) {
                if (l.val != r.val) return false;
                stackL.push(l);
                stackR.push(r);
                l = l.left;
                r = r.right;
            } else {
                if (l != null || r != null) return false; //判断其中有一个为null,另一个不为null的情况
                //否则说明都为null,开始回退
                l = stackL.pop();
                l = l.right;
                r = stackR.pop();
                r = r.left;
            }
        }
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return true;
    }

    class Stack extends ArrayList<TreeNode> {
        public TreeNode pop() {
            return remove(size() - 1);
        }

        public boolean push(TreeNode p) {
            return add(p);
        }

        public TreeNode peak() {
            return get(size() - 1);
        }
    }
}
