package com.woldier.datastruacture.ch2.d01_dynamic_array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author woldier
 * @version 1.0
 * @description 动态数组, 需要注意的是, 本实现是线程不安全的实现, 再多线程条件下, 会出现线程安全问题
 * @date 2023/6/12 18:27
 **/
public class DynamicArray implements Iterable<Integer> {
    /**
     * 容量
     */
    private int capacity = 8;
    /**
     * 当前数组大小
     */
    private int size = 0;
    /**
     * 数组,采用懒汉式加载的方式
     */
    private int[] elements;

    /***
     *
     * description 添加元素
     *
     *
     * @param index 添加的索引位置
     * @param elem  添加的元素
     * @return void
     * @author: woldier
     * @date: 2023/6/13 16:39
     */
    public void add(int index, int elem) {
        //动态扩容
        check4AccCapacity();
        //size 可以当作可插入元素的最大位置
        if (0 <= index && index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = elem;
        size++;
    }

    /***
     *
     * description 检查是狗需要扩容,并且添加懒汉模式创建初始化数组
     *
     *
     * @return void
     * @author: woldier
     * @date: 2023/6/13 19:08
     */
    private void check4AccCapacity() {
        if (size == 0) elements = new int[capacity]; //lazy mode
        if (size == capacity) {
            capacity += capacity >>> 1;
            int[] newArr = new int[capacity];
            System.arraycopy(elements, 0, newArr, 0, size);
            elements = newArr;
        }
    }

    /***
     *
     * description 在末尾添加
     *
     * @param elem  元素
     * @return void
     * @author: woldier
     * @date: 2023/6/13 16:57
     */
    public void add(int elem) {
        add(size, elem);
    }


    /***
     *
     * description forEach循环
     *
     * @param consumer  消费者函数,支持lumbda编程
     * @return void
     * @author: woldier
     * @date: 2023/6/13 17:12
     */

    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(elements[i]);
        }
    }

    /***
     *
     * description 删除指定位置元素
     *
     * @param index  索引位置
     * @return int
     * @author: woldier
     * @date: 2023/6/13 17:41
     */
    public int remove(int index) {
        int elem = elements[index];
        if (index < size - 1)
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return elem;
    }


    /***
     * description 实现了interable接口,通过该方法返回一个迭代器对象,可以使用增强for来进行遍历
     *
     * @return java.util.Iterator<java.lang.Integer>
     * @author: woldier
     * @date: 2023/6/13 17:23
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public Integer next() {
                return elements[idx++]; //先返回,再idx+1
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(elements, 0, size));
    }


    @Test
    public void test() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add(1);

        dynamicArray.add(2);
        dynamicArray.add(4);
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(6);
        dynamicArray.add(6);
        dynamicArray.add(6);
        dynamicArray.add(6);
        dynamicArray.remove(4);

//        System.out.println("==========自定义foreach============");
//        dynamicArray.forEach(System.out::print);
//        System.out.println("\n==========增强for,通过实现Iterable接口获得的特性============");
        for (Integer integer : dynamicArray) {
            System.out.print(integer);
        }
//        System.out.println("\n==========Iterable接口自带默认方法forEach============");
//        dynamicArray.forEach(System.out::print);
//        System.out.println("\n==========stream============");
//        dynamicArray.stream().forEach(System.out::print);

    }

}
