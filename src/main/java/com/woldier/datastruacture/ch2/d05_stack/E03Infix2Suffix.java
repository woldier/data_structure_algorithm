package com.woldier.datastruacture.ch2.d05_stack;

import java.util.ArrayList;
import java.util.List;

/**
 * description 中缀表达式转后缀表达式
 *
 * @author: woldier
 * @date: 2023/6/28 下午4:30
 */
public class E03Infix2Suffix {
    /**
     * description
     * <p>
     * 算法思想是 扫描中缀表达式,.
     * <p>
     * 如果是数字,则加入到后缀字符串中,
     * <p>
     * 如果是运算符,那么比较当前运算符与栈顶元素的优先级如果当前运算符优先级高,那么将其入栈;否则弹出栈顶元素加入到后缀表达式,并比较下一元素直到入栈
     * <p>
     * 遍历完成后将栈中所有元素依次出栈
     *
     * @param s 中缀表达式
     * @return 后缀表达式
     * @author: woldier
     * @date: 2023/6/28 下午4:31
     */
    public String infix2Suffix(String s) {
        List<Character> stuck = new ArrayList<>();
        StringBuilder builder = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    while (true) {
                        if (stuck.isEmpty() || priority(c, stuck.get(stuck.size() - 1)))
                            break; //如果当前栈为空,或者说当前运算符的优先级较高,那么其入栈,不需要再从栈中弹出
                        builder.append(stuck.remove(stuck.size() - 1));
                    }
                    stuck.add(c);
                }
                case '(' -> {  //括号直接入栈
                    stuck.add(')');
                }
                case ')' -> { //从栈中弹出运算符 直到弹出) 为止
                    char cc;
                    while ((cc = stuck.remove(stuck.size() - 1)) != c)
                        builder.append(cc);
                    //
                }
                default -> {
                    builder.append(c);
                }
            }
        }
        while (!stuck.isEmpty())
            builder.append(stuck.remove(stuck.size() - 1));
        return builder.toString();
    }

    private boolean priority(char c1, char c2) {
        return (getPriority(c1) - getPriority(c2)) > 0;
    }

    private int getPriority(char c) {
        switch (c) {
            case ')' -> {
                return 0;
            }
            case '*', '/' -> {
                return 2;
            }
            case '+', '-' -> {
                return 1;
            }
            default -> {
                throw new RuntimeException("不受支持的运算符");
            }
        }
    }
}
