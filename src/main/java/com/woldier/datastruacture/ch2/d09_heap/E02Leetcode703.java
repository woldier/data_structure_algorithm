package com.woldier.datastruacture.ch2.d09_heap;

/**
 * description 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *  
 * <p>
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/29 下午9:22
 */
public class E02Leetcode703 {
    int[] array;
    int size;
    int capacity;

    public E02Leetcode703(int k, int[] nums) {
        array = new int [k];
        capacity = k ;
        int i = 0;
        while(i<k&&nums.length>i){
            offer(nums[i]);
            i++;
        }
        while(i<nums.length&&nums.length>i){
            if(nums[i]>array[0])
                replace(nums[i]);
            i++;
        }
    }

    public int add(int val) {
        if(size<capacity) offer(val);
        else if(val>array[0])
            replace(val);

        return array[0];
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

    // 将 offered 元素上浮: 直至 offered 大于父元素或到堆顶
    private void up(int offered) {
        int parent = (offered + 1) / 2 - 1; //得到父亲索引
        if (offered != 0 && array[offered] < array[parent]) { //如果i不为0,并且自身的值小于父亲的值
            swap(offered, parent); //交换
            up(parent); //上浮
        }
    }


    // 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它小
    public void down(int parent) {
        int p = parent;  //指向最小值的指针
        int left = parent * 2 + 1;
        int right = left + 1;
        //如果左节点存在,比较大小 若较大,记录其值
        if (left < size && array[p] > array[left]) p = left;  //如果左孩子存在,且左孩子值较小 记录索引
        if (right < size && array[p] > array[right]) p = right;  //如果右孩子在,且由孩子值较小,记录索引
        if (p != parent) { //如果最小值索引与parent不等,说明需要下潜
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
}
