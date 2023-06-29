package com.woldier.datastruacture.ch2.d04_queue.test;

import com.woldier.datastruacture.ch2.d04_queue.PriorityElem;
import com.woldier.datastruacture.ch2.d04_queue.PriorityQueue1;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class Test4PriorityQueue2 {
    @Test
    public void test(){

        PriorityQueue1<PriorityElem<Integer>> priorityQueue1 = new PriorityQueue1<>(8);
        PriorityElem<Integer> elem1 = new PriorityElem<>(1, 4);
        PriorityElem<Integer> elem2 = new PriorityElem<>(2, 2);
        PriorityElem<Integer> elem3 = new PriorityElem<>(3, 3);
        PriorityElem<Integer> elem4 = new PriorityElem<>(4, 1);
        PriorityElem<Integer> elem5 = new PriorityElem<>(5, 2);
        PriorityElem<Integer> elem6 = new PriorityElem<>(6, 6);
        priorityQueue1.offer(elem1);
        priorityQueue1.offer(elem2);
        priorityQueue1.offer(elem3);
        priorityQueue1.offer(elem4);
        priorityQueue1.offer(elem5);
        priorityQueue1.offer(elem6);
        Assertions.assertIterableEquals(Arrays.asList(elem1,elem2,elem3,elem4,elem5,elem6),priorityQueue1);
        Assertions.assertSame(elem6,priorityQueue1.peek());
        Assertions.assertSame(elem6,priorityQueue1.poll());
        Assertions.assertSame(elem1,priorityQueue1.poll());

        priorityQueue1.offer(elem6);
        Assertions.assertSame(elem6,priorityQueue1.poll());
    }
}
