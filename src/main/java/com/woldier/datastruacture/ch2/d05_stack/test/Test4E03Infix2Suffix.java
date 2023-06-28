package com.woldier.datastruacture.ch2.d05_stack.test;

import com.woldier.datastruacture.ch2.d05_stack.E03Infix2Suffix;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Test4E03Infix2Suffix {
    @Test
    public void test1(){
        E03Infix2Suffix m = new E03Infix2Suffix();
        Assertions.assertEquals("ab+c+",m.infix2Suffix("a+b+c"));
        Assertions.assertEquals("abc*+",m.infix2Suffix("a+b*c"));
        Assertions.assertEquals("ab*c+",m.infix2Suffix("a*b+c"));
        Assertions.assertEquals("ab*cd*+",m.infix2Suffix("a*b+c*d"));
        Assertions.assertEquals("ab+c*",m.infix2Suffix("(a+b)*c"));
        Assertions.assertEquals("abc*+df+*",m.infix2Suffix("(a+b*c)*(d+f)"));



    }
}
