package com.woldier.datastruacture.ch2.d03_recursion.test;

import com.woldier.datastruacture.ch2.d03_recursion.BubbleSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author woldier
 * @version 1.0
 * @description 测试递归冒泡排序
 * @date 2023/6/17 14:13
 **/
public class TestBubbleSort {
    @Test
    public void test() {
        int[] a = {2, 1, 0, 5};
        BubbleSort.bubbleSort1(a, 3);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i]);
    }


    @Test
    public void test2() {
        int[] a = {1, 2, 3, 5};
        BubbleSort.bubbleSort2(a, 3);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i]);
    }

}
