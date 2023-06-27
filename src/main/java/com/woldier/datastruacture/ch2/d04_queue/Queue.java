package com.woldier.datastruacture.ch2.d04_queue;


/**
*
* description 队列接口,定义了一系列常规方法,所有Queue的实现应该实现这些方法
*
* @author: woldier
* @date: 2023/6/26 下午9:08
*/
public interface Queue<E> {

    /**
    *
    * description 向队列尾插入值
    *
    * @param e 待插入的值
    * @return  插入成功返回true,插入失败返回false
    * @author: woldier
    * @date: 2023/6/26 下午9:07
    */
    boolean offer(E e);


    /**
    *
    * description 从队头获取值并移除
    *
    * @return  如果队列非空返回对头元素,否则返回null
    * @author: woldier
    * @date: 2023/6/26 下午9:10
    */
    E poll();


    /**
     *
     * description 从队头获取值不移除
     *
     * @return  如果队列非空返回对头元素,否则返回null
     * @author: woldier
     * @date: 2023/6/26 下午9:10
     */
    E peek();

    /**
    *
    * description 判断是否队空
    * @return 空返回true,否则返回null
    * @author: woldier
    * @date: 2023/6/27 下午1:50
    */
    boolean isEmpty();
}
