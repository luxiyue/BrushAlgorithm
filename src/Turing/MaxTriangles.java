package Turing;

import java.util.Arrays;

/**
 * 三角形的最大周长:
 * 给定由一些正数(代表长度)组成的数组arr，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * 如果不能形成任何面积不为零的三角形，返回`0。
 */
public class MaxTriangles {
    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{3,6,2,3}));
    }

    /**
     * 贪心算法分析：
     * 先给数组从小到大排序,从最后三个数 往前用滑动窗口的思想移动
     */
    public static int largestPerimeter(int[] a){
        Arrays.sort(a);
        for (int i=a.length-1;i >= 2; i--){
            if (a[i-1] + a[i-2] > a[i]) {
                return a[i-1] + a[i-2] + a[i];
            }
        }
        return 0;
    }
}
