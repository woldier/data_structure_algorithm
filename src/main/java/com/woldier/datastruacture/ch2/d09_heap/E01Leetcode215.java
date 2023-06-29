package com.woldier.datastruacture.ch2.d09_heap;

/**
 * description 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 10e5
 * -104 <= nums[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/29 下午8:45
 */
public class E01Leetcode215 {
    /**
     * description 解题思路是,先创建一个小根堆,然后从数组向其中添加k个元素,
     * 之后如果数组元素小于等于小根堆的根则跳过,否则替换掉根并下潜
     * <p>
     * 排序使用的是堆排序
     *
     * @param nums
     * @param k
     * @return 返回第k大的元素
     * @author: woldier
     * @date: 2023/6/29 下午8:47
     */
    public int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(10000);
        int i=0;
        while(i<nums.length){
            if(k>0){  //前k个元素入堆
                heap.offer(nums[i]);
                k--;
            }
            else {
                if(nums[i]>heap.array[0]) //如果数组当前元素大于根堆的 根节点 ,那么取而代之,并进行下潜
                {
                    heap.array[0] = nums[i];
                    heap.down(0);
                }
            }
            i++;
        }

        return heap.peek();
    }
}
