package com.woldier.datastruacture.ch2.d03_recursion;

import java.util.Arrays;

/**
 * description 杨辉三角递归计算实现
 *
 * @author: root
 * @date: 2023/6/25 下午12:53
 */
public class PascalTriangle {
    /**
     * description 杨辉三角递归计算
     *
     * @return
     * @author: root
     * @date: 2023/6/25 下午12:54
     */
    private static int elem(int i, int j) {
        if (j == 0 || i == j) return 1;
        return elem(i - 1, j - 1) + elem(i - 1, j);
    }

    private final static int[][] men = new int[16][16];

    static {
        for (int[] man : men) {
            Arrays.fill(man, 0);
        }
    }

    /**
     * description 杨辉三角递归实现优化(记忆法),通过缓存相同计算,达到加速的目的
     *
     * @return
     * @author: root
     * @date: 2023/6/25 下午1:16
     */
    private static int elem2(int i, int j) {
        if (j == 0 || i == j) return 1;
        if (men[i][j] != 0) return men[i][j];
        int a = elem(i - 1, j - 1) + elem(i - 1, j);
        men[i][j] = a;
        return a;
    }

    /**
     * description 动态数组规划
     * <pre>
     *     {@code
     *          1 0 0 0 0 0  i=0
     *          1 1 0 0 0 0  i=1
     *          1 2 1 0 0 0  i=2
     *          1 3 3 1 0 0  i=3
     *          1 4 6 4 1 0  i=4
     *     }
     * </pre>
     * 通过观察可以发现,第i行都可以由i-1行计算得到
     *
     * @return
     * @author: root
     * @date: 2023/6/25 下午1:34
     */
    private static void createRow(int i, int[] nums) {
        if(i==0) {nums[0] =1 ;return ;}
        for (int j = i; j >0 ; j--) {
            nums[j] = nums[j] + nums[j-1];
        }
    }

    private static void printSpace(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(" ");
        }
    }

    public static void test1() {
        for (int i = 0; i <= 4; i++) {
            printSpace((4 - i) * 2);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", elem(i, j));
            }
            System.out.println();

        }
    }

    public static void test2(int layer) {
        for (int i = 0; i <= layer; i++) {
            printSpace((layer - i) * 2);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", elem2(i, j));
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        test2(8);
    }
}
