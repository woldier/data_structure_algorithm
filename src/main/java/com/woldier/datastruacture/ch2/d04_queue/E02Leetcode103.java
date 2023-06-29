package com.woldier.datastruacture.ch2.d04_queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author: woldier
 * @date: 2023/6/29 上午10:16
 */
public class E02Leetcode103 {
    /**
     * description 算法思想是，通过内嵌循环（循环中完成一层的元素入队）
     * <p>
     * 1
     * 2    3
     * 4   5 6  7
     * 刚开始时 队列中只有 1  内循环循环一次
     * 内循环中将队列中所有元素加入当前list中(根据是奇数行还是偶数行选择是在头部添加还是尾部添加)，并且子节点计数加一 本次内循环完成后，队列中有2，3 子节点计数为2
     * 然后以此时的子节点计数个数作为内循环的执行次数
     *
     * @param root tree的根节点
     * @return 返回各个层级的节点 ，各层级存储在不同的list中
     * @author: woldier
     * @date: 2023/6/28 上午8:39
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> listList = new ArrayList<>();
        if (root == null) return listList;
        int size = 1, count = 1;
        LinkedList<Integer> list;
        ArrayQueue<TreeNode> queue = new ArrayQueue<>(2000);
        queue.offer(root);
        boolean odd = true;
        while (!queue.isEmpty()) {
            int cc = 0;
            list = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    cc++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    cc++;
                }
                if(odd)
                    list.offerLast(node.val);
                else
                    list.offerFirst(node.val);
            }
            count = cc;
            odd = !odd;
            listList.add(list);
        }

        return listList;
    }
}
