package com.woldier.datastruacture.ch2.d08_blockingqueue;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description 阻塞队列的双锁实现
 *
 * @author: woldier
 * @date: 2023/6/29 下午6:11
 */
public class BlockingQueue2<E> implements BlockingQueue<E> {
    private E[] array;  //数组
    /**
     * description 这里使用的是原子类是考虑到 ,
     * <p>
     * offer 与poll都会操作size ,但是他们持有不同的锁,
     * <p>
     * size存在线程安全,因此改为使用原子类
     */
    private AtomicInteger size;  //数组大小 使用的原子整数类
    private int head;  //头指针
    private int tail;  //尾指针


    private final ReentrantLock headLock = new ReentrantLock(); //头锁
    private final Condition headWaits = headLock.newCondition();
    private final ReentrantLock tailLock = new ReentrantLock();  //尾锁
    private final Condition tailWaits = tailLock.newCondition();


    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int oldSize;
        try {
            while (isFull()) tailWaits.await();  //队满等待

            //入队操作
            array[tail++] = e;
            if (tail == array.length) tail = 0;
            oldSize = size.getAndIncrement();  //原子自增

            //当入队之前如果队列元素个数大于零,那么应该由当前线程负责唤醒因为队满而阻塞的poll线程(级联唤醒)
            if (oldSize + 1 < array.length)
                tailWaits.signal();

        } finally {
            tailLock.unlock();
        }
        // 注意 headWaits.signal(); 需要获得 headLock
        // 但是持有tailLock 再获取headLock这样使得死锁条件(请求且保持)成立,会出现死锁,因此应该先释放锁再获取另一把锁

        // 另外我们并不是每次执行放入都需要取通知headWaits等待中的进程只是在当前队是空的情况下才需要这样做,
        // 剩下当队列不为空时,因该让出队的进程唤醒剩余的(级联唤醒)
        if (oldSize == 0) {
            headLock.lockInterruptibly();
            try {  //尝试唤醒因队空而等待的队列
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit timeUnit) throws InterruptedException {
        tailLock.lockInterruptibly();
        int oldSize;
        long t = timeUnit.toNanos(timeout);
        try {
            while (isFull()&&t>0) t = tailWaits.awaitNanos(t);  //队满等待
            if(t<=0) return false;
            //入队操作
            array[tail++] = e;
            if (tail == array.length) tail = 0;
            oldSize = size.getAndIncrement();  //原子自增

            //当入队之前如果队列元素个数大于零,那么应该由当前线程负责唤醒因为队满而阻塞的poll线程(级联唤醒)
            if (oldSize + 1 < array.length)
                tailWaits.signal();

        } finally {
            tailLock.unlock();
        }
        // 注意 headWaits.signal(); 需要获得 headLock
        // 但是持有tailLock 再获取headLock这样使得死锁条件(请求且保持)成立,会出现死锁,因此应该先释放锁再获取另一把锁

        // 另外我们并不是每次执行放入都需要取通知headWaits等待中的进程只是在当前队是空的情况下才需要这样做,
        // 剩下当队列不为空时,因该让出队的进程唤醒剩余的(级联唤醒)
        if (oldSize == 0) {
            headLock.lockInterruptibly();
            try {  //尝试唤醒因队空而等待的队列
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E e;
        int oldSize;
        try {
            while (isEmpty()) headWaits.await();  //队空等待

            //出队操作
            e = array[head];
            array[head++] = null;
            if (head == array.length)
                head = 0;
            oldSize = size.getAndDecrement();  //原子减

            //当出队之前如果队列元素个数大于零,那么应该由当前线程负责唤醒因为队空而阻塞的poll线程(级联唤醒)
            if (oldSize -1 > 0)
                headWaits.signal();

        } finally {
            headLock.unlock();
        }

        // 注意 tailWaits.signal();需要获得 tailLock
        // 但是持有headLock 再获取ailLock这样使得死锁条件(请求且保持)成立,会出现死锁,因此应该先释放锁再获取另一把锁
        tailLock.lockInterruptibly();

        // 另外我们并不是每次执行放入都需要取通知tailWaits等待中的进程只是在当前队是满的情况下才需要这样做,
        // 剩下当队列不为满时,因该让入队的进程唤醒剩余的(级联唤醒)
        if (oldSize == array.length) {
            try {//尝试唤醒因队满而阻塞的进程
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return e;

    }

    public boolean isEmpty() {
        return size.get() == 0;
    }

    public boolean isFull() {
        return size.get() == array.length;
    }
}
