package com.woldier.datastruacture.ch2.d05_stack;

/**
 * description 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/28 下午1:37
 */
public class E01Leetcode20 {
    /**
     * description TODO
     *
     * @param s 待匹配的字符串
     * @return 如果有不成对的括号返回false, 否则返回true
     * @author: woldier
     * @date: 2023/6/28 下午1:45
     */
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '{':
                    stack.push('}');
                    break;
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '}':
                case ']':
                case ')':
                    Character cc = stack.peek() ;
                    if (cc!=null&&cc == c)
                        stack.pop();
                    else
                        return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}
