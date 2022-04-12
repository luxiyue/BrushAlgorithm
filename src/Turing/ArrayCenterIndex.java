package Turing;//寻找数组的中心下标

import java.util.Arrays;

/**
 * 给定一个整数数组nums,请编写一个能够返回数组“中心下标”的方法
 * 中心下标是数组中的一个下标，其左侧所有的元素相加的和等于右侧所有元素相加的和
 * 如果数组不存在中心下标，返回-1.如果存在多个中心下标，应该返回最靠近左边的哪一个
 * 注意：中心下标可能出现在数组的两端
 */
public class ArrayCenterIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6}));
    }



    //核心思想：遍历该数组，当左边加上当前元素的和 == 右边加上当前元素的和（数组总和-左边），即找到了数组的中心坐标
    private static int pivotIndex(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        //统计所有元素值的总和
        int sum = Arrays.stream(nums).sum();
        int left = 0;
        for (int i=0;i < nums.length;i++) {
            left += nums[i];
            if (left == sum){
                return i;
            }
            sum -= nums[i];
        }
        return -1;
    }



}
