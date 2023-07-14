package com.woldier.datastruacture.ch2.d10_tree.test;

import com.woldier.datastruacture.ch2.d10_tree.tree.E04Leetcode101;
import org.junit.jupiter.api.Test;

public class Test4E04Leetcode101 {
    @Test
    public void test(){

        E04Leetcode101.TreeNode p6 = new E04Leetcode101.TreeNode(Integer.MIN_VALUE);
        E04Leetcode101.TreeNode p7 = new E04Leetcode101.TreeNode(4);
        E04Leetcode101.TreeNode p3 = new E04Leetcode101.TreeNode(null, 3, p7);

        E04Leetcode101.TreeNode p4 = new E04Leetcode101.TreeNode(4);
        E04Leetcode101.TreeNode p5 = new E04Leetcode101.TreeNode(5);
        E04Leetcode101.TreeNode p2 = new E04Leetcode101.TreeNode(p4, 3, p5);

        E04Leetcode101.TreeNode p = new E04Leetcode101.TreeNode(p2, 2, p3);
        E04Leetcode101 s = new E04Leetcode101();
        s.isSymmetricStack(p.left,p.right);
        
    }
    
}
