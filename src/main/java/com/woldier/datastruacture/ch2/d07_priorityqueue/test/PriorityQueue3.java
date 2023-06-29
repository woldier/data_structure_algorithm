package com.woldier.datastruacture.ch2.d07_priorityqueue.test;

import com.woldier.datastruacture.ch2.d07_priorityqueue.Priority;
import com.woldier.datastruacture.ch2.d04_queue.Queue;

import java.util.Iterator;

/**
 * description 优先级队列的大根堆实现
 * <p>
 * preliminary 堆是完全二叉树,因此插入堆的元素只能是索引最大处
 * 假设索引从0开始,节点i的父节点为 i>>>1 -1  节点的子节点为 i<<1 + 1 和 i<<1 + 2
 *
 * @author: woldier
 * @date: 2023/6/29 下午1:21
 */
public class PriorityQueue3<E extends Priority> implements Queue<E>, Iterable<E> {
    private Priority[] array;
    private int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * description 添加元素
     *
     *
     * <pre>
     *     {@code
     *      0    1   2  3  4  5
     *     100  26  32  7  5  3
     *
     *       100
     *    26    32
     *  7   5  3
     *
     *     现在插入208,插入的位置应该为32的右孩子,但是插入不满足大根堆,因此要交换
     *
     *       100
     *    26    208
     *  7   5  3   32
     *  此时依旧不满足,继续上移
     *
     *       208
     *    26    100
     *  7   5  3   32
     *     到根或者是其父节点满足大根堆属性结束上移
     *     }
     * </pre>
     *
     * @param e 待插入元素
     * @return
     * @author: woldier
     * @date: 2023/6/29 下午1:27
     */
    @Override
    public boolean offer(E e) {
        if (isFull()) return false;
        int p = size++; // 得到待插入位置,并且size 自增
        while (p > 0 && array[(p+1) / 2 - 1].priority() < e.priority()) {
            array[p] = array[(p+1) / 2 - 1];
            p = (p+1) / 2 - 1;
        }
        array[p] = e;
        return false;
    }

    /**
     * description 出队
     *
     * <pre>
     *     {@code
     *      0    1   2  3  4  5 size
     *     100  26  32  7  5  3
     *
     *       100
     *    26    32
     *  7   5  3
     *
     *     现在出队,先让根节点与最底层最右子孩子(数组最后一个元素)交换,然后删除最底层最右子孩子(数组最后一个元素)
     *     之后从根节点开始调整根堆,如果根有孩子,判断较大的孩子,然后判断孩子与根的大小,孩子大则交换(想让与调整根堆满足堆特性)
     *     如果根没有左右孩子,那么则break
     *       3
     *    26    32
     *  7   5  100
     *  ---------------
     *       3
     *    26    32
     *  7   5
     *  ---------------
     *       32
     *    26    3
     *  7   5
     *     }
     * </pre>
     *
     * @return
     * @author: woldier
     * @date: 2023/6/29 下午1:44
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;
        Priority elem = array[0];
        //交换
        array[0] = array[size - 1]; //重新幅值根
        array[--size] = null; //size 自减 并help GC
        int p = 0;//自己的指针
        int rightKid;//做孩子的指针
        while (true) { //当存在右孩子继续循环,当右孩子不存在,但是左孩子也存在也继续循环
            rightKid = p * 2 + 2;
            if (size - rightKid > 0) {//存在右孩子
                Priority l = array[rightKid - 1];
                Priority r = array[rightKid];
                if (l.priority() > r.priority()) {//左边大
                    if (array[p].priority() >= l.priority()) break;
                    switchElem(p, rightKid - 1);
                } else {//右边大
                    if (array[p].priority() >= r.priority()) break;
                    switchElem(p, rightKid);
                }
            } else if (size - rightKid - 1 > 0) {//存在左孩子{}
                if (array[p].priority() >= array[rightKid - 1].priority())//符合大根堆
                    break;
                switchElem(p, rightKid - 1);

            } else //左右都不存在 直接结束
                break;
            p = rightKid;
        }
        return (E) elem;
    }

    private void switchElem(int p, int k) {
        Priority temp = array[p];
        array[p] = array[k];
        array[k] = temp;
    }

    @Override
    public E peek() {
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
