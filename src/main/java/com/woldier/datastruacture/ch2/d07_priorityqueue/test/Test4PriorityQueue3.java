package com.woldier.datastruacture.ch2.d07_priorityqueue.test;

import com.woldier.datastruacture.ch2.d07_priorityqueue.PriorityElem;
import com.woldier.datastruacture.ch2.d07_priorityqueue.test.PriorityQueue3;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Test4PriorityQueue3 {
    @Test
    public void test(){

        PriorityQueue3<PriorityElem<Integer>> priorityQueue1 = new PriorityQueue3<>(8);
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
//        Assertions.assertIterableEquals(Arrays.asList(elem1,elem2,elem3,elem4,elem5,elem6),priorityQueue1);
        Assertions.assertSame(elem6,priorityQueue1.peek());
        Assertions.assertSame(elem6,priorityQueue1.poll());
        Assertions.assertSame(elem1,priorityQueue1.poll());

        priorityQueue1.offer(elem6);
        Assertions.assertSame(elem6,priorityQueue1.poll());

    }
}
