package com.woldier.datastruacture.ch2.d04_queue;

import java.util.concurrent.TimeUnit;

public interface BlockingQueue<E>  {
    void offer(E e) throws InterruptedException;

    boolean offer(E e, long timeout, TimeUnit timeUnit) throws InterruptedException;

    E poll()throws InterruptedException;

}
