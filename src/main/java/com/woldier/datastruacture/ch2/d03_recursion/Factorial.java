package com.woldier.datastruacture.ch2.d03_recursion;

/**
 * 递归
 */
public class Factorial {
    public static int f(int x) {
        return x == 1 ? 1 : x * f(x - 1);
    }
}
