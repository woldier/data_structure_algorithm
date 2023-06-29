package com.woldier.datastruacture.ch2.d06_dequeue.test;

import com.woldier.datastruacture.ch2.d06_dequeue.ArrayDequeue;
import com.woldier.datastruacture.ch2.d06_dequeue.LinkedDeQueue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class Test4DeQueue {
    @Test
    public void test(){
        LinkedDeQueue<Integer> queue = new LinkedDeQueue(8);
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerFirst(3);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1),queue);
        queue.offerLast(4);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1,4),queue);
        queue.pollFirst();
        Assertions.assertIterableEquals(Arrays.asList(2,1,4),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(2,1),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(2),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(),queue);
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerFirst(3);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1),queue);

    }

    @Test
    public void test2(){
        ArrayDequeue<Integer> queue = new ArrayDequeue(8);
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerFirst(3);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1),queue);
        queue.offerLast(4);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1,4),queue);
        queue.pollFirst();
        Assertions.assertIterableEquals(Arrays.asList(2,1,4),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(2,1),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(2),queue);
        queue.pollLast();
        Assertions.assertIterableEquals(Arrays.asList(),queue);
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerFirst(3);
        Assertions.assertIterableEquals(Arrays.asList(3,2,1),queue);

    }
}
