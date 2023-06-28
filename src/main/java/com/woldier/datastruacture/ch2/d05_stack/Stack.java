package com.woldier.datastruacture.ch2.d05_stack;

/**
 * description 栈的接口 定义了一些通用方法
 *
 * @author: woldier
 * @date: 2023/6/28 下午12:31
 */
public interface Stack<E>  {
    /**
     * description 元素入栈
     *
     * @param value 入栈元素
     * @return 成功返回true 失败返回false
     * @author: woldier
     * @date: 2023/6/28 下午12:35
     */
    boolean push(E value);

    /**
     * description 从栈中弹出元素
     *
     * @return 如果有元素返回该元素, 如果栈为空返回null
     * @author: woldier
     * @date: 2023/6/28 下午12:35
     */
    E pop();

    /**
     * description 返回栈顶元素,不弹出
     *
     * @return 如果有元素返回该元素, 如果栈为空返回null
     * @author: woldier
     * @date: 2023/6/28 下午12:36
     */
    E peek();

    /**
     * description 判断栈空
     *
     * @return 如果栈空返回true 非空返回false
     * @author: woldier
     * @date: 2023/6/28 下午12:37
     */
    boolean isEmpty();

    /**
     * description 判断栈满
     *
     * @return 如果栈空返回false 非空返回true
     * @author: woldier
     * @date: 2023/6/28 下午12:38
     */
    boolean isFull();
}
