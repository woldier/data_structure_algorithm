package com.woldier.datastruacture.ch2.d10_tree.bst;

/**
 * description 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 *  
 * <p>
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/14 下午1:01
 */
public class E01Leetcode450 {
    public TreeNode deleteNode(TreeNode root, int key) {

        return doDelete(root, key);

    }


    /**
     * description 递归删除
     * <pre>
     *     {@code
     *         4
     *       /   \
     *      2     6
     *     / \   / \
     *    1   3 5   7
     *     }
     * </pre>
     *
     * @param node
     * @param key
     * @return 返回删除后的子节点信息
     * @author: woldier
     * @date: 2023/7/14 上午11:05
     */
    public TreeNode doDelete(TreeNode node, int key) {
        if (node == null) return null;
        if (key > node.val) { //key 比node 的值大,那么应该往右边走
            node.right = doDelete(node.right, key);
            return node;
        } else if (key < node.val) { //key 比node 的值小,那么应该往左边走
            node.left = doDelete(node.left, key);
            return node;
        } else { //node 的key与查找的key相同
            //执行删除
            //如果只有左孩子
            if (node.left == null && node.right != null) {
                return node.right;
            }
            //如果只有右孩子
            else if (node.left != null && node.right == null) {
                return node.left;
            }            //如果为叶子节点
            else if (node.left == null) {
                return null;
            } else {            //如果两个孩子都没有
                shift(node);  //重建树
                return node; //返回重建后的node
            }
        }
    }

    /**
     * description 被删除节点左右孩子都有的情况,先找到p的后继s,以及其父亲sp
     * 如果sp就是待删除节点,那么只需要将s托孤给parent
     * 如果sp不是,那么吧s托孤给parent,然后吧s的右孩子托孤给sp
     *
     * @param p
     * @author: woldier
     * @date: 2023/7/14 上午10:33
     */
    private void shift(TreeNode p) {
        //找到后继
        TreeNode s = p.right, sp = p; //初始化后继及后继的父节点
        while (s.left != null) {
            sp = s;
            s = s.left;
        }
        //判断sp与p的关系
        p.val = s.val;
        if (sp == p) { //如果相等,那么只需要吧s替代p
            p.right = s.right;
        } else { //如果不相等,
            sp.left = s.right;
        }
        s.right = null; //help GC
    }

}
