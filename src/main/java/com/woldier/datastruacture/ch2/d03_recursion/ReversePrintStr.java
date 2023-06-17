package com.woldier.datastruacture.ch2.d03_recursion;


public class ReversePrintStr {
    public static void print(String str){
        System.out.print(str.charAt(str.length() - 1));
        if (str.length()!=1)
            print(str.substring(0,str.length()-1));
    }
}
