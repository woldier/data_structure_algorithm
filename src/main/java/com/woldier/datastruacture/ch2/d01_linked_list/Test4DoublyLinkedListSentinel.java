package com.woldier.datastruacture.ch2.d01_linked_list;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/6/14 16:26
 **/
public class Test4DoublyLinkedListSentinel {

    @Test
    public void test() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);

        Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 6), list);
        list.add(6, 7);
        Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), list);
        list.removeLast();
        Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 6), list);
        list.remove(5);
        Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), list);

        Assertions.assertThrows(IllegalArgumentException.class, () -> list.remove(5));
        list.removeFirst();
        Assertions.assertIterableEquals(Arrays.asList(2, 3, 4, 5), list);

    }
}
