package com.woldier.datastruacture.ch2.d10_tree.bst;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BSTTree1 {
    BSTNode root;

    private static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * description 根据key获取value
     *
     * @param key 键值
     * @return 返回根据key获取到的元素
     * @author: woldier
     * @date: 2023/7/6 下午4:03
     */
    public Object get(int key) {
        return doGet(root, key);
    }

    public Object getWithRecursion(int key) {
        return doGetRecursion(root, key);
    }

    /**
     * description 根据值获取key
     *
     * @param root 根
     * @param key  索引
     * @return
     * @author: woldier
     * @date: 2023/7/6 下午4:07
     */
    private Object doGet(BSTNode root, int key) {
        BSTNode p = root;
        while (p != null && p.key != key) {
            if (key < p.key) //p的值较小,那么往左边走
                p = p.left;
            else
                p = p.right;

        }
        return p == null ? null : p.value;
    }

    /**
    *
    * description 根据值获取key 递归实现
    *
     * @param p 根
     * @param key  索引
    * @author: woldier
    * @date: 2023/7/6 下午4:38
    */
    private Object doGetRecursion(BSTNode p, int key) {
        if (p == null) return null;
        if (p.key == key) return p.value;
        return p.key > key ? doGetRecursion(p.left, key) : doGetRecursion(p.right, key);
    }

    public Object min() {
        return null;
    }

    public Object max() {
        return null;
    }

    public void put(int key, Object value) {

    }

    public Object successor(int key) {
        return null;
    }

    public Object predecessor(int key) {
        return null;
    }

    public Object delete(int key) {
        return null;
    }

    @Test
    public void test() {
        /*
         *
         *          4
         *       /     \
         *      2       6
         *     / \     / \
         *    1   3   5   7
         * */

        BSTNode n1 = new BSTNode(1, "张无忌");
        BSTNode n3 = new BSTNode(3, "宋青书");
        BSTNode n2 = new BSTNode(2, "周芷若", n1, n3);


        BSTNode n5 = new BSTNode(5, "说不得");
        BSTNode n7 = new BSTNode(7, "殷离");
        BSTNode n6 = new BSTNode(6, "赵敏", n5, n7);
        BSTNode root = new BSTNode(4, "小昭", n2, n6);

        BSTTree1 tree1 = new BSTTree1();
        tree1.root = root;
        Assertions.assertSame(n1.value, tree1.get(1));
        Assertions.assertSame(n1.value, tree1.getWithRecursion(1));




    }
}
