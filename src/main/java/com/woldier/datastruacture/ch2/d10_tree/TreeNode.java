package com.woldier.datastruacture.ch2.d10_tree;
/**
*
* description 二叉树节点
*
* @author: woldier
* @date: 2023/6/30 上午11:07
*/
public class TreeNode<E> {
    public TreeNode<E> left;
    public E value;
    public TreeNode<E> right;

    public TreeNode() {
    }

    public TreeNode(E value) {
        this.value = value;
    }

    public TreeNode(TreeNode<E> left, E value, TreeNode<E> right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
