package com.woldier.datastruacture.ch2.d01_linked_list;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author woldier
 * @version 1.0
 * @description 测类
 * @date 2023/6/14 15:11
 **/
public class Test4SingleLinkedListSentinel {
    @Test
    public void test() {
        SingleLinkedListSentinel list = new SingleLinkedListSentinel();

        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        Assertions.assertIterableEquals(Arrays.asList(3, 2, 1, 0), list);

        Assertions.assertThrows(IllegalArgumentException.class, () -> list.insert(5, 8));

        list.insert(0, 0);
        Assertions.assertIterableEquals(Arrays.asList(0, 3, 2, 1, 0), list);
        list.remove(4);
        Assertions.assertIterableEquals(Arrays.asList(0, 3, 2, 1), list);
        list.remove(0);
        Assertions.assertIterableEquals(Arrays.asList(3, 2, 1), list);

    }
}
