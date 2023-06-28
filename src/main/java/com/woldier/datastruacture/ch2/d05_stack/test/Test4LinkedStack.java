package com.woldier.datastruacture.ch2.d05_stack.test;

import com.woldier.datastruacture.ch2.d05_stack.LinkedStack;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
*
* description 测试LinkedStack
*
* @author: woldier
* @date: 2023/6/28 下午12:53
*/
public class Test4LinkedStack {
    @Test
    public void test1(){
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        Assertions.assertIterableEquals(Arrays.asList(6,5,4,3,2,1) ,stack);
        stack.pop();
        Assertions.assertIterableEquals(Arrays.asList(5,4,3,2,1) ,stack);
        stack.pop();
        stack.pop();
        stack.pop();
        Assertions.assertIterableEquals(Arrays.asList(2,1) ,stack);
    }
}
