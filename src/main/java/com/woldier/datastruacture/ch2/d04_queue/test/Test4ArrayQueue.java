package com.woldier.datastruacture.ch2.d04_queue.test;

import com.woldier.datastruacture.ch2.d04_queue.ArrayQueue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class Test4ArrayQueue {
    @Test
    public void test1(){
        ArrayQueue<Integer> queue = new ArrayQueue<>(5,()->new Integer[5]);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        Assertions.assertIterableEquals(Arrays.asList(1,2,3,4),queue);
    }
}
