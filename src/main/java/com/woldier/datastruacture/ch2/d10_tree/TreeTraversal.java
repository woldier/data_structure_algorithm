package com.woldier.datastruacture.ch2.d10_tree;

import com.woldier.datastruacture.ch2.d05_stack.ArrayStack;

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
     * description TODO
     *
     * @param root      根节点
     * @param consumer  遍历操作
     * @param recursion 是否采用递归
     * @return
     * @author: woldier
     * @date: 2023/7/4 下午3:43
     */
    public static <E> void preOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer, boolean recursion) {
        if (recursion) preOrderRecursion(root, consumer);
        else preOrderStack(root, consumer);
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
     * description 采用栈实现递归,模拟递归调用,算法思想是先把根节点入栈
     * <p>
     * 然后当栈非空的情况下,栈顶元素出栈,执行consumer操作,然后有左孩子则入栈左孩子,有右孩子则 入栈右孩子
     *
     * @param p        书的根节点
     * @param consumer 对节点要进行的操作
     * @author: woldier
     * @date: 2023/7/4 下午3:34
     */
    private static <E> void preOrderStack(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        stack.push(p);
        while ((p = stack.pop()) != null) {
            consumer.accept(p);//遍历

            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        }
    }


    //==================================================================================


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

    public static <E> void midOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer, boolean recursion) {
        if (recursion)
            midOrderRecursion(root, consumer);
        else
            midOderStack(root, consumer);
    }

    private static <E> void midOrderRecursion(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        if (p == null) return;
        midOrderRecursion(p.left, consumer);
        consumer.accept(p);
        midOrderRecursion(p.right, consumer);
    }

    private static <E> void midOderStack(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode<E> pop = stack.pop();

                consumer.accept(pop);
                p = pop.right;

            }
        }


    }
    //==================================================================================

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
    public static <E> void postOrder(TreeNode<E> root, Consumer<TreeNode<E>> consumer,boolean recursion) {
        if(recursion)
            postOrderRecursion(root, consumer);
        else
            postOderStack(root, consumer);
    }
    private static <E> void postOrderRecursion(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        if (p == null) return;
        postOrderRecursion(p.left, consumer);
        postOrderRecursion(p.right, consumer);
        consumer.accept(p);
    }

    private static <E> void postOderStack(TreeNode<E> p, Consumer<TreeNode<E>> consumer) {
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        TreeNode<E> old = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode<E> pop = stack.peek();
                if (pop.right == null||pop.right==old) { //当右子树为空的时候才会进行遍历,否则遍历右子树
                    old = stack.pop();
                    consumer.accept(pop);
                }else{
                    p = pop.right;
                }

            }
        }
    }
}
