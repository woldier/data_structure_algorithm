package com.woldier.datastruacture.ch1.d01_binary_search;

/**
 * @author woldier
 * @version 1.0
 * @description 二分查找重复元素时查询最左或者最右
 * @date 2023/6/9 10:00
 **/
public class BinarySearch03 {
    /***
     * description 当存在重复元素时返回最左侧的元素(元素下标最小)索引
     <pre>
     *(1)
     *  (i)  (m)    (j)
     *  ↓     ↓     ↓
     *  1 3 4 4 4 5 6
     </pre>
     *
     <pre>
     *(2)
     *  i m   j
     *  ↓ ↓   ↓
     *  1 3 4 4 4 5 6
     </pre>
     <pre>
     *(3)
     *      m
     *      i j
     *      ↓ ↓
     *  1 3 4 4 4 5 6
     </pre>
     <pre>
     *(4)
     *    j i
     *    ↓ ↓
     *  1 3 4 4 4 5 6
     </pre>
     *例如,当查询的目标为4时, 那么查询到索引为4相等就返回了,然而他并不是最左或最右的值
     * 因此,我们需要假定此时m索引的左边存在更合适的索引其值相等,那么我们让j移至m-1继续查询
     * 算法步骤如下
     * <p>当i<=j时,计算中间值m</p>
     * <p>若a[m]大于target,i = m+ 1;若a[m]小于target,j = m -1;若a[m] = target,记录当前索引为候选者,j = m-1</p>
     * @param a 数组
     * @param target 查询的目标
     * @return int 返回的值
     * @author: woldier
     * @date: 2023/6/9 10:03
     */
    public static int binarySearchLeftMost01(int[] a, int target) {
        int i = 0, j = a.length, candidate = -1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (a[mid] < target)
                i = mid + 1;
            else if (a[mid] > target) {
                j = mid - 1;
            } else {
                candidate = mid;
                j = mid - 1;
            }
        }
        return candidate;
    }

    /***
     *
     * description z最左查询的优化版
     *
     * @param a 数组
     * @param target 查询的目标元素
     * @return int 返回 ≥target的最小索引
     *  @author: woldier
     * @date: 2023/6/9 10:40
     */
    public static int binarySearchLeftMost02(int[] a, int target) {
        int i = 0, j = a.length;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (a[mid] <= target)
                i = mid + 1;
            else if (a[mid] > target) {
                j = mid - 1;
            }

        }
        return i;
    }


    /***
     *
     * description 当存在重复元素时返回最右侧的元素(元素下标最大)索引
     <pre>
     *(1)
     *  (i)  (m)    (j)
     *  ↓     ↓     ↓
     *  1 3 4 4 4 5 6
     </pre>
     *
     <pre>
     *(2)
     *          i m j
     *          ↓ ↓ ↓
     *  1 3 4 4 4 5 6
     </pre>
     <pre>
     *(3)
     *
     *          i(jm)
     *          ↓
     *  1 3 4 4 4 5 6
     </pre>
     <pre>
     *(4)
     *          j i
     *          ↓ ↓
     *  1 3 4 4 4 5 6

     </pre>
     *例如,当查询的目标为4时, 那么查询到索引为4相等就返回了,然而他并不是最左或最右的值
     * 因此,我们需要假定此时m索引的左边存在更合适的索引其值相等,那么我们让i移至m+1继续查询
     * 算法步骤如下
     * <p>当i<=j时,计算中间值m</p>
     * <p>若a[m]大于target,i = m+ 1;若a[m]小于target,j = m -1;若a[m] = target,记录当前索引为候选者,i = m+1</p>
     * @param a 数组
     * @param target 查询的目标
     * @return int 返回的值
     * @author: woldier
     * @date: 2023/6/9 10:26
     */

    public static int binarySearchRightMost01(int[] a, int target) {
        int i = 0, j = a.length, candidate = -1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (a[mid] < target)
                i = mid + 1;
            else if (a[mid] > target) {
                j = mid - 1;
            } else {
                candidate = mid;
                i = mid + 1;
            }
        }
        return candidate;
    }


    /***
     *
     * description TODO
     *
     * @param a 数组
     * @param target 目标元素
     * @return int 返回≤target的最右侧元素
     * @author: woldier
     * @date: 2023/6/9 11:02
     */
    public static int binarySearchRightMost02(int[] a, int target) {
        int i = 0, j = a.length;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (a[mid] <= target)
                i = mid + 1;
            else if (a[mid] > target) {
                j = mid - 1;
            }
        }
        return i - 1;
    }
}
