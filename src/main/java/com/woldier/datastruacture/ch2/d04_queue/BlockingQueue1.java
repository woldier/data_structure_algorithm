package com.woldier.datastruacture.ch2.d04_queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements BlockingQueue<E> {
    private E[] array;
    private int size;
    private int head;
    private int tail;

    private ReentrantLock lock = new ReentrantLock();
    Condition tailWaits = lock.newCondition();
    Condition headWaits = lock.newCondition();

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) tailWaits.await();  //队满等待

            //入队操作
            array[tail++] = e;
            if (tail == array.length) tail = 0;
            size++;
            headWaits.signal();//唤醒空等待线程
        } finally {
            lock.unlock();
        }

    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit timeUnit) throws InterruptedException {

        lock.lockInterruptibly();
        try {
            long l = timeUnit.toNanos(timeout);
            while (isFull()) {
                if(l<=0) return false; //等待时间小于零 直接返回
                l = tailWaits.awaitNanos(l);
                //队满等待
            }
            //入队操作
            array[tail++] = e;
            if (tail == array.length) tail = 0;
            size++;
            headWaits.signal();//唤醒空等待线程
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) headWaits.await();  //队满等待

            //出队操作
            E e = array[head];
            array[head++] = null;
            if (head == array.length)
                head = 0;
            size--;
            tailWaits.signal();//唤醒满等待线程
            return e;
        } finally {
            lock.unlock();
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
