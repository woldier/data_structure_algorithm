package com.woldier.datastruacture.ch2.d10_tree.test;

import com.woldier.datastruacture.ch2.d10_tree.tree.TreeNode;
import com.woldier.datastruacture.ch2.d10_tree.tree.TreeTraversal;
import org.junit.jupiter.api.Test;

public class Test4TreeTraversal {
    @Test
    public void test() {


        TreeNode<Integer> p6 = new TreeNode<>(6);
        TreeNode<Integer> p7 = new TreeNode<>(7);
        TreeNode<Integer> p3 = new TreeNode<>(p6, 3, p7);

        TreeNode<Integer> p4 = new TreeNode<>(4);
        TreeNode<Integer> p5 = new TreeNode<>(5);
        TreeNode<Integer> p2 = new TreeNode<>(p4, 2, p5);

        TreeNode<Integer> p = new TreeNode<>(p2, 1, p3);


//        TreeTraversal.preOrder(p,System.out::print);
//        System.out.println();
//        TreeTraversal.midOrder(p,System.out::print);
//        System.out.println();
//        TreeTraversal.postOrder(p,System.out::print);

//        TreeTraversal.preOrder(p,System.out::print,false);
//        TreeTraversal.midOrder(p,System.out::print,false);
        TreeTraversal.postOrder(p,System.out::print,false);
    }

}
