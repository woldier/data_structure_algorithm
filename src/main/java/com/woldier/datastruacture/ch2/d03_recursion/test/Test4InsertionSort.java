package com.woldier.datastruacture.ch2.d03_recursion.test;

import com.woldier.datastruacture.ch2.d03_recursion.InsertionSort;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Test4InsertionSort {
    @Test
    public void test(){
        int[] ints = {2, 3, 6, 1, 4};
        InsertionSort.sort(ints);
        Assertions.assertArrayEquals(new int[]{1,2,3,4,6},ints);

    }

}
