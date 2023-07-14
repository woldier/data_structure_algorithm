package com.woldier.datastruacture.ch2.d10_tree.tree;

import java.util.LinkedList;

/**
 * description 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/5 下午4:01
 */
public class E06Leetcode111<E> {
    public int minDepth(TreeNode<E> root) {

        return 0;
    }

    /**
     * description 通过递归的方式实现
     *
     * @param p
     * @return
     * @author: woldier
     * @date: 2023/7/5 下午4:03
     */
    private int minDepth1(TreeNode<E> p) {
        if (p == null) return 0;//如果为null 说明深度为
        int l = minDepth1(p.left);
        int r = minDepth1(p.right);
        if (l == 0) return r + 1;
        if (r == 0) return l + 1;
        return Integer.min(l, r) + 1;
    }

    /**
     * description 通过层序遍历的方法找到的 第一个个叶子节点即是最小深度
     *
     * @param p
     * @return
     * @author: woldier
     * @date: 2023/7/5 下午4:11
     */
    private int minDepth2(TreeNode<E> p) {
        Queue q1 = new Queue();
        Queue q2 = new Queue();

        int deep = 0;
        q2.push(p);
        while(!q1.isEmpty()||!q2.isEmpty()){
            if(q1.isEmpty()) {
                Queue temp = q1;
                q1 = q2;
                q2 = temp;
                deep++;
            }
            TreeNode<E> pop = q1.pop();
            if (pop.left==null&&p.right==null) return deep;
            if(pop.left!=null) q2.push(pop.left);
            if(pop.right!=null) q2.push(pop.right);
        }
        return deep;
    }

    private class Queue extends LinkedList<TreeNode<E>>{
        public void push(TreeNode<E>  p){
             addLast(p);
        }
        public TreeNode<E> pop(){
            return removeFirst();
        }
        public TreeNode<E> peek(){
            return getFirst();
        }
    }

}
