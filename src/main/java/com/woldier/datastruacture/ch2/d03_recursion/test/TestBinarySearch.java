package com.woldier.datastruacture.ch2.d03_recursion.test;

import com.woldier.datastruacture.ch2.d03_recursion.BinarySearch;
import org.junit.Test;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/6/17 10:51
 **/
public class TestBinarySearch {
    @Test
    public void test(){
        System.out.println(BinarySearch.binarySearch(new int[]{1, 2, 3, 4}, 5, 0, 3));
    }
}
