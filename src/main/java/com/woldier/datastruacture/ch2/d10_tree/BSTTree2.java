package com.woldier.datastruacture.ch2.d10_tree;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BSTTree2<K extends Comparable<K>, V> {
    BSTNode<K, V> root;

    private static class BSTNode<K extends Comparable<K>, V> {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
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
    public Object get(K key) {
        return doGet(root, key);
    }

    public Object getWithRecursion(K key) {
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
    private V doGet(BSTNode root, K key) {
        BSTNode p = root;
        while (p != null && p.key != key) {
            if (p.key.compareTo(key) > 0) //p的值较小,那么往左边走
                p = p.left;
            else
                p = p.right;

        }
        return p == null ? null : (V) p.value;
    }

    /**
     * description 根据值获取key 递归实现
     *
     * @param p   根
     * @param key 索引
     * @author: woldier
     * @date: 2023/7/6 下午4:38
     */
    private V doGetRecursion(BSTNode p, K key) {
        if (p == null) return null;
        if (p.key == key) return (V) p.value;
        return (V) (p.key.compareTo(key) > 0 ? doGetRecursion(p.left, key) : doGetRecursion(p.right, key));
    }

    /**
     * description 获取最小值
     *
     * @return 返回最小值
     * @author: woldier
     * @date: 2023/7/6 下午6:14
     */
    public V min() {
        return getMin(root);
    }

    private V getMin(BSTNode<K, V> root) {
        BSTNode<K, V> p = root;
        while (p != null && p.left != null)
            p = p.left;
        return p.value;
    }

    public V max() {
        return getMax(root);
    }

    private V getMax(BSTNode<K, V> root) {
        BSTNode<K, V> p = root;
        while (p != null && p.right != null) p = p.right;
        return p.value;
    }

    /**
     * description 放入元素
     *
     * @param key   键
     * @param value 值
     * @return
     * @author: woldier
     * @date: 2023/7/6 下午6:44
     */
    public void put(K key, V value) {
        BSTNode<K, V> p = root, parent = root;
        while (p != null) {
            parent = p;
            if (p.key.compareTo(key) > 0) //p的值大,往左走
                p = p.left;
            else if (p.key.compareTo(key) < 0)
                p = p.right;
            else { //key相等,那么就就替换当前节点的value
                p.value = value;
                return;
            }
        }
        BSTNode<K, V> node = new BSTNode<>(key, value);
        if (parent == null) root = node;
        else {
            if (parent.key.compareTo(key) > 0)
                parent.left = node;
            else
                parent.right = node;
        }

    }

    /**
     * description 分两种情况,1.如果该节点有左孩子,那么前驱来自左子树的最大值 2.如果没有左孩子,且为祖先的右孩子,那么祖先是前驱, 其余情况为没有前驱
     *
     * @param key 键
     * @return 返回对对应的前驱的值
     * @author: woldier
     * @date: 2023/7/6 下午6:58
     */

    public V predecessor(K key) {
        BSTNode<K, V> p = root, parent = root;
        while (p != null) {
            parent = p;
            if (p.key.compareTo(key) > 0) //p的值大,往左走
                p = p.left;
            else if (p.key.compareTo(key) < 0)
                p = p.right;
            else { //key相等,那么就就替换当前节点的value
                break;
            }
        }
        if (p.left != null) return (V) getMax(p.left); //存在左子树
        if (parent != null && parent.right == p) return parent.value;
        return null;//其他情况表示不存在,返回null
    }


    /**
     * description 分两种情况,1.如果该节点有右孩子,那么后继来自右子树的最小值 2.如果没有右孩子,且为祖先的左孩子,那么祖先是前驱, 其余情况为没有前驱
     *
     * @param key 键值
     * @return
     * @author: woldier
     * @date: 2023/7/6 下午7:27
     */
    public V successor(K key) {
        BSTNode<K, V> p = root, parent = root;
        while (p != null) {
            parent = p;
            if (p.key.compareTo(key) > 0) //p的值大,往左走
                p = p.left;
            else if (p.key.compareTo(key) < 0)
                p = p.right;
            else { //key相等,那么就就替换当前节点的value
                break;
            }
        }
        if (p.right != null) return (V) getMin(p.right); //存在左子树
        if (parent != null && parent.left == p) return parent.value;
        return null;//其他情况表示不存在,返回null
    }

    /**
     * description 要删除某节点（称为 D），必须先找到被删除节点的父节点，这里称为 Parent
     * <p>
     * 删除节点没有左孩子，将右孩子托孤给 Parent
     * 删除节点没有右孩子，将左孩子托孤给 Parent
     * 删除节点左右孩子都没有，已经被涵盖在情况1、情况2 当中，把 null 托孤给 Parent
     * 删除节点左右孩子都有，可以将它的后继节点（称为 S）托孤给 Parent，设 S 的父亲为 SP，又分两种情况
     * SP 就是被删除节点，此时 D 与 S 紧邻，只需将 S 托孤给 Parent
     * SP 不是被删除节点，此时 D 与 S 不相邻，此时需要将 S 的后代托孤给 SP，再将 S 托孤给 Parent
     *
     * @param null
     * @return
     * @author: woldier
     * @date: 2023/7/6 下午7:51
     */
    public V delete(K key) {
        BSTNode<K, V> p = root, parent = null;
        while (p != null) {

            if (p.key.compareTo(key) > 0) { //p的值大,往左走
                parent = p;
                p = p.left;
            } else if (p.key.compareTo(key) < 0) {
                parent = p;
                p = p.right;
            } else { //key相等,那么就就替换当前节点的value
                break;
            }
        }
        if (parent == null) {
            root.right = p.right;
            root.left = p.left;

        }
        else if (p.left != null && p.right == null) { //情况一
            shiftSingleLR(parent,p,true);

        }
        else if (p.right != null && p.left == null) { //情况2
            shiftSingleLR(parent,p,false);
        }
        else if (p.right == null && p.left == null) {//情况3
            parent = null;
        } else { //情况4 左右孩子都有

        }
        return p.value;
    }

    private void shiftSingleLR(BSTNode<K, V> parent, BSTNode<K, V> p, boolean isL) {
        if (p == parent.left) parent.left = isL ? p.left : p.right;
        if (p == parent.right) parent.right = isL ? p.left : p.right;
    }

    private void shift(BSTNode<K, V> parent, BSTNode<K, V> p){


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

        BSTTree2<Integer, String> tree1 = new BSTTree2();
        tree1.root = root;
        Assertions.assertSame(n1.value, tree1.get(1));
        Assertions.assertSame(n1.value, tree1.getWithRecursion(1));

        Assertions.assertSame(n1.value, tree1.min());
        Assertions.assertSame(n7.value, tree1.max());


        Assertions.assertSame(n1.value, tree1.predecessor(2));
        Assertions.assertSame(n3.value, tree1.predecessor(4));


        Assertions.assertSame(n3.value, tree1.successor(2));
        Assertions.assertSame(n5.value, tree1.successor(4));


    }
}
