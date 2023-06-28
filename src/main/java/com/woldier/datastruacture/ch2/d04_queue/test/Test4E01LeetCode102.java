package com.woldier.datastruacture.ch2.d04_queue.test;

import com.woldier.datastruacture.ch2.d04_queue.E01LeetCode102;
import com.woldier.datastruacture.ch2.d04_queue.TreeNode;
import org.junit.Test;

/**
*
* description 测试
*
* @author: woldier
* @date: 2023/6/27 下午8:57
*/
public class Test4E01LeetCode102 {
    @Test
    public void test() {
        TreeNode p15 = new TreeNode(15);
        TreeNode p7 = new TreeNode(7);
        TreeNode p9 = new TreeNode(9);
        TreeNode p20 = new TreeNode(20, p15, p7);
        TreeNode p = new TreeNode(3, p9, p20);
    }

}
