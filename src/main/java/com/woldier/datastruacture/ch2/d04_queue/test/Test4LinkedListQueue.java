package com.woldier.datastruacture.ch2.d04_queue.test;

import com.woldier.datastruacture.ch2.d04_queue.LinkedListQueue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
*
* description 链队测试
*
* @author: woldier
* @date: 2023/6/26 下午10:30
*/
public class Test4LinkedListQueue {


    @Test
    public void test1(){
        LinkedListQueue queue = new LinkedListQueue();
        queue.forEach(System.out::println);
    }

    @Test
    public void test2(){
        LinkedListQueue queue = new LinkedListQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Assertions.assertIterableEquals(Arrays.asList(1,2,3,4,5),queue);
        System.out.println(queue.peek());
        Assertions.assertIterableEquals(Arrays.asList(1,2,3,4,5),queue);
        System.out.println(queue.poll());
        Assertions.assertIterableEquals(Arrays.asList(2,3,4,5),queue);
    }

}
