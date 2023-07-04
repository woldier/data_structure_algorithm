package com.woldier.datastruacture.ch2.d10_tree;

import java.util.ArrayList;
import java.util.List;

public class E01Leetcode144 {
    private List<Integer> preOrder(TreeNode p){
        ArrayList<TreeNode> stack = new ArrayList();
        List<Integer> res = new ArrayList();
        while(p!=null||!stack.isEmpty()){
            if(p!=null){
                stack.add(p); //入栈
                p = p.left; // 遍历左子树
            }
            else{
                TreeNode pop = stack.remove(stack.size()-1);
                res.add(pop.val);
                p = pop.right;
            }
        }
        return res;
    }



      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

}
