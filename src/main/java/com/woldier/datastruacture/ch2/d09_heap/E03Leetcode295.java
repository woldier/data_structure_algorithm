package com.woldier.datastruacture.ch2.d09_heap;

/**
 * description 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder 对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 提示:
 * <p>
 * -105 <= num <= 105
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 104 次调用 addNum 和 findMedian
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/29 下午9:56
 */
public class E03Leetcode295 {
    Heap maxHeap = new Heap(10, true);
    Heap minHeap = new Heap(10, false);

    public E03Leetcode295() {

    }

    /**
     * description
     * <p>
     * <p>
     * 采用双根堆,一大根一小根(左大右小)
     * 当有元素add的时候replace = num
     * 先与大根堆根元素比较(若maxHeap.size大于0),如果add元素较小,则replace顶部元素. 记录replace的值
     * 与大根堆根元素比较(若minHeap.size大于0),如果add元素较大,则replace顶部元素. 记录replace的值
     * 随后,若两堆size相等,则replace值加入小根堆 否则加入大根堆
     *
     * @author: woldier
     * @date: 2023/6/29 下午10:06
     */
    public void addNum(int num) {
        int temp = num;
        if (maxHeap.size > 0 && num < maxHeap.peek()) {
            temp = maxHeap.peek();
            maxHeap.replace(num);
        }
        if (minHeap.size > 0 && num > minHeap.peek()) {
            temp = minHeap.peek();
            minHeap.replace(num);

        }
        if (maxHeap.size == minHeap.size)
            minHeap.offer(temp);
        else
            maxHeap.offer(temp);
    }

    /**
     * description 找值就比较简单,如果当前nums为奇数那么返回堆顶即可,如果是偶数返回两次堆顶然后取平均
     *
     * @return
     * @author: woldier
     * @date: 2023/6/30 上午9:09
     */
    public double findMedian() {
        if (minHeap.size > maxHeap.size) {
            return minHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

    }


    public static void main(String[] args) {
//        E03Leetcode295 medianFinder = new E03Leetcode295();
//        medianFinder.addNum(-1);
//        medianFinder.addNum(-2);
//        medianFinder.findMedian();
//        medianFinder.addNum(-3);
//        medianFinder.findMedian();
//        medianFinder.addNum(-4);
//        medianFinder.findMedian();
//        medianFinder.addNum(-5);
//        medianFinder.findMedian();
        E03Leetcode295 medianFinder = new E03Leetcode295();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        medianFinder.findMedian(); // return 2.0

    }

}
