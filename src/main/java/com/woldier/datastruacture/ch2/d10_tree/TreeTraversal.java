package com.woldier.datastruacture.ch2.d10_tree;

import java.util.function.Consumer;

/**
 * description 二叉树遍历
 *
 * @author: woldier
 * @date: 2023/6/30 上午11:10
 */
public class TreeTraversal {
    /**
     * description 树的先序遍历 递归实现
     *
     * @param root     根节点
     * @param consumer 遍历执行的操作
     * @return
     * @author: woldier
     * @date: 2023/6/30 上午11:12
     */
    public static <E> void preOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer) {
        preOrderRecursion(root, consumer);
    }
    public static <E> void preOrder(TreeNode<E> root) {
        preOrderRecursion(root, Object::toString);
    }
    /**
     * description 先序遍历的递归函数
     *
     * @param p        根节点
     * @param consumer 遍历执行的操作
     * @return
     * @author: woldier
     * @date: 2023/6/30 上午11:15
     */
    private static <E> void preOrderRecursion(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        if (p == null) return;
        consumer.accept(p);
        preOrderRecursion(p.left, consumer);
        preOrderRecursion(p.right, consumer);
    }

    /**
     * description 二叉树的中序遍历 递归实现
     *
     * @param root     根节点
     * @param consumer 遍历执行的操作
     * @return
     * @author: woldier
     * @date: 2023/6/30 上午11:12
     */
    public static <E> void midOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer) {
        midOrderRecursion(root, consumer);
    }
    private static <E> void midOrderRecursion(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        if (p == null) return;
        midOrderRecursion(p.left, consumer);
        consumer.accept(p);
        midOrderRecursion(p.right, consumer);
    }
    /**
     * description 二叉树的后序遍历 递归实现
     *
     * @param root     根节点
     * @param consumer 遍历执行的操作
     * @return
     * @author: woldier
     * @date: 2023/6/30 上午11:12
     */
    public static <E> void postOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer) {
        postOrderRecursion(root, consumer);
    }
    private static <E> void postOrderRecursion(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        if (p == null) return;
        postOrderRecursion(p.left, consumer);
        postOrderRecursion(p.right, consumer);
        consumer.accept(p);
    }

}
