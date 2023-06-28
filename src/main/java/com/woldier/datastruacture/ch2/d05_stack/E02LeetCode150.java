package com.woldier.datastruacture.ch2.d05_stack;

/**
 * description 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 注意：
 * <p>
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 * <p>
 *
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= tokens.length <= 104
 * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/28 下午2:02
 */
public class E02LeetCode150 {

    /**
     * description 算法思想是如果遍历到的String是数字那么则存入栈中,如果得到的是操作符,那么从栈中弹出两个数组进行运算操作,并且吧结果存入栈中
     * <p>
     * 当遍历完后,栈中的唯一元素就是计算结果
     *
     * @author: woldier
     * @date: 2023/6/28 下午2:05
     */
    public int evalRPN(String[] tokens) {
        ArrayStack<Integer> stack = new ArrayStack<>(10000);
        for (String s : tokens) {
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){ //是运算符
                Integer a = stack.pop();
                Integer b = stack.pop();
                switch (s){
                    case "+":
                        stack.push( b + a );
                        break;
                    case "-":
                        stack.push( b- a );
                        break;
                    case "*":
                        stack.push( b * a );
                        break;
                    case "/":

                        stack.push( b / a);
                        break;
                }
            }
            else {  //不是运算符
                stack.push(Integer.valueOf(s));
            }
        }

        return stack.pop();
    }
}
