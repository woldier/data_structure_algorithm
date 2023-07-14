package com.woldier.datastruacture.ch2.d10_tree.avl;
/**
*
* description 平衡二叉树
*
* @author: woldier
* @date: 2023/7/14 下午2:31
*/
public class AVLTree {
    /**
     * 平衡二叉树节点类
     */
    private class AVLNode{
        int key;
        int val;
        AVLNode left;  //左子树
        AVLNode right; //右子树
        int height = 1;  //高度,创建时都为1

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public AVLNode(int key, int val, AVLNode left, AVLNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
