package com.woldier.datastruacture.ch2.d04_queue.test;

import com.woldier.datastruacture.ch2.d04_queue.ArrayQueue;
import com.woldier.datastruacture.ch2.d04_queue.ArrayQueue3;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class Test4ArrayQueue3 {
    @Test
    public void test1(){
//        int value = Integer.MAX_VALUE;
//        value +=5;
//        System.out.println(value% 8);
//
//        System.out.println( (value-0b1000000000000000) %8 );

//        System.out.println( value<< >>%8);
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(8);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        Assertions.assertIterableEquals(Arrays.asList(1,2,3,4,4,4,4,4),queue);
        queue.poll();
        Assertions.assertIterableEquals(Arrays.asList(2,3,4,4,4,4,4),queue);
    }
}
