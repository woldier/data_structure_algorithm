package com.woldier.datastruacture.ch2.d03_recursion;

/**
 * @author woldier
 * @version 1.0
 * @description 冒泡排序 递归实现
 * @date 2023/6/17 14:02
 **/
public class BubbleSort {
    /**
    *
    * description 冒泡排序

    *   <pre>
     *       {@code
     *
     *            | 已排序区域
     *value 1 5 4 3 8 9
     *index 0 1 2 3 4 5

     *       }
    *   </pre>
     *   算法思想是 根据j的索引从0-j中找到最大的元素排到最后
     *
    * @param nums 数组
     * @param j  数组的最大索引
    * @return void
    * @author: woldier
    * @date: 2023/6/17 14:04
    */
    public static void bubbleSort1(int [] nums,int j){
        if(j==0) return;
        for (int i = 0;i<j;i++){
            if (nums[i]>nums[i+1]){
                int temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }

        }
        bubbleSort1(nums,j-1);
    }

    /**
     *
     * description 冒泡排序

     *   <pre>
     *       {@code
     *
     *            | 已排序区域
     *value 1 5 4 3 8 9
     *index 0 1 2 3 4 5

     *       }
     *   </pre>
     *   算法思想是 与前面的实现不同的是,比较过后,如果需要交换,那么会记录下交换的位置,
     *   这样如果没有发生交换,会大大加快递归过程
     *
     * @param nums 数组
     * @param j  数组的最大索引
     * @return void
     * @author: woldier
     * @date: 2023/6/17 14:04
     */
    public static void bubbleSort2(int [] nums,int j){

    }
}
