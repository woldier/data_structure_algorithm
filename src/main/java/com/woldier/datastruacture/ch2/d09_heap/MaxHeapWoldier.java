package com.woldier.datastruacture.ch2.d09_heap;

import java.util.Arrays;

public class MaxHeapWoldier {
    int[] array;
    int size;

    public MaxHeapWoldier(int capacity) {
        array = new int[capacity];
    }

    /**
     * description 通过数组建堆
     *
     * @param array 传入需要建堆的数组
     * @return
     * @author: woldier
     * @date: 2023/6/29 下午7:33
     */
    public MaxHeapWoldier(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify(); //建堆
    }


    /**
     * description 算法思想时 1. 找到最后一个非叶子节点 2.然后逐个遍历,将他们下潜
     * <p>
     * 如何找到最后一个非叶子节点? size/2 - 1 即是
     *
     * @author: woldier
     * @date: 2023/6/29 下午7:34
     */
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {

        return array[0];
    }


    /**
     * 删除堆顶元素
     *
     * @return 堆顶元素
     */
    public int poll() {
        if (size == 0) return -1;
        size--;
        swap(0, size);
        down(0);
        return array[size];
    }

    /**
     * 删除指定索引处元素
     *
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        if (index == 0) {
            size--;
            return array[0];
        }
        if (index >= size || index < 0) return -1;
        size--;
        swap(index, size);
        down(index);
        return array[size];
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) return false;
        array[size++] = offered;
        up(size - 1);
        return true;
    }

    // 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
    private void up(int offered) {
        int parent = (offered + 1) / 2 - 1; //得到父亲索引
        if (offered != 0 && array[offered] > array[parent]) { //如果i不为0,并且自身的值大于父亲的值
            swap(offered, parent); //交换
            up(parent); //上浮
        }
    }


    // 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它大
    private void down(int parent) {
        int p = parent;  //指向最大值的指针
        int left = parent * 2 + 1;
        int right = left + 1;
        //如果左节点存在,比较大小 若较大,记录其值
        if (left < size && array[p] < array[left]) p = left;  //如果左孩子存在,且左孩子值较大 记录索引
        if (right < size && array[p] < array[right]) p = right;  //如果右孩子在,且由孩子值较大,记录索引
        if (p != parent) { //如果最大值索引与parent不等,说明需要下潜
            swap(parent, p);//先交换
            down(p);//下潜
        }
    }

    // 交换两个索引处的元素
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void sort(int[] array){
        this.array = array;
        this.size = array.length;
        heapify();//建堆
        while(size>0){  //排序
            swap(0,--size);
            down(0);
        }
    }
    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 4, 5, 6, 7};
//        MaxHeap maxHeap = new MaxHeap(array);
//        System.out.println(Arrays.toString(maxHeap.array));
//
//        int[] array = {2, 3, 1, 7, 4, 5, 6};
//        MaxHeapWoldier maxHeap = new MaxHeapWoldier(array);
//        System.out.println(Arrays.toString(maxHeap.array));
//        while(maxHeap.size>0){
//            maxHeap.swap(0,--maxHeap.size);
//            maxHeap.down(0);
//        }
//        System.out.println(Arrays.toString(maxHeap.array));
        MaxHeapWoldier heap = new MaxHeapWoldier(10000);
        heap.sort(new int[]{
                3,2,1,5,6,4});
        System.out.println(Arrays.toString(heap.array));
    }
}
