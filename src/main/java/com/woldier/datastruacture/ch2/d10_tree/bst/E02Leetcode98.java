package com.woldier.datastruacture.ch2.d10_tree.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * description 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/7/14 下午1:21
 */
public class E02Leetcode98 {
    /**
     * 判断一棵树是否是符合二叉排序树
     * description 算法思想是中序便利,并且记录前一个节点的值,如果值服从从小打到,说明是排序树.
     *
     * @param root
     * @return
     * @author: woldier
     * @date: 2023/7/14 下午1:22
     */
    public boolean isValidBST(TreeNode root) {
        return doCheckBST(root);
    }
   /**
   *
   * description 非递归实现
   *
   * @param p
   * @return
   * @author: woldier
   * @date: 2023/7/14 下午1:26
   */
    public boolean doCheckBST(TreeNode p){
        Stack<TreeNode> stack = new Stack<>();
        int preVal = Integer.MIN_VALUE;
        while(p!=null||stack.size()>0){
            if(p!=null){
                stack.push(p); //p入栈

                p = p.left; //指向p的左孩子
                //if(p!=null&&p.val<preVal) return false; //p的左孩子不为null
            }else {//p 指向了null,从stack中弹出
                TreeNode pop = stack.pop();
                if(preVal>pop.val) return false;//不满足BST
                preVal = pop.val; //记录前驱值
                p = pop.right; //p指向右孩子
            }
        }
        return true;

    }
    class Stack<E> extends ArrayList<E>{
        public boolean push(E e){
            return add(e);
        }
        public E pop(){
            return remove(size()-1);
        }
        public E peek(){
            return get(size()-1);
        }
    }

    @Test
    public void test(){
        E02Leetcode98 s = new E02Leetcode98();
        TreeNode root = new TreeNode(2,new TreeNode(2),new TreeNode(2));
        s.doCheckBST(root);
    }



}
