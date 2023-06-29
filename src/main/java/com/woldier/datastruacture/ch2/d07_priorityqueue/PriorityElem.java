package com.woldier.datastruacture.ch2.d07_priorityqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PriorityElem<E> implements Priority{
    private E e;
    private int priority;
    /**
     * description 返回优先级的大小,值越大说明优先级越高
     *
     * @return 返回优先级的大小, 值越大说明优先级越高
     * @author: woldier
     * @date: 2023/6/29 上午10:50
     */
    @Override
    public int priority() {
        return priority;
    }
}
