package com.woldier.datastruacture.ch2.d10_tree.tree;

import java.util.ArrayList;

public class E08ExpressionTree {

    /**
     * description 将后缀表达式转为书存储
     * <p>
     * <p>
     * <p>
     * 中缀 (1-2)*3
     * 后缀 12-3*
     * <p>
     * 建树
     *
     * <pre>
     *     {@code
     *      *
     *   -     3
     *  1  3
     *     }
     * </pre>
     * <p>
     * 算法思想是,用栈保存数字,当遇到符号的时候,从栈中弹出两个元素然后以运算符为root 建树,然后将该运算符放入栈中
     * <p>
     * |  1   2     | 入栈 遇到-号,建树
     * <pre>
     *     {@code
     *    -
     *  1  2
     *     }
     * </pre>
     * |  -   3     | 入栈 遇到*号,出栈,建树
     *
     * <pre>
     *     {@code
     *      *
     *    -   3
     *  1  2
     *     }
     * </pre>
     *
     * @param str 输入的序列
     * @return
     * @author: woldier
     * @date: 2023/7/5 下午7:42
     */
    public TreeNode<String> expressionTree(String[] str) {
        Stack stack = new Stack();
        for (String s : str) {
            switch (s) {
                case "+", "-", "*", "/" -> {
                    TreeNode<String> r = stack.pop();
                    TreeNode<String> l = stack.pop();
                    TreeNode<String> p = new TreeNode<>(l, s, r);
                    stack.push(p);
                }
                default -> {
                    stack.push(new TreeNode<>(s));
                }
            }
        }
        return stack.peek();
    }


    private class Stack extends ArrayList<TreeNode<String>> {

        public boolean push(TreeNode<String> p) {
            return add(p);
        }

        public TreeNode<String> pop() {
            return remove(size() - 1);
        }

        public TreeNode<String> peek() {
            return get(size() - 1);
        }
    }
}
