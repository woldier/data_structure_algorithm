package com.woldier.datastruacture.ch2.d05_stack;

import java.util.Iterator;

/**
 * description 数组栈实现
 *
 * @author: woldier
 * @date: 2023/6/28 下午1:08
 */
public class ArrayStack<E>
        implements Stack<E>, Iterable<E> {

    /**
     * description 数组的栈实现
     * <pre>
     *     {@code
     *              p
     *              ↓
     * index  0 1 2 3 4 5 6 7
     * value  2 4 8 n n n n n
     *
     * 栈满
     *                         p
     *                         ↓
     * index  0 1 2 3 4 5 6 7
     * value  2 4 8 6 5 7 3 3
     *
     *     }
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/28 下午1:12
     */
    private final E[] array; //存储用的数组
    private int p; //栈顶指针

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }
    @SuppressWarnings("all")
    public ArrayStack() {
        array = (E[]) new Object[8];
    }

    /**
     * description 元素入栈
     *
     * @param value 入栈元素
     * @return 成功返回true 失败返回false
     * @author: woldier
     * @date: 2023/6/28 下午12:35
     */
    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[p] = value;
        p++;
        return true;
    }

    /**
     * description 从栈中弹出元素
     *
     * @return 如果有元素返回该元素, 如果栈为空返回null
     * @author: woldier
     * @date: 2023/6/28 下午12:35
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return array[--p];
    }

    /**
     * description 返回栈顶元素,不弹出
     *
     * @return 如果有元素返回该元素, 如果栈为空返回null
     * @author: woldier
     * @date: 2023/6/28 下午12:36
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[p-1];
    }

    /**
     * description 判断栈空
     *
     * @return 如果栈空返回true 非空返回false
     * @author: woldier
     * @date: 2023/6/28 下午12:37
     */
    @Override
    public boolean isEmpty() {
        return p==0;
    }

    /**
     * description 判断栈满
     *
     * @return 如果栈空返回false 非空返回true
     * @author: woldier
     * @date: 2023/6/28 下午12:38
     */
    @Override
    public boolean isFull() {
        return p==array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = p-1;
            @Override
            public boolean hasNext() {
                return count+1>0;
            }

            @Override
            public E next() {
                return array[count--];
            }
        };
    }
}
