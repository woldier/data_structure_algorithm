package com.woldier.datastruacture.ch2.d10_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/5 下午2:50
 */
public class E05Leetcode104 {
    public class TreeNode {
        int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int maxDepth(TreeNode root) {
            return 0;
        }


        /**
         * description 递归实现
         *
         * @param root 根节点
         * @return
         * @author: woldier
         * @date: 2023/7/5 下午2:51
         */
        private int maxDepth1(TreeNode root) {
            if (root == null) return 0;  //为空
            if (root.left == null && root.right == null) return 1;  // 为叶子节点
            int l = maxDepth1(root.left);
            int r = maxDepth1(root.right);
            return Integer.max(l, r) + 1;
        }

        /**
         * description 查看深度,非递归实现
         *
         * @param p
         * @return
         * @author: woldier
         * @date: 2023/7/5 下午3:05
         */

        private int maxDepth2(TreeNode p) {
            int size = 0;
            Stack stack = new Stack();
            TreeNode old = null;
            while (p != null || !stack.isEmpty()) {
                if (p != null) { //当指针不为null 说明遍历左子树
                    stack.push(p);
                    if (size < stack.size()) size = stack.size();
                    p = p.left;
                } else {
                    //p为null 说明到了叶子节点
                    TreeNode peek = stack.peek();
                    if (peek.right == null || peek.right == old) {
                        old = stack.pop();
                    } else {
                        p = peek.right;
                    }
                }
            }

            return size;
        }

        private class Stack extends ArrayList<TreeNode> {

            public boolean push(TreeNode p) {
                return add(p);
            }

            public TreeNode pop() {
                return remove(size() - 1);
            }

            public TreeNode peek() {
                return get(size() - 1);
            }
        }
    }
}