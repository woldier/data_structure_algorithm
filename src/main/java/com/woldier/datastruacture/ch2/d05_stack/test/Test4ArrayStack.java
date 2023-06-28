package com.woldier.datastruacture.ch2.d05_stack.test;

import com.woldier.datastruacture.ch2.d05_stack.ArrayStack;
import com.woldier.datastruacture.ch2.d05_stack.LinkedStack;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class Test4ArrayStack {
    @Test
    public void test1(){
        ArrayStack<Integer> stack = new ArrayStack<>();
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
