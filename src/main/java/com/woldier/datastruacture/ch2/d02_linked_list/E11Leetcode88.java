package com.woldier.datastruacture.ch2.d02_linked_list;

import java.util.Arrays;

/**
 * description 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/26 下午7:56
 */
public class E11Leetcode88 {

    /**
     * description 算法思想是定义两个数组指针分别指向尾部
     * 然后从数组尾部进行比较,将较大的放入nums1数组的尾部
     *
     * <pre>
     *     {@code
     *           ↓p1   ↓
     *     1 3 5 7 0 0 0
     *         ↓p2
     *     2 4 6
     *      第一轮比较 p1指针的值较大放入尾部
     *         ↓p1   ↓
     *     1 3 5 7 0 0 7
     *         ↓p2
     *     2 4 6
     *     继续移动
     *     p1
     *     ↓ ↓
     *     1 3 3 4 5 6 7
     *     ↓p2
     *     2 4 6
     *
     *     p1
     *     ↓ ↓
     *     1 2 3 4 5 6 7
     *   ↓p2
     *     2 4 6
     *     }
     * </pre>
     *
     * @param nums1 待合并数组1
     * @param m     数组1的元素个数
     * @param nums2 待合并数组2
     * @param n     数组2的元素个数
     * @return
     * @author: woldier
     * @date: 2023/6/26 下午7:57
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m==0){
            System.arraycopy(nums2,0,nums1,0,n);
            return;
        }
        if(n==0) return;
        int p1 = m - 1, p2 = n - 1, p = nums1.length-1;
        while (true) {
            if (nums1[p1] > nums2[p2])
                nums1[p] = nums1[p1--];
            else
                nums1[p] = nums2[p2--];
            p--;
            if(p1==-1){
                System.arraycopy(nums2,0,nums1,0,p2+1);
                return;
            }
            if(p2==-1) return;
        }
    }

}
