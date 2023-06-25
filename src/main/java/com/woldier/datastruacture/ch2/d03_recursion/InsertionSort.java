package com.woldier.datastruacture.ch2.d03_recursion;

public class InsertionSort {
    public static void sort(int[] nums){
        insertionSort(nums
        ,0);
    }
    /**
    *
    * description 插入排序,递归实现
    *
    * @return
    * @author: root
    * @date: 2023/6/21 上午9:11
    */
    private static void insertionSort(int [] nums,int low){
        if(low == nums.length -1 ) return; //递归结束条件
        int value = nums[low+1];
        int p = low;
        while(p>-1&&nums[p]>value){
            nums[p+1] = nums[p];

            p--;
        }
        nums[p+1] = value;
        insertionSort(nums,low+1);
    }
}
