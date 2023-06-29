package com.woldier.datastruacture.ch2.d04_queue;

/**
 * description 优先级队列中的元素需要实现此接口,返回优先级大小
 *
 * @author: woldier
 * @date: 2023/6/29 上午10:49
 */
public interface Priority {
    /**
     * description 返回优先级的大小,值越大说明优先级越高
     *
     * @return 返回优先级的大小, 值越大说明优先级越高
     * @author: woldier
     * @date: 2023/6/29 上午10:50
     */
    int priority();
}
