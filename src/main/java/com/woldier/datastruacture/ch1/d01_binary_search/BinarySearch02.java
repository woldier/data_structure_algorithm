package com.woldier.datastruacture.ch1.d01_binary_search;

/**
 * @author woldier
 * @version 1.0
 * @description 二分查找的平衡实现
 * @date 2023/6/9 9:19
 **/
public class BinarySearch02 {
    /**
     * description 二分查找的平衡版
     * <p>1.初始时i=0,j等于边界即a.length </p>
     * <p>2.确定中点m坐标</p>
     * <p>3.判断target与a[m]的关系</p>
     * 若target小于a[m]说明在可能在左侧,那么j移至m
     * 否则target大于等于a[m]说明可能在右侧或者就是中点,那么i移至m
     * <p>4.当{@code i<j-1}不成立的话就跳出循环</p>
     *
     * @param a      数组
     * @param target 目标元素
     * @return int 查找成功返回目标元素索引,失败返回-1
     * @author: woldier
     * @date: 2023/6/9 9:21
     */
    public static int binary_search_average(int[] a, int target) {
        int i = 0, j = a.length;
        while (i < j - 1) {
            int m = (i + j) >>> 1;
            if (target < a[m])
                j = m;
            else
                i = m;
        }
        if (target == a[i])
            return i;
        else return -1;
    }
}
