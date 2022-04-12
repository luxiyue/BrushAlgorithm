package Turing;

import java.util.Arrays;

/**
 * 整数数组nums，在数组中找出由三个数字组成的最大乘积，并输出这个乘积
 * 乘积不会越界
 */
public class ThreeMaxProduct {

    public static void main(String[] args) {
        System.out.println(sort(new int[]{-20,-3,4,5,6}));
        System.out.println(getMaxMin(new int[]{-20,-3,4,5,6}));
    }

    //方法一：排序法找最值
    //最大的三个数 和 最小的两个数（可能为负数）*最大数 取最大值
    public static int sort(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0]*nums[1]*nums[n-1],nums[n-1]*nums[n-2]*nums[n-2]);
    }


    //方法二
    //本函数主要是对上面方法的改进，找出最大的三个值和最小的2个值
    public static int getMaxMin(int[] nums){
        int min1 = Integer.MAX_VALUE,min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE,max3 = Integer.MIN_VALUE;

        for ( int x : nums ) {
            //找最小
            if (x < min1) {
                min2 = min1;
                min1 = x;
            }else if (x < min2){
                min2 = x;
            }
            //找最大
            if (x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            }else if (x > max2){
                max3 = max2;
                max2 = x;
            }else if (x > max3){
                max3 = x;
            }
        }

        return Math.max(min1*min2*max1,max1*max2*max3);
    }



}
