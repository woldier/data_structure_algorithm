package com.woldier.datastruacture.ch2.d03_recursion;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/6/17 10:46
 **/
public class BinarySearch {
    public static int binarySearch(int[] nums,int
            target,int i,int j){
        if(i>j) return -1;
        int mid  = (i + j) >>> 1;
        if(nums[mid]>target)
            return binarySearch(nums,target,i,mid-1);
        else if(nums[mid]<target)
            return binarySearch(nums, target, mid+1, j);
        else  return mid;
    }
}
