package com.woldier.datastruacture.ch1.d01_binary_search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author woldier
 * @version 1.0
 * @description 二分查找简单实现
 * @date 2023/6/8 16:17
 **/
@Slf4j
public class BinarySearch01 {
    /**
     * description 二分查找
     *
     * @param a      升序数组
     * @param target 查找的目标元素
     * @return int 返回的数组下标
     * @author: woldier
     * @date: 2023/6/8 16:19
     */

    public static int binary_search01(int[] a, int target) {
        int head = 0, tail = a.length - 1;  //初始化头尾索引

        while (head <= tail) {
            int midIdx = (head + tail) / 2; //获取中间元素坐标
            if (a[midIdx] == target) return midIdx;  //相等则返回
            if (a[midIdx] > target)  //中部数据大于target 说明有可能在左边
                tail = midIdx - 1;
            else
                head = midIdx + 1;
        }
        return -1;
    }

    /**
     * 上述实现存在一些小细节需要讨论
     * <p>1.循环条件<p/>
     * 循环条件是head <= tail 而不能是 head < tail
     * 假设当数组的长度为1,其中有一个元素[5],此时head=0,tail=1-1=0 因此不会进入循环中,因此就算target就是数组中的元素也不会返回下标
     *
     * <p>2.中间索引的计算</p>
     * 上述代码实现使用的计算方式是 <pre>{@code midIdx = (head + tail) / 2}</pre>
     * 但是需要注意的是,当数组长度为int的正最大值时,可能出现计算负数索引
     * 例如,当数组长度为int最大值时{@code head =0  tail = Integer.MAX_VALUE};那么当查找元素大于中间索引元素时此时head 会变为中间元素的索引值加一
     * 那么此时就出现了整数计算溢出,计算结果为负数.
     * 深纠原因时因为 此时 <pre>{@code head =0x 0 100 000 000 000 ;  tail = ox 0 111 1111 1111 1111;}</pre> 两者相加的到的结果为 {@code ox 1 011 1111 1111 1111}
     * 由于java的int类型时有符号的,因此解析成了负数 然后再进行的除2 也是一个负数.
     * 为了避免此种情况的发送我们我们将两者相加的结果进行右移替代除2操作,这样便不会将最左边的值解析为符号位
     * 为了演示区别,可以做如下实验
     *
     * <pre>
     * {@code
     *         int a = Integer.MAX_VALUE / 2 ,b = Integer.MAX_VALUE;
     *         int mid1 = (a + b) /2; //res -536870913
     *         int mid2 = (a+b) >>> 1;// res 1610612735
     *         }
     * <pre/>
     *
     * <p>3.不等式比较要符合思维逻辑</p>
     */
    public static int binary_search01_(int[] a, int target) {
        int head = 0, tail = a.length - 1;  //初始化头尾索引

        while (head <= tail) {
            int midIdx = (head + tail) >>> 1; //获取中间元素坐标
            if (a[midIdx] == target) return midIdx;  //相等则返回
            if (a[midIdx] > target)  //中部数据大于target 说明有可能在左边
                tail = midIdx - 1;
            else
                head = midIdx + 1;
        }
        return -1;
    }

    /***
     *
     * description 二分查找的改动版本
     *    <pre>
     *          |(i)              |(j)
     *          [] [] [] [] [] []
     *          0  1  2  3  4  5  6
     *    </pre>
     *这种实现中i依旧指向左区间,但是j指向的时右边界,其值不参与比较.
     * 计算得到的中间元素也与之前有所不同
     *    <pre>
     *          |(i)     |(m)     |(j)
     *          [] [] [] [] [] []
     *          0  1  2  3  4  5  6
     *    </pre>
     * 此时如果索引{@code m}对应的元素小于{@code target} 说明可能在右边,因此{@code i=m+1}
     * 如果索引{@code m}对应的元素大于{@code target} 说明可能在左边,因此{@code j=m} 边界移至m 而不是m-1,因为边界对应的索引并不参与比较
     *
     *
     * 结束条件也较为不同
     *    <pre>
     *                (i) (j)
     *                |  |
     *          [] [] [] [] [] []
     *          0  1  2  3  4  5  6
     *    </pre>
     *    当 {@code i<j} 时循环继续 , 直到出现上述情况,
     *    此时计算{@code m=(i+j)>>>1}结果是2,此时比较m随意对应的值若,
     *    如果索引{@code m}对应的元素小于{@code target} 说明可能在右边,因此{@code i=m+1} 此时i已经是边界了 循环应该结束
     *    如果索引{@code m}对应的元素大于{@code target} 说明可能在左边,因此{@code j=m} 边界移至m ,此时i也是边界了 循环应该结束
     * @param a  数组
     * @param target  目标元素
     * @return int
     * @author: woldier
     * @date: 2023/6/8 17:24
     */
    public static int binary_search02(int[] a, int target) {
        int i = 0, j = a.length;
        while (i < j) {
            int m = (i+j) >>> 1;
            if(a[m]<target)
                i = m+1;
            else if (a[m]>target) {
                j = m ;
            }
            else return m;
        }
        return -1;
    }


    @Test
    public void test_int_overflow() {
        int a = Integer.MAX_VALUE / 2, b = Integer.MAX_VALUE;
        int mid1 = (a + b) / 2;
        int mid2 = (a + b) >>> 1;
        System.out.printf("mid1:%d,mid2:%d", mid1, mid2);
    }

    @Test
    public void test_1_binary_search01() {
        int[] a = {1, 2, 3, 3, 8, 12};
        Assert.assertEquals(1, binary_search02(a, 2));
    }

    @Test
    public void test_2_binary_search01() {
        int[] a = {1, 2, 3, 3, 8, 12};
        Assert.assertEquals(-1, binary_search01(a, 88));
    }


}
