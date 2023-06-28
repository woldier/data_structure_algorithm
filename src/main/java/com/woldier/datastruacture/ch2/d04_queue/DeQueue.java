package com.woldier.datastruacture.ch2.d04_queue;

/**
 * description 双端队列
 *
 * @author: woldier
 * @date: 2023/6/28 下午7:37
 */
public interface DeQueue<E> {
    /**
     * description 放入头部
     *
     * @param e 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    boolean offerFirst(E e);

    /**
     * description 尾部
     *
     * @param e 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    boolean offerLast(E e);

    /**
     * description 获取队头元素
     *
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    E pollFirst();

    /**
     * description 获取队尾元素
     *
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    E pollLast();

    /**
     * description 获取队头元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    E peekFirst();

    /**
     * description 获取队尾元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    E peekLast();

    /**
     * description 判断是否时队空
     *
     * @return 如果队列尾空返回true, 不为空返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:43
     */
    boolean isEmpty();

    /**
     * description 判断是否时队满
     *
     * @return 如果队列尾空返回 false,不为空返回true
     * @author: woldier
     * @date: 2023/6/28 下午7:43
     */
    boolean isFull();

}
